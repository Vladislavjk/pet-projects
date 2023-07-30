package com.syberry.server.repo;

import com.syberry.server.entity.Type;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface TypeRepo extends CrudRepository<Type, Long> {
    Optional<Type> findByName(String name);
}
