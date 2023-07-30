package com.syberry.server.service;

import com.syberry.server.entity.Wishlist;
import com.syberry.server.exception.ProductExistsInWishlistException;

public interface WishlistService {
    Wishlist createWishlistForUser(Long user_id);
    Wishlist getById(Long id);
    Wishlist getByUserId(Long user_id);
    Wishlist addProductToWishlist(Long wishlist_id, Long product_id);
    Wishlist deleteProductByIdFromWishlist(Long wishlist_id, Long product_id);
}
