package com.bank.api.model.payment;

import lombok.Builder;
import lombok.Value;

import java.math.BigDecimal;

/**
 * Class that represents Payment Request.
 */
@Builder
@Value
public class PaymentRequest {

    Long creditorAccountId;
    Long debitorAccountId;
    BigDecimal amount;
}
