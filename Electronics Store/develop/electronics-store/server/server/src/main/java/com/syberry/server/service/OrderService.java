package com.syberry.server.service;

import com.syberry.server.entity.Order;

public interface OrderService {
    Order getById(Long id);
    Iterable<Order> getOrders();
    Iterable<Order> getUserOrders(Long user_id);
    Order addOrder(Order order);
    Long deleteById(Long id);
    Order updateOrder(Order newOrder, Long id);
}
