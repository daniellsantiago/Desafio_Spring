package com.grupo2.desafiospring.exception;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
@AllArgsConstructor
public abstract class AbstractApplicationException extends RuntimeException {
    private final String errorMessage;
    private final String errorType;
    private final HttpStatus httpStatusCode;
}
