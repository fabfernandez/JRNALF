package com.mercadolibre.finalchallengedemo.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(InvalidPartFilterException.class)
    public ResponseEntity handleInvalidDate(InvalidPartFilterException e) {
        return handle(HttpStatus.BAD_REQUEST,e.getMessage());
    }

    private ResponseEntity<ApiError> handle(HttpStatus status, String message) {
        return new ResponseEntity<>(new ApiError(status.toString(), message, status.value() ), status);
    }
}

