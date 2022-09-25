package com.bank.api.exception;

import com.bank.api.model.ErrorResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

/**
 * Class to handle exception.
 */
@Slf4j
@ControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(value = {AccountNotFoundException.class})
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ResponseBody
    public ErrorResponse accountNotFoundException(AccountNotFoundException ex) {
        log.debug("Account Not Found", ex);
        return ErrorResponse.builder()
                .message("Account Not Found")
                .build();
    }

    @ExceptionHandler(value = {PaymentExecutionException.class})
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ResponseBody
    public ErrorResponse paymentExecutionException(PaymentExecutionException ex) {
        log.debug("Account Not Found", ex);
        return ErrorResponse.builder()
                .message("Could not execute payment.")
                .build();
    }
}
