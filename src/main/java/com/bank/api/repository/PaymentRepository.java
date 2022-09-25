package com.bank.api.repository;

import com.bank.api.model.payment.PaymentDetails;
import org.springframework.data.repository.CrudRepository;

/**
 * Interface that implements the Payment Repository .
 */
public interface PaymentRepository extends CrudRepository<PaymentDetails, Long> {

}
