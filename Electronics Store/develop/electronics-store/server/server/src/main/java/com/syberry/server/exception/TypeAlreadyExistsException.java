package com.syberry.server.exception;

public class TypeAlreadyExistsException extends RuntimeException {
    public TypeAlreadyExistsException(String message) {
        super(message);
    }
}
