package com.syberry.server.exception;

public class ProductExistsInCartException extends RuntimeException {
    public ProductExistsInCartException(String message) {
        super(message);
    }
}
