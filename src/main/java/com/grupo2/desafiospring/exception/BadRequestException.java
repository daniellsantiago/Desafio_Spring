package com.grupo2.desafiospring.exception;

import org.springframework.http.HttpStatus;

public class BadRequestException extends AbstractApplicationException {
    public BadRequestException(String errorMessage) {
        super(errorMessage,"BAD_REQUEST", HttpStatus.BAD_REQUEST);
    }
}
