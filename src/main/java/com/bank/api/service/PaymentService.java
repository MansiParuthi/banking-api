package com.bank.api.service;

import com.bank.api.model.payment.PaymentDetails;
import com.bank.api.model.payment.PaymentRequest;

/**
 * Interface that provides methods for Payments.
 */
public interface PaymentService {

    /**
     * Method that saves payment in the database.
     *
     * @param paymentRequest
     * @return PaymentDetails object
     */
    PaymentDetails makePayment(PaymentRequest paymentRequest);
}
