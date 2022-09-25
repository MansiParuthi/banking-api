package com.bank.api.web;

import com.bank.api.model.account.AccountData;
import com.bank.api.model.payment.PaymentRequest;
import com.bank.api.service.PaymentService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.SpyBean;

import java.math.BigDecimal;
import java.util.Arrays;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

import static com.bank.api.utils.TestUtils.*;
import static org.assertj.core.api.Java6Assertions.assertThat;

/**
 * Class that implements integration tests of the PaymentController features.
 */
@SpringBootTest
public class PaymentControllerIT {

    @SpyBean
    private PaymentService paymentService;

    @Autowired
    private PaymentController paymentController;

    @Autowired
    private AccountController accountController;

    /**
     * Method to test payment flow.
     */
    @Test
    public void shouldMakePayment() {

        //assemble
        final AccountData accountData1 = accountController.saveAccount(getAccountData1());
        final AccountData accountData2 = accountController.saveAccount(getAccountData2());

        //act
        paymentController.makePayment(getPaymentRequest1(accountData1.getAccountId(), accountData2.getAccountId()));

        //assert
        assertThat(accountController.getAccountByAccountId(accountData1.getAccountId()).getAmount()).isEqualTo(new BigDecimal("80.00"));
        assertThat(accountController.getAccountByAccountId(accountData2.getAccountId()).getAmount()).isEqualTo(new BigDecimal("20.00"));
    }

    /**
     * Method to test concurrent payment flow.
     */
    @Test
    public void shouldMakeConcurrentPayment() throws InterruptedException {

        //assemble
        final AccountData accountData1 = accountController.saveAccount(getAccountData1());
        final AccountData accountData2 = accountController.saveAccount(getAccountData2());
        final AccountData accountData3 = accountController.saveAccount(getAccountData3());
        final List<PaymentRequest> paymentRequests = Arrays.asList(getPaymentRequest1(accountData1.getAccountId(), accountData2.getAccountId()), getPaymentRequest2(accountData1.getAccountId(), accountData3.getAccountId()));

        //act
        final ExecutorService executor = Executors.newFixedThreadPool(2);
        for (final PaymentRequest paymentRequest : paymentRequests) {
            executor.execute(() -> paymentController.makePayment(paymentRequest));
        }

        executor.shutdown();
        executor.awaitTermination(2, TimeUnit.MINUTES);

        //assert
        assertThat(accountController.getAccountByAccountId(accountData1.getAccountId()).getAmount()).isEqualTo(new BigDecimal("40.00"));
        assertThat(accountController.getAccountByAccountId(accountData2.getAccountId()).getAmount()).isEqualTo(new BigDecimal("20.00"));
        assertThat(accountController.getAccountByAccountId(accountData3.getAccountId()).getAmount()).isEqualTo(new BigDecimal("90.00"));
    }
}
