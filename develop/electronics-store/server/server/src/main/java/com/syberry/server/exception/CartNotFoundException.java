package com.syberry.server.exception;

public class CartNotFoundException extends NotFoundException {
    public CartNotFoundException() {
        super("Cart not found");
    }
}
