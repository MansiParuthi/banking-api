package com.bank.api.service;

import com.bank.api.model.payment.PaymentDetails;
import com.bank.api.model.payment.PaymentRequest;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.orm.ObjectOptimisticLockingFailureException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * Class that provides methods for Payments.
 */
@Slf4j
@Service
public class PaymentServiceImpl implements PaymentService {

    @Autowired
    MoneyTransferService moneyTransferService;

    /**
     * Method that saves payment in the database.
     *
     * @param paymentRequest
     * @return PaymentDetails object
     */
    @Override
    @Transactional(readOnly = true)
    public PaymentDetails makePayment(PaymentRequest paymentRequest) {
        try {
            return moneyTransferService.makePayment(paymentRequest);
        } catch (ObjectOptimisticLockingFailureException e) {
            log.warn("Somebody has already made payment in concurrent transaction. Will try again..");
            return moneyTransferService.makePayment(paymentRequest);
        }
    }

}
