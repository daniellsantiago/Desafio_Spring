package com.grupo2.desafiospring.exception;

import org.springframework.http.HttpStatus;

public class InternalServerErrorException extends AbstractApplicationException {
    public InternalServerErrorException(String errorMessage) {
        super(errorMessage, "INTERNAL_SERVER_ERROR", HttpStatus.INTERNAL_SERVER_ERROR);
    }
}
