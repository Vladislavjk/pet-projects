package com.syberry.server.exception;

public class OrderNotFoundException extends NotFoundException {
    public OrderNotFoundException() {
        super("Order not found");
    }
}
