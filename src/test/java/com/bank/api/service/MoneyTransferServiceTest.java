package com.bank.api.service;

import com.bank.api.exception.PaymentExecutionException;
import com.bank.api.model.account.AccountData;
import com.bank.api.model.payment.PaymentDetails;
import com.bank.api.model.payment.PaymentRequest;
import com.bank.api.repository.AccountRepository;
import com.bank.api.repository.PaymentRepository;
import org.aspectj.lang.annotation.Before;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.math.BigDecimal;
import java.util.Optional;

import static com.bank.api.utils.TestUtils.*;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

/**
 * Class that implements tests of the MoneyTransferService features.
 */
@ExtendWith(MockitoExtension.class)
class MoneyTransferServiceTest {

    @InjectMocks
    private MoneyTransferService moneyTransferService;

    @Mock
    private AccountRepository accountRepository;

    @Mock
    private PaymentRepository paymentRepository;

    /**
     * Method to test payment flow.
     */
    @Test
    public void shouldMakePayment() {

        //assemble
        PaymentRequest paymentRequest = getPaymentRequest1(Long.valueOf(256), Long.valueOf(123));
        PaymentDetails expectedPaymentDetails = getPaymentDetails();
        expectedPaymentDetails.setPaymentId(null);
        AccountData creditor = getAccountData3();
        creditor.setAccountId(paymentRequest.getCreditorAccountId());
        AccountData debitor = getAccountData2();
        debitor.setAccountId(paymentRequest.getDebitorAccountId());
        when(accountRepository.findByAccountIdEquals(paymentRequest.getCreditorAccountId())).thenReturn(Optional.of(creditor));
        when(accountRepository.findByAccountIdEquals(paymentRequest.getDebitorAccountId())).thenReturn(Optional.of(debitor));
        AccountData expectedDebitor = getAccountData2();
        expectedDebitor.setAmount(debitor.getAmount().subtract(paymentRequest.getAmount()));
        AccountData expectedCreditor = getAccountData2();
        expectedCreditor.setAmount(creditor.getAmount().add(paymentRequest.getAmount()));
        when(accountRepository.save(debitor)).thenReturn(expectedDebitor);
        when(accountRepository.save(creditor)).thenReturn(expectedCreditor);
        when(paymentRepository.save(expectedPaymentDetails)).thenReturn(expectedPaymentDetails);

        //act
        PaymentDetails actualPaymentDetails = moneyTransferService.makePayment(paymentRequest);

        //assert
        assertEquals(actualPaymentDetails.getPaymentId(), expectedPaymentDetails.getPaymentId());
        assertEquals(actualPaymentDetails.getPaymentStatus(), expectedPaymentDetails.getPaymentStatus());
    }

    /**
     * Method to test payment flow when balance is low.
     */
    @Test
    public void shouldThrowBalanceIsLowExceptionWhenBalanceISLow() {

        //assemble
        PaymentRequest paymentRequest = PaymentRequest.builder()
                .amount(BigDecimal.valueOf(100))
                .creditorAccountId(Long.valueOf(256))
                .debitorAccountId(Long.valueOf(123))
                .build();
        AccountData creditor = getAccountData3();
        creditor.setAccountId(paymentRequest.getCreditorAccountId());
        AccountData debitor = getAccountData2();
        debitor.setAccountId(paymentRequest.getDebitorAccountId());
        when(accountRepository.findByAccountIdEquals(paymentRequest.getCreditorAccountId())).thenReturn(Optional.of(creditor));
        when(accountRepository.findByAccountIdEquals(paymentRequest.getDebitorAccountId())).thenReturn(Optional.of(debitor));


        //act
        try {
            moneyTransferService.makePayment(paymentRequest);
        } catch (PaymentExecutionException e) {
            assertEquals(e.getMessage(), "Could not execute payment. Please check your balance");
        }
    }

    /**
     * Method to test payment flow when account is not found.
     */
    @Test
    public void shouldThrowAccountNotFoundException() {

        //assemble
        PaymentRequest paymentRequest = PaymentRequest.builder()
                .build();

        //act
        try {
            moneyTransferService.makePayment(paymentRequest);
        } catch (PaymentExecutionException e) {
            assertEquals(e.getMessage(), "Could not execute payment. Please check your details");
        }
    }


}