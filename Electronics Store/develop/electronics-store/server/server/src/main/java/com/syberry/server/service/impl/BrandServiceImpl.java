package com.syberry.server.service.impl;

import com.syberry.server.entity.Brand;
import com.syberry.server.exception.BrandAlreadyExistsException;
import com.syberry.server.exception.BrandNotFoundException;
import com.syberry.server.repo.BrandRepo;
import com.syberry.server.service.BrandService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class BrandServiceImpl implements BrandService {

    private final BrandRepo brandRepo;

    @Override
    public Brand addBrand(Brand brand) {
        if (brandRepo.findByName(brand.getName()).isPresent()) {
            throw new BrandAlreadyExistsException("Brand with such name exists");
        }
        return brandRepo.save(brand);
    }

    @Override
    public Brand getById(Long id) {
        return brandRepo.findById(id).orElseThrow(BrandNotFoundException::new);
    }

    @Override
    public Brand getByName(String name) {
        return brandRepo.findByName(name).orElseThrow(BrandNotFoundException::new);
    }

    @Override
    public Iterable<Brand> getBrands() {
        return brandRepo.findAll();
    }

    @Override
    public Long deleteById(Long id) {
        if (brandRepo.findById(id).isEmpty()) {
            throw new BrandNotFoundException();
        }
        brandRepo.deleteById(id);
        return id;
    }

    @Override
    public Brand updateBrand(Brand newBrand, Long id) {
        return brandRepo.findById(id)
                .map(brand -> {
                    brand.setName(newBrand.getName());
                    return brandRepo.save(brand);
                })
                .orElseGet(() -> brandRepo.save(newBrand));
    }
}
