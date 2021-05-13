package com.mercadolibre.finalchallengedemo.exceptions;

public class ForbiddenAccessException extends RuntimeException {
    public ForbiddenAccessException(String s) {
        super(s);
    }
}
