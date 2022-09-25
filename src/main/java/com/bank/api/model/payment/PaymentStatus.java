package com.bank.api.model.payment;

import com.fasterxml.jackson.annotation.JsonValue;

/**
 * Enum that represents the two types of Payment Status: SUCCESS and FAIL.
 */
public enum PaymentStatus {
    SUCCESS("Success"),
    FAIL("Fail");

    private final String value;

    PaymentStatus(String paymentStatus) {
        this.value = paymentStatus;
    }

    @JsonValue
    public String getValue() {
        return value;
    }
}
