package com.bank.api.utils;

import com.bank.api.model.account.AccountData;
import com.bank.api.model.account.AccountType;
import com.bank.api.model.payment.PaymentDetails;
import com.bank.api.model.payment.PaymentRequest;
import com.bank.api.model.payment.PaymentStatus;

import java.math.BigDecimal;

import static java.util.UUID.randomUUID;

/**
 * Class that implements util functions
 */
public class TestUtils {

    public static PaymentRequest getPaymentRequest1(Long accountId1, Long accountId2) {
        return PaymentRequest.builder()
                .amount(new BigDecimal(30))
                .creditorAccountId(accountId1)
                .debitorAccountId(accountId2)
                .build();
    }

    public static PaymentRequest getPaymentRequest2(Long accountId1, Long accountId2) {
        return PaymentRequest.builder()
                .amount(new BigDecimal(40))
                .creditorAccountId(accountId2)
                .debitorAccountId(accountId1)
                .build();
    }

    public static AccountData getAccountData2() {
        return AccountData.builder()
                .accountType(AccountType.PERSONAL)
                .amount(new BigDecimal("50.00"))
                .description("Personal Account")
                .customerId(randomUUID().toString())
                .currency("AED")
                .build();
    }

    public static AccountData getAccountData3() {
        return AccountData.builder()
                .accountType(AccountType.PERSONAL)
                .amount(new BigDecimal("50.00"))
                .description("Personal Account")
                .customerId(randomUUID().toString())
                .currency("AED")
                .build();
    }

    public static PaymentDetails getPaymentDetails() {
        return PaymentDetails.builder()
                .paymentId(Long.valueOf("23"))
                .paymentStatus(PaymentStatus.SUCCESS)
                .build();
    }

    public static AccountData getAccountData1() {
        return AccountData.builder()
                .accountType(AccountType.PERSONAL)
                .amount(new BigDecimal("50.00"))
                .description("Personal Account")
                .customerId(randomUUID().toString())
                .currency("AED")
                .build();
    }
}
