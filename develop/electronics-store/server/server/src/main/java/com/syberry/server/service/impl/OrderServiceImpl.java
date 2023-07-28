package com.syberry.server.service.impl;

import com.syberry.server.entity.Order;
import com.syberry.server.entity.Product;
import com.syberry.server.exception.OrderNotFoundException;
import com.syberry.server.repo.OrderRepo;
import com.syberry.server.service.OrderService;
import com.syberry.server.service.ProductService;
import com.syberry.server.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class OrderServiceImpl implements OrderService {
    private final OrderRepo orderRepo;
    private final UserService userService;
    private final ProductService productService;


    @Override
    public Order getById(Long id) {
        return orderRepo.findById(id).orElseThrow(OrderNotFoundException::new);
    }

    @Override
    public Iterable<Order> getOrders() {
        return orderRepo.findAll();
    }

    @Override
    public Iterable<Order> getUserOrders(Long user_id) {
        return orderRepo.findByUser(userService.getById(user_id));
    }

    @Override
    public Order addOrder(Order order) {
        initOrder(order);
        return orderRepo.save(order);
    }

    private void initOrder(Order order) {
        List<Product> products = new ArrayList<>();
        for (Product product : order.getProducts()) {
            products.add(productService.getById(product.getId()));
        }

        order.setUser(userService.getById(order.getUser().getId()));
        order.setProducts(products);
    }

    @Override
    public Long deleteById(Long id) {
        if (orderRepo.findById(id).isEmpty()) {
            throw new OrderNotFoundException();
        }
        getById(id).setProducts(null);
        getById(id).setUser(null);
        orderRepo.save(getById(id));
        orderRepo.deleteById(id);
        return id;
    }

    @Override
    public Order updateOrder(Order newOrder, Long id) {
        initOrder(newOrder);

        return orderRepo.findById(id)
                .map(order -> {
                    order.setUser(newOrder.getUser());
                    order.setProducts(newOrder.getProducts());
                    order.setPrice(newOrder.getPrice());
                    return orderRepo.save(order);
                })
                .orElseGet(() -> orderRepo.save(newOrder));
    }
}
