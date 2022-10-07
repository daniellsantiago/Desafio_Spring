package com.grupo2.desafiospring.exception;

import com.grupo2.desafiospring.dto.ErrorMessageResponseDto;
import lombok.AllArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

@ControllerAdvice
@AllArgsConstructor
public class GlobalExceptionHandler {

    private final Logger logger = LoggerFactory.getLogger(GlobalExceptionHandler.class);

    @ExceptionHandler(MethodArgumentNotValidException.class)
    @ResponseBody
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ErrorMessageResponseDto handleMethodArgumentNotValidException(MethodArgumentNotValidException exception) {
        logger.error("MethodArgumentNotValidException: ", exception);
        return ErrorMessageResponseDto.withFieldErrors(exception.getBindingResult().getFieldErrors());
    }

    @ExceptionHandler(Exception.class)
    @ResponseBody
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    public ErrorMessageResponseDto handleException(Exception exception) {
        logger.error("Exception: ", exception);
        return ErrorMessageResponseDto.of("An unknown server error has occurred", "UNKNOWN_SERVER_ERROR");
    }

    @ExceptionHandler(NotFoundException.class)
    @ResponseBody
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public ErrorMessageResponseDto handleNotFoundException(NotFoundException exception) {
        logger.error("NotFoundException: ", exception);
        return ErrorMessageResponseDto.of(exception.getErrorMessage(), exception.getErrorType());
    }

    @ExceptionHandler(InternalServerErrorException.class)
    @ResponseBody
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    public ErrorMessageResponseDto handleInternalServerErrorException(InternalServerErrorException exception) {
        logger.error("InternalServerErrorException: ", exception);
        return ErrorMessageResponseDto.of(exception.getErrorMessage(), exception.getErrorType());
    }

    @ExceptionHandler(BusinessRuleException.class)
    @ResponseBody
    @ResponseStatus(HttpStatus.UNPROCESSABLE_ENTITY)
    public ErrorMessageResponseDto handleBusinessRuleException(BusinessRuleException exception) {
        logger.error("BusinessRuleException: ", exception);
        return ErrorMessageResponseDto.of(exception.getErrorMessage(), exception.getErrorType());
    }

    @ExceptionHandler(BadRequestException.class)
    @ResponseBody
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ErrorMessageResponseDto handleBadRequestException(BadRequestException exception) {
        logger.error("BadRequestException: ", exception);
        return ErrorMessageResponseDto.of(exception.getErrorMessage(), exception.getErrorType());
    }
}
