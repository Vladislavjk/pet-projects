package com.syberry.server.exception;

public class WishlistNotFoundException extends NotFoundException {
    public WishlistNotFoundException() {
        super("Wishlist not found");
    }
}
