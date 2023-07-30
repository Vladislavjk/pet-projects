package com.syberry.server.controller;

import com.syberry.server.service.CartService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequiredArgsConstructor
@RequestMapping("/api/cart")
public class CartController {
    private final CartService cartService;

    @PostMapping
    @PreAuthorize("hasRole('USER') or hasRole('MODERATOR') or hasRole('ADMIN')")
    @ResponseStatus(HttpStatus.CREATED)
    public ResponseEntity<?> addProductToCart(@RequestParam Long cart_id, @RequestParam Long product_id) {
        cartService.addProductToCart(cart_id, product_id);
        return ResponseEntity.ok("Product successfully added to cart");
    }

    @GetMapping("/{id}")
    @PreAuthorize("hasRole('USER') or hasRole('MODERATOR') or hasRole('ADMIN')")
    public ResponseEntity<?> getCart(@PathVariable Long id) {
        return ResponseEntity.ok(cartService.getById(id));
    }

    @DeleteMapping
    @PreAuthorize("hasRole('USER') or hasRole('MODERATOR') or hasRole('ADMIN')")
    public ResponseEntity<?> deleteProductFromCart(@RequestParam Long cart_id, @RequestParam Long product_id) {
        return ResponseEntity.ok(cartService.deleteProductByIdFromCart(cart_id, product_id));
    }

    @DeleteMapping("/{cart_id}")
    @PreAuthorize("hasRole('USER') or hasRole('MODERATOR') or hasRole('ADMIN')")
    public ResponseEntity<?> clearCart(@PathVariable Long cart_id) {
        return ResponseEntity.ok(cartService.clearCart(cart_id));
    }
}
