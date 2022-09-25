package com.bank.api.web;

import com.bank.api.model.payment.PaymentDetails;
import com.bank.api.model.payment.PaymentRequest;
import com.bank.api.service.PaymentService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@Slf4j
@RestController
@RequestMapping("/payments")
public class PaymentController {

    private PaymentService paymentService;

    @Autowired
    public PaymentController(PaymentService paymentService) {
        this.paymentService = paymentService;
    }

    /**
     * Method to make a payment.
     *
     * @param paymentRequest
     * @return PaymentDetails object
     * <p>
     * HTTP Status:
     * <p>
     * 201 - Created: Everything worked as expected.
     * 400 - Bad Request: The request was unacceptable, often due to missing a required parameter.
     */
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public PaymentDetails makePayment(@RequestBody PaymentRequest paymentRequest) {
        log.info("Recieved Payment Request");
        return paymentService.makePayment(paymentRequest);
    }
}
