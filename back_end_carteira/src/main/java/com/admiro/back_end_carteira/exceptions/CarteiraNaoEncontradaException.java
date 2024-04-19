package com.admiro.back_end_carteira.exceptions;

public class CarteiraNaoEncontradaException extends RuntimeException {

    public CarteiraNaoEncontradaException(String message) {
        super(message);
    }

    public CarteiraNaoEncontradaException(String message, Throwable cause) {
        super(message, cause);
    }
}

