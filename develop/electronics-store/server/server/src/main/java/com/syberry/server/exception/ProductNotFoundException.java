package com.syberry.server.exception;

public class ProductNotFoundException extends NotFoundException {
    public ProductNotFoundException() {
        super("Product not found");
    }
}
