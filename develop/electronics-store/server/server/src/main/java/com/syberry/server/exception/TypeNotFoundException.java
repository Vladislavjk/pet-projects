package com.syberry.server.exception;

public class TypeNotFoundException extends NotFoundException {
    public TypeNotFoundException() {
        super("Type not found");
    }
}
