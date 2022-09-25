package com.bank.api.model.payment;

import com.sun.istack.NotNull;
import lombok.Builder;
import lombok.Data;

import javax.persistence.*;

/**
 * Class that represents Payment Details.
 */
@Entity
@Data
@Builder
@Table(name = "payments")
public class PaymentDetails {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    Long paymentId;

    @NotNull
    @Enumerated(EnumType.STRING)
    @Column(name = "payment_status")
    PaymentStatus paymentStatus;
}
