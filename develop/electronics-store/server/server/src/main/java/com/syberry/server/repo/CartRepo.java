package com.syberry.server.repo;

import com.syberry.server.entity.Cart;
import com.syberry.server.entity.User;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface CartRepo extends CrudRepository<Cart, Long> {
    Optional<Cart> findByUser(User user);
}
