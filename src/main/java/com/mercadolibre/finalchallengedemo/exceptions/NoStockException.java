package com.mercadolibre.finalchallengedemo.exceptions;

public class NoStockException extends RuntimeException {
    public NoStockException(String s) {
        super(s);
    }
}
