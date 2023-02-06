package com.syberry.server.service.impl;

import com.syberry.server.entity.Cart;
import com.syberry.server.entity.Product;
import com.syberry.server.exception.CartNotFoundException;
import com.syberry.server.exception.ProductExistsInCartException;
import com.syberry.server.repo.CartRepo;
import com.syberry.server.service.CartService;
import com.syberry.server.service.ProductService;
import com.syberry.server.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class CartServiceImpl implements CartService {
    private final CartRepo cartRepo;
    private final UserService userService;
    private final ProductService productService;

    @Override
    public Cart createCartForUser(Long user_id) {
        return cartRepo.save(new Cart(userService.getById(user_id)));
    }

    @Override
    public Cart getById(Long id) {
        return cartRepo.findById(id).orElseThrow(CartNotFoundException::new);
    }

    @Override
    public Cart getByUserId(Long user_id) {
        return cartRepo.findByUser(userService.getById(user_id)).orElseThrow(CartNotFoundException::new);
    }

    @Override
    public Cart addProductToCart(Long cart_id, Long product_id) {
        if (getById(cart_id).getProducts().stream().map(Product::getId).collect(Collectors.toList()).contains(product_id)) {
            throw new ProductExistsInCartException("Product with such id already exists in cart");
        }
        getById(cart_id).getProducts().add(productService.getById(product_id));
        getById(cart_id).setTotalPrice(getById(cart_id).getTotalPrice() + productService.getById(product_id).getPrice());
        cartRepo.save(getById(cart_id));
        return getById(cart_id);
    }

    @Override
    public Cart deleteProductByIdFromCart(Long cart_id, Long product_id) {
        getById(cart_id).getProducts().remove(
                getById(cart_id).getProducts().stream().filter(product -> product.getId() == product_id).findFirst().get()
        );
        getById(cart_id).setTotalPrice(getById(cart_id).getTotalPrice() - productService.getById(product_id).getPrice());
        cartRepo.save(getById(cart_id));
        return getById(cart_id);
    }

    @Override
    public Cart clearCart(Long cart_id) {
        getById(cart_id).getProducts().clear();
        getById(cart_id).setTotalPrice(0);
        cartRepo.save(getById(cart_id));
        return getById(cart_id);
    }
}
