package com.syberry.server.service.impl;

import com.syberry.server.ServerApplication;
import com.syberry.server.entity.*;
import com.syberry.server.repo.OrderRepo;
import com.syberry.server.service.ProductService;
import com.syberry.server.service.UserService;
import org.junit.Assert;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.TestPropertySource;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@RunWith(MockitoJUnitRunner.class)
@AutoConfigureTestDatabase
@ActiveProfiles("test")
@TestPropertySource(locations = "classpath:application-test.properties")
@SpringBootTest(classes = ServerApplication.class)
class OrderServiceImplTest {

    private static final Long ORDER_ID = 1L;
    private static final Long ORDER_TO_ADD_ID = 2L;
    private static final Long USER_ID = 2L;
    private static final Long PRODUCT_ID = 3L;
    private static final Long TYPE_ID = 4L;
    private static final Long BRAND_ID = 5L;

    @Mock
    UserService userService;

    @Mock
    ProductService productService;

    @Mock
    OrderRepo orderRepo;

    @InjectMocks
    OrderServiceImpl orderService;

    @Test
    void getById() {
        Order expected = buildExpectedOrder();
        Mockito.when(orderRepo.findById(ORDER_ID)).thenReturn(Optional.of(expected));
        Order actual = orderService.getById(ORDER_ID);
        Assert.assertEquals(expected, actual);
    }

    @Test
    void getOrders() {
        Order order = buildExpectedOrder();
        Mockito.when(orderRepo.findAll()).thenReturn(List.of(order));
        List<Order> actual = (List<Order>) orderService.getOrders();
        Assert.assertFalse(actual.isEmpty());
    }

    @Test
    void getUserOrders() {
        User user = buildExpectedUser();
        Order order = buildExpectedOrder();
        Mockito.when(userService.getById(USER_ID)).thenReturn(user);
        Mockito.when(orderRepo.findByUser(user)).thenReturn(List.of(order));
        List<Order> actual = (List<Order>) orderService.getUserOrders(USER_ID);
        Assert.assertFalse(actual.isEmpty());
    }

    @Test
    void addOrder() {
        Order order = buildOrderToAdd();
        User user = buildExpectedUser();
        Product product = buildExpectedProduct();
        Mockito.when(productService.getById(PRODUCT_ID)).thenReturn(product);
        Mockito.when(userService.getById(USER_ID)).thenReturn(user);
        Mockito.when(orderRepo.save(order)).thenReturn(order);
        Order actual = orderService.addOrder(order);
        Assert.assertEquals(order, actual);
    }

    @Test
    void deleteById() {
        Order order = buildExpectedOrder();
        Mockito.when(orderRepo.findById(ORDER_ID)).thenReturn(Optional.of(order));
        Order actual = orderService.getById(ORDER_ID);
        Mockito.when(orderRepo.save(actual)).thenReturn(actual);
        orderService.deleteById(ORDER_ID);
        Assert.assertTrue(actual.getUser() == null & actual.getProducts() == null);
    }

    private Order buildExpectedOrder() {
        Order order = new Order();
        order.setId(ORDER_ID);
        order.setUser(buildExpectedUser());
        order.setProducts(new ArrayList<>());
        order.getProducts().add(buildExpectedProduct());
        return order;
    }

    private Order buildOrderToAdd() {
        Order order = new Order();
        order.setId(ORDER_TO_ADD_ID);
        order.setUser(buildExpectedUser());
        order.setProducts(new ArrayList<>());
        order.getProducts().add(buildExpectedProduct());
        return order;
    }

    private Product buildExpectedProduct() {
        Product product = new Product();
        Brand brand = new Brand("Apple");
        Type type = new Type("laptop");
        product.setId(PRODUCT_ID);
        product.setName("Macbook Pro");
        product.setPrice(2000.0);
        type.setId(TYPE_ID);
        brand.setId(BRAND_ID);
        product.setBrand(brand);
        product.setType(type);
        return product;
    }

    private User buildExpectedUser() {
        User user = new User("moderator", "some-pass");
        user.setId(USER_ID);
        return user;
    }
}