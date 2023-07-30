package com.syberry.server.service.impl;

import com.syberry.server.ServerApplication;
import com.syberry.server.entity.*;
import com.syberry.server.exception.WishlistNotFoundException;
import com.syberry.server.repo.WishlistRepo;
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

import static org.mockito.ArgumentMatchers.any;

@RunWith(MockitoJUnitRunner.class)
@AutoConfigureTestDatabase
@ActiveProfiles("test")
@TestPropertySource(locations = "classpath:application-test.properties")
@SpringBootTest(classes = ServerApplication.class)
class WishlistServiceImplTest {

    private static final Long WISHLIST_ID = 1L;
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
    WishlistRepo wishlistRepo;

    @InjectMocks
    WishlistServiceImpl wishlistService;

    @Test
    void createWishlistForUser() {
        User user = buildExpectedUser();
        Wishlist expected = buildExpectedWishlist();
        Mockito.when(userService.getById(USER_ID)).thenReturn(user);
        Mockito.when(wishlistRepo.save(any(Wishlist.class))).thenReturn(expected);
        Wishlist actual = wishlistService.createWishlistForUser(USER_ID);
        Assert.assertEquals(expected, actual);
    }

    @Test
    void getById() {
        Wishlist expected = buildExpectedWishlist();
        Mockito.when(wishlistRepo.findById(WISHLIST_ID)).thenReturn(Optional.of(expected));
        Wishlist actual = wishlistService.getById(WISHLIST_ID);
        Assert.assertEquals(expected, actual);
    }

    @Test
    void getByIdFail() {
        Assert.assertThrows(WishlistNotFoundException.class, () -> wishlistService.getById(WISHLIST_ID));
    }

    @Test
    void getByUserId() {
        User user = buildExpectedUser();
        Wishlist wishlist = buildExpectedWishlist();
        Mockito.when(userService.getById(USER_ID)).thenReturn(user);
        Mockito.when(wishlistRepo.findByUser(user)).thenReturn(Optional.of(wishlist));
        Wishlist actual = wishlistService.getByUserId(USER_ID);
        Assert.assertNotNull(actual);
    }

    @Test
    void getByUserIdFail() {
        Assert.assertThrows(WishlistNotFoundException.class, () -> wishlistService.getByUserId(USER_ID));
    }

    @Test
    void addProductToWishlist() {
        Wishlist wishlist = buildExpectedWishlist();
        Product product = buildProductToAdd();
        Mockito.when(wishlistRepo.save(wishlist)).thenReturn(wishlist);
        Mockito.when(wishlistRepo.findById(WISHLIST_ID)).thenReturn(Optional.of(wishlist));
        Mockito.when(productService.getById(PRODUCT_TO_ADD_ID)).thenReturn(product);
        Wishlist actual = wishlistService.addProductToWishlist(WISHLIST_ID, PRODUCT_TO_ADD_ID);
        Assert.assertFalse(actual.getProducts().isEmpty());
    }

    @Test
    void deleteProductByIdFromWishlist() {
        Wishlist wishlist = buildExpectedWishlist();
        Product product = buildExpectedProduct();
        Mockito.when(wishlistRepo.save(wishlist)).thenReturn(wishlist);
        Mockito.when(wishlistRepo.findById(WISHLIST_ID)).thenReturn(Optional.of(wishlist));
        Mockito.when(productService.getById(PRODUCT_ID)).thenReturn(product);
        Wishlist actual = wishlistService.deleteProductByIdFromWishlist(WISHLIST_ID, PRODUCT_ID);
        Assert.assertTrue(actual.getProducts().isEmpty());
    }

    private Wishlist buildExpectedWishlist() {
        Wishlist wishlist = new Wishlist();
        wishlist.setId(WISHLIST_ID);
        wishlist.setUser(buildExpectedUser());
        wishlist.setProducts(new ArrayList<>());
        wishlist.getProducts().add(buildExpectedProduct());
        return wishlist;
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