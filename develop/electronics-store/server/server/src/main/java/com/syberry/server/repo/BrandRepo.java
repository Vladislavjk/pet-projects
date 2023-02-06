package com.syberry.server.repo;

import com.syberry.server.entity.Brand;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface BrandRepo extends CrudRepository<Brand, Long> {
    Optional<Brand> findByName(String name);
}
