package com.syberry.server.service.impl;

import com.syberry.server.entity.Product;
import com.syberry.server.entity.Wishlist;
import com.syberry.server.exception.ProductExistsInWishlistException;
import com.syberry.server.exception.WishlistNotFoundException;
import com.syberry.server.repo.WishlistRepo;
import com.syberry.server.service.ProductService;
import com.syberry.server.service.UserService;
import com.syberry.server.service.WishlistService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class WishlistServiceImpl implements WishlistService {
    private final WishlistRepo wishlistRepo;
    private final UserService userService;
    private final ProductService productService;

    @Override
    public Wishlist createWishlistForUser(Long user_id) {
        return wishlistRepo.save(new Wishlist(userService.getById(user_id)));
    }

    @Override
    public Wishlist getById(Long id) {
        return wishlistRepo.findById(id).orElseThrow(WishlistNotFoundException::new);
    }

    @Override
    public Wishlist getByUserId(Long user_id) {
        return wishlistRepo.findByUser(userService.getById(user_id)).orElseThrow(WishlistNotFoundException::new);
    }

    @Override
    public Wishlist addProductToWishlist(Long wishlist_id, Long product_id) {
        if (getById(wishlist_id).getProducts().stream().map(Product::getId).collect(Collectors.toList()).contains(product_id)) {
            throw new ProductExistsInWishlistException("Product with such id already exists in wishlist");
        }
        getById(wishlist_id).getProducts().add(productService.getById(product_id));
        wishlistRepo.save(getById(wishlist_id));
        return getById(wishlist_id);
    }

    @Override
    public Wishlist deleteProductByIdFromWishlist(Long wishlist_id, Long product_id) {
        getById(wishlist_id).getProducts().remove(
                getById(wishlist_id).getProducts().stream().filter(product -> product.getId() == product_id).findFirst().get()
        );
        wishlistRepo.save(getById(wishlist_id));
        return getById(wishlist_id);
    }
}
