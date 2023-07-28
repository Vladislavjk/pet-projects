package com.syberry.server.controller;

import com.syberry.server.service.WishlistService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequiredArgsConstructor
@RequestMapping("/api/wishlist")
public class WishlistController {
    private final WishlistService wishlistService;

    @PostMapping
    @PreAuthorize("hasRole('USER') or hasRole('MODERATOR') or hasRole('ADMIN')")
    @ResponseStatus(HttpStatus.CREATED)
    public ResponseEntity<?> addProductToCart(@RequestParam Long wishlist_id, @RequestParam Long product_id) {
        wishlistService.addProductToWishlist(wishlist_id, product_id);
        return ResponseEntity.ok("Product successfully added to wishlist");
    }

    @GetMapping("/{id}")
    @PreAuthorize("hasRole('USER') or hasRole('MODERATOR') or hasRole('ADMIN')")
    public ResponseEntity<?> getWishlist(@PathVariable Long id) {
        return ResponseEntity.ok(wishlistService.getById(id));
    }

    @DeleteMapping
    @PreAuthorize("hasRole('USER') or hasRole('MODERATOR') or hasRole('ADMIN')")
    public ResponseEntity<?> deleteProductFromWishlist(@RequestParam Long wishlist_id, @RequestParam Long product_id) {
        return ResponseEntity.ok(wishlistService.deleteProductByIdFromWishlist(wishlist_id, product_id));
    }
}
