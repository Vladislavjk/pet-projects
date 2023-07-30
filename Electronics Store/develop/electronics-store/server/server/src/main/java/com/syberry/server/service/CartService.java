package com.syberry.server.service;

import com.syberry.server.entity.Cart;
import com.syberry.server.exception.ProductExistsInCartException;

public interface CartService {
    Cart createCartForUser(Long user_id);
    Cart getById(Long id);
    Cart getByUserId(Long user_id);
    Cart addProductToCart(Long cart_id, Long product_id);
    Cart deleteProductByIdFromCart(Long cart_id, Long product_id);
    Cart clearCart(Long cart_id);
}
