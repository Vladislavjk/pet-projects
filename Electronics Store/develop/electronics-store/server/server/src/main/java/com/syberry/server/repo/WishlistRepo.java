package com.syberry.server.repo;

import com.syberry.server.entity.User;
import com.syberry.server.entity.Wishlist;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface WishlistRepo extends CrudRepository<Wishlist, Long> {
    Optional<Wishlist> findByUser(User user);
}
