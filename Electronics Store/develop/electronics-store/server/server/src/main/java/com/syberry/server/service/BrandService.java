package com.syberry.server.service;

import com.syberry.server.entity.Brand;
import com.syberry.server.exception.BrandAlreadyExistsException;

public interface BrandService {
    Brand getById(Long id);
    Brand getByName(String name);
    Iterable<Brand> getBrands();
    Brand addBrand(Brand brand);
    Long deleteById(Long id);
    Brand updateBrand(Brand newBrand, Long id);
}
