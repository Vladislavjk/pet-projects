package com.syberry.server.service;

import com.syberry.server.entity.Product;
import com.syberry.server.exception.ProductAlreadyExistsException;

public interface ProductService {
    Product getById(Long id);
    Iterable<Product> getProducts();
    Iterable<Product> searchProducts(String name);
    Product addProduct(Product product);
    Long deleteById(Long id);
    Product updateProduct(Product newProduct, Long id);
}
