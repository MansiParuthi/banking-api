package com.bank.api.model.account;

import com.fasterxml.jackson.annotation.JsonValue;

/**
 * Enum that represents the two types of Accounts: BUSINESS and PERSONAL.
 */
public enum AccountType {
    BUSINESS("Business"),
    PERSONAL("Personal");

    private final String value;

    AccountType(String accountType) {
        this.value = accountType;
    }

    @JsonValue
    public String getValue() {
        return value;
    }
}
