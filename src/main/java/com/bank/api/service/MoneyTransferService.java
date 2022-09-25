package com.bank.api.service;

import com.bank.api.exception.PaymentExecutionException;
import com.bank.api.model.account.AccountData;
import com.bank.api.model.payment.PaymentDetails;
import com.bank.api.model.payment.PaymentRequest;
import com.bank.api.model.payment.PaymentStatus;
import com.bank.api.repository.AccountRepository;
import com.bank.api.repository.PaymentRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import javax.security.auth.login.AccountNotFoundException;

/**
 * Class that provides methods for MoneyTransfer.
 */
@Slf4j
@Service
public class MoneyTransferService {

    @Autowired
    private AccountRepository accountRepository;

    @Autowired
    private PaymentRepository paymentRepository;

    /**
     * Method that saves payment in the database.
     *
     * @param paymentRequest
     * @return PaymentDetails object
     */
    @Transactional(propagation = Propagation.REQUIRES_NEW)
    public PaymentDetails makePayment(PaymentRequest paymentRequest) {
        try {
            AccountData debitor = getDebitorAccount(paymentRequest.getDebitorAccountId());
            AccountData creditor = getCreditorAccount(paymentRequest.getCreditorAccountId());
            if (debitor.getAmount().intValue() >= paymentRequest.getAmount().intValue()) {
                debitor.setAmount(debitor.getAmount().subtract(paymentRequest.getAmount()));
                creditor.setAmount(creditor.getAmount().add(paymentRequest.getAmount()));
                accountRepository.save(creditor);
                accountRepository.save(debitor);
                PaymentDetails paymentDetails = PaymentDetails.builder().paymentStatus(PaymentStatus.SUCCESS).build();
                paymentRepository.save(paymentDetails);
                return paymentDetails;
            } else {
                log.info("Balance is low");
                throw new PaymentExecutionException("Could not execute payment. Please check your balance");
            }
        } catch (AccountNotFoundException e) {
            log.info("Could not find creditor or debitor account");
            throw new PaymentExecutionException("Could not execute payment. Please check your details");
        }
    }

    /**
     * Method to find an CreditorAccount by accountId in the database.
     *
     * @param creditorAccountId
     * @return AccountData object
     */
    private AccountData getCreditorAccount(Long creditorAccountId) throws AccountNotFoundException {
        return accountRepository.findByAccountIdEquals(creditorAccountId).orElseThrow(AccountNotFoundException::new);
    }

    /**
     * Method to find an DebitorAccount by accountId in the database.
     *
     * @param debitorAccountId
     * @return AccountData object
     */
    private AccountData getDebitorAccount(Long debitorAccountId) throws AccountNotFoundException {
        return accountRepository.findByAccountIdEquals(debitorAccountId).orElseThrow(AccountNotFoundException::new);
    }
}
