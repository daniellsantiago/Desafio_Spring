package com.grupo2.desafiospring.exception;

import org.springframework.http.HttpStatus;

public class NotFoundException extends AbstractApplicationException {
    public NotFoundException(String errorMessage) {
        super(errorMessage, "NOT_FOUND_ERROR", HttpStatus.NOT_FOUND);
    }
}
