package com.syberry.server.service.impl;

import com.syberry.server.ServerApplication;
import com.syberry.server.entity.*;
import com.syberry.server.exception.CartNotFoundException;
import com.syberry.server.repo.CartRepo;
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
import java.util.Optional;

@RunWith(MockitoJUnitRunner.class)
@AutoConfigureTestDatabase
@ActiveProfiles("test")
@TestPropertySource(locations = "classpath:application-test.properties")
@SpringBootTest(classes = ServerApplication.class)
class CartServiceImplTest {

    private static final Long CART_ID = 1L;
    private static final Long USER_ID = 2L;
    private static final Long PRODUCT_ID = 3L;
    private static final Long PRODUCT_TO_ADD_ID = 4L;
    private static final Long TYPE_ID = 4L;
    private static final Long BRAND_ID = 5L;

    @Mock
    UserService userService;

    @Mock
    ProductService productService;

    @Mock
    CartRepo cartRepo;

    @InjectMocks
    CartServiceImpl cartService;

    @Test
    void createCartForUser() {
        User user = new User("admin", "some-pass");
        Cart cart = new Cart(user);
        Mockito.when(userService.getById(user.getId())).thenReturn(user);
        Mockito.when(cartRepo.save(new Cart(userService.getById(user.getId())))).thenReturn(cart);
        Cart actual = cartService.createCartForUser(user.getId());
        Assert.assertEquals(cart, actual);
    }

    @Test
    void getById() {
        Cart cart = buildExpectedCart();
        Mockito.when(cartRepo.findById(CART_ID)).thenReturn(Optional.of(cart));
        Cart actual = cartService.getById(CART_ID);
        Assert.assertNotNull(actual);
    }

    @Test
    void getByIdFail() {
        Assert.assertThrows(CartNotFoundException.class, () -> cartService.getById(CART_ID));
    }

    @Test
    void getByUserId() {
        User user = buildExpectedUser();
        Cart cart = buildExpectedCart();
        Mockito.when(userService.getById(USER_ID)).thenReturn(user);
        Mockito.when(cartRepo.findByUser(user)).thenReturn(Optional.of(cart));
        Cart actual = cartService.getByUserId(USER_ID);
        Assert.assertNotNull(actual);
    }

    @Test
    void getByUserIdFail() {
        Assert.assertThrows(CartNotFoundException.class, () -> cartService.getByUserId(USER_ID));
    }

    @Test
    void addProductToCart() {
        Cart cart = buildExpectedCart();
        Mockito.when(cartRepo.save(cart)).thenReturn(cart);
        Mockito.when(cartRepo.findById(CART_ID)).thenReturn(Optional.of(cart));
        Mockito.when(productService.getById(PRODUCT_TO_ADD_ID)).thenReturn(buildProductToAdd());
        Cart actual = cartService.addProductToCart(CART_ID, PRODUCT_TO_ADD_ID);
        Assert.assertTrue(actual.getProducts().size() == 2);
    }

    @Test
    void deleteProductByIdFromCart() {
        Cart expected = buildExpectedCart();
        Mockito.when(cartRepo.save(expected)).thenReturn(expected);
        Mockito.when(cartRepo.findById(expected.getId())).thenReturn(Optional.of(expected));
        Mockito.when(productService.getById(PRODUCT_ID)).thenReturn(buildExpectedProduct());
        Cart actual = cartService.deleteProductByIdFromCart(CART_ID, PRODUCT_ID);
        Assert.assertTrue(actual.getProducts().isEmpty());
    }

    @Test
    void clearCart() {
        Cart expected = buildExpectedCart();
        Mockito.when(cartRepo.save(expected)).thenReturn(expected);
        Mockito.when(cartRepo.findById(CART_ID)).thenReturn(Optional.of(expected));
        Mockito.when(productService.getById(PRODUCT_ID)).thenReturn(buildExpectedProduct());
        Cart actual = cartService.clearCart(CART_ID);
        Assert.assertTrue(actual.getProducts().isEmpty());
    }

    private Cart buildExpectedCart() {
        Cart cart = new Cart(buildExpectedUser());
        cart.setId(CART_ID);
        cart.setProducts(new ArrayList<>());
        cart.getProducts().add(buildExpectedProduct());
        return cart;
    }

    private Product buildExpectedProduct() {
        Product product = new Product();
        Brand brand = new Brand("Lenovo");
        Type type = new Type("laptop");
        product.setId(PRODUCT_ID);
        product.setName("LENOVO LEGION Y510");
        product.setPrice(950.0);
        type.setId(TYPE_ID);
        brand.setId(BRAND_ID);
        product.setBrand(brand);
        product.setType(type);
        return product;
    }

    private Product buildProductToAdd() {
        Product product = new Product();
        Brand brand = new Brand("Huawei");
        Type type = new Type("laptop");
        product.setId(PRODUCT_TO_ADD_ID);
        product.setName("Huawei IdeaPad 12");
        product.setPrice(1200.0);
        type.setId(TYPE_ID);
        brand.setId(BRAND_ID);
        product.setBrand(brand);
        product.setType(type);
        return product;
    }

    private User buildExpectedUser() {
        User user = new User("user", "some-pass");
        user.setId(USER_ID);
        return user;
    }
}