package com.bank.api.exception;

/**
 * Class that implements AccountNotFoundException.
 */
public class AccountNotFoundException extends RuntimeException {

    public AccountNotFoundException(String message) {
        super(message);
    }
}
