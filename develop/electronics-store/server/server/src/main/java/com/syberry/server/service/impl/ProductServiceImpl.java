package com.syberry.server.service.impl;

import com.syberry.server.entity.Product;
import com.syberry.server.exception.ProductAlreadyExistsException;
import com.syberry.server.exception.ProductNotFoundException;
import com.syberry.server.repo.ProductRepo;
import com.syberry.server.service.BrandService;
import com.syberry.server.service.ProductService;
import com.syberry.server.service.TypeService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ProductServiceImpl implements ProductService {
    private final ProductRepo productRepo;
    private final BrandService brandService;
    private final TypeService typeService;

    @Override
    public Product getById(Long id) {
        return productRepo.findById(id).orElseThrow(ProductNotFoundException::new);
    }

    @Override
    public Iterable<Product> getProducts() {
        return productRepo.findAll();
    }

    @Override
    public Iterable<Product> searchProducts(String name) {
        return productRepo.findByNameStartsWith(name);
    }

    @Override
    public Product addProduct(Product product) {
        if (productRepo.findByName(product.getName()) != null) {
            throw new ProductAlreadyExistsException("Product with such name exists");
        }

        product.setBrand(brandService.getByName(product.getBrand().getName()));
        product.setType(typeService.getByName(product.getType().getName()));
        return productRepo.save(product);
    }

    @Override
    public Long deleteById(Long id) {
        if (productRepo.findById(id).isEmpty()) {
            throw new ProductNotFoundException();
        }
        productRepo.deleteById(id);
        return id;
    }

    @Override
    public Product updateProduct(Product newProduct, Long id) {
        return productRepo.findById(id)
                .map(product -> {
                    product.setName(newProduct.getName());
                    product.setPrice(newProduct.getPrice());
                    product.setTitle(newProduct.getTitle());
                    product.setDescription(newProduct.getDescription());
                    product.setBrand(newProduct.getBrand());
                    product.setType(newProduct.getType());
                    return productRepo.save(product);
                })
                .orElseGet(() -> productRepo.save(newProduct));
    }
}
