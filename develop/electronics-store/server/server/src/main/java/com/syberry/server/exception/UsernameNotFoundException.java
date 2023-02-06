package com.syberry.server.exception;

public class UsernameNotFoundException extends NotFoundException {
    public UsernameNotFoundException() {
        super("Username not found");
    }
}
