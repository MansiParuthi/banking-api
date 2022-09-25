package com.bank.api.exception;

/**
 * Class that implements PaymentExecutionException.
 */
public class PaymentExecutionException extends RuntimeException {

    public PaymentExecutionException(String message) {
        super(message);
    }
}

