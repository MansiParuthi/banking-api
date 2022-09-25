package com.bank.api.model;

import com.fasterxml.jackson.annotation.JsonAlias;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Builder;
import lombok.Value;

/**
 * Class that represents Error Response.
 */
@Value
@Builder
@JsonInclude(JsonInclude.Include.NON_NULL)
public class ErrorResponse {

    @JsonAlias("Code")
    String code;

    @JsonAlias("Id")
    String id;

    @JsonAlias("Message")
    String message;

}
