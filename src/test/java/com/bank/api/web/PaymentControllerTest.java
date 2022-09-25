package com.bank.api.web;

import com.bank.api.model.payment.PaymentDetails;
import com.bank.api.model.payment.PaymentRequest;
import com.bank.api.service.PaymentServiceImpl;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import static com.bank.api.utils.TestUtils.getPaymentDetails;
import static com.bank.api.utils.TestUtils.getPaymentRequest1;
import static org.junit.jupiter.api.Assertions.assertEquals;

/**
 * Class that implements tests of the PaymentController features.
 */
@ExtendWith(MockitoExtension.class)
public class PaymentControllerTest {

    @InjectMocks
    private PaymentController paymentController;

    @Mock
    private PaymentServiceImpl paymentService;

    /**
     * Method to test payment flow.
     */
    @Test
    public void shouldMakePayment() {

        //assemble
        PaymentRequest paymentRequest = getPaymentRequest1(Long.valueOf(123), Long.valueOf(256));
        PaymentDetails expectedPaymentDetails = getPaymentDetails();
        Mockito.when(paymentService.makePayment(paymentRequest)).thenReturn(expectedPaymentDetails);

        //act
        PaymentDetails actualPaymentDetails = paymentController.makePayment(paymentRequest);

        //assert
        assertEquals(actualPaymentDetails.getPaymentId(), expectedPaymentDetails.getPaymentId());
        assertEquals(actualPaymentDetails.getPaymentStatus(), expectedPaymentDetails.getPaymentStatus());

    }
}