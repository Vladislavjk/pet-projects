package com.syberry.server.exception;

public class BrandNotFoundException extends NotFoundException {
    public BrandNotFoundException() {
        super("Brand not found");
    }
}
