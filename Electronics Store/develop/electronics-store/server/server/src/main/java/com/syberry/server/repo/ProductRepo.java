package com.syberry.server.repo;

import com.syberry.server.entity.Product;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProductRepo extends CrudRepository<Product, Long> {
    Product findByName(String name);
    Iterable<Product> findByNameStartsWith(String name);
}
