package com.syberry.server.repo;

import com.syberry.server.entity.Order;
import com.syberry.server.entity.User;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface OrderRepo extends CrudRepository<Order, Long> {
    Iterable<Order> findByUser(User user);
}
