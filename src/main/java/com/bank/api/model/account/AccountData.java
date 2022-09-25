package com.bank.api.model.account;

import com.bank.api.model.BaseEntity;
import com.sun.istack.NotNull;
import lombok.*;

import javax.persistence.*;
import java.math.BigDecimal;

/**
 * Class that represents Account Data.
 */
@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@EqualsAndHashCode(callSuper = false)
@Table(name = "accounts")
public class AccountData extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    @Column(name = "account_id")
    Long accountId;

    @NotNull
    @Column(name = "amount")
    BigDecimal amount;

    @NotNull
    @Enumerated(EnumType.STRING)
    @Column(name = "account_type")
    AccountType accountType;

    @NotNull
    @Column(name = "currency")
    String currency;

    @Column(name = "account_desc")
    String description;

    @Column(name = "nickName")
    String nickName;

    @NotNull
    @Column(name = "customer_Id")
    String customerId;
}
