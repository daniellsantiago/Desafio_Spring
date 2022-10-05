package com.grupo2.desafiospring.exception;

import org.springframework.http.HttpStatus;

public class BusinessRuleException extends AbstractApplicationException {
    public BusinessRuleException(String errorMessage) {
        super(errorMessage, "BUSINESS_RULE_ERROR", HttpStatus.UNPROCESSABLE_ENTITY);
    }
}
