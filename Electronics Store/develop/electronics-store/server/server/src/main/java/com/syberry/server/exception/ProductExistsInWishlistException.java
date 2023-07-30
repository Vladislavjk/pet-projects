package com.syberry.server.exception;

public class ProductExistsInWishlistException extends RuntimeException {
    public ProductExistsInWishlistException(String message) {
        super(message);
    }
}
