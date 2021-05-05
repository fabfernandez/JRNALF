package com.mercadolibre.finalchallengedemo.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindException;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestController;

import java.util.LinkedHashMap;
import java.util.Map;

@ControllerAdvice(annotations = RestController.class)
public class GlobalExceptionHandler {

    @ExceptionHandler(InvalidPartFilterException.class)
    public ResponseEntity handleInvalidDate(InvalidPartFilterException e) {
        return handle(HttpStatus.BAD_REQUEST,e.getMessage());
    }

    @ExceptionHandler(BindException.class)
    public ResponseEntity<ApiError> onMethodArgumentNotValidException(BindException ex) {
        return getResponseEntity(ex);
    }

    private ResponseEntity<ApiError> getResponseEntity(BindException ex) {
        Map<String, String> errors = new LinkedHashMap<>();
        ex.getBindingResult().getAllErrors().forEach((error) -> {
            String fieldName = ((FieldError) error).getField();
            String errorMessage = error.getDefaultMessage();
            errors.put(fieldName, errorMessage);
        });

        ApiError apiError = new ApiError("BAD_REQUEST", errors.toString(), HttpStatus.BAD_REQUEST.value());
        return ResponseEntity.status(apiError.getStatus()).body(apiError);
    }

    private ResponseEntity<ApiError> handle(HttpStatus status, String message) {
        return new ResponseEntity<>(new ApiError(status.toString(), message, status.value() ), status);
    }
}

