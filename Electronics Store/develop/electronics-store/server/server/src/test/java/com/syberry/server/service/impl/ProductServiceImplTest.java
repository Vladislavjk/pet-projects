package com.syberry.server.service.impl;

import com.syberry.server.ServerApplication;
import com.syberry.server.entity.Brand;
import com.syberry.server.entity.Product;
import com.syberry.server.entity.Type;
import com.syberry.server.exception.ProductNotFoundException;
import com.syberry.server.repo.ProductRepo;
import com.syberry.server.service.BrandService;
import com.syberry.server.service.TypeService;
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

import java.util.List;
import java.util.Optional;

import static org.mockito.ArgumentMatchers.anyString;

@RunWith(MockitoJUnitRunner.class)
@AutoConfigureTestDatabase
@ActiveProfiles("test")
@TestPropertySource(locations = "classpath:application-test.properties")
@SpringBootTest(classes = ServerApplication.class)
class ProductServiceImplTest {

    private static final Long PRODUCT_ID = 1L;
    private static final Long PRODUCT_TO_ADD_ID = 2L;
    private static final Long TYPE_ID = 2L;
    private static final Long BRAND_ID = 3L;

    @Mock
    ProductRepo productRepo;

    @Mock
    BrandService brandService;

    @Mock
    TypeService typeService;

    @InjectMocks
    ProductServiceImpl productService;

    @Test
    void getById() {
        Product expected = buildExpectedProduct();
        Mockito.when(productRepo.findById(PRODUCT_ID)).thenReturn(Optional.of(expected));
        Product actual = productService.getById(PRODUCT_ID);
        Assert.assertEquals(expected, actual);
    }

    @Test
    void getByIdFail() {
        Assert.assertThrows(ProductNotFoundException.class, () -> productService.getById(PRODUCT_ID));
    }

    @Test
    void getProducts() {
        Product product = buildExpectedProduct();
        Mockito.when(productRepo.findAll()).thenReturn(List.of(product));
        Iterable<Product> actual = productService.getProducts();
        Assert.assertNotNull(actual);
    }

    @Test
    void searchProducts() {
        Product product = buildExpectedProduct();
        String name = "Apple Airpods";
        Mockito.when(productRepo.findByNameStartsWith(name)).thenReturn(List.of(product));
        Iterable<Product> actual = productService.searchProducts(name);
        Assert.assertNotNull(actual);
    }

    @Test
    void addProduct() {
        Product expected = buildProductToAdd();
        Brand brand = buildBrandToAdd();
        Type type = buildTypeToAdd();
        Mockito.when(productRepo.findByName(anyString())).thenReturn(null);
        Mockito.when(brandService.getByName(anyString())).thenReturn(brand);
        Mockito.when(typeService.getByName(anyString())).thenReturn(type);
        Mockito.when(productRepo.save(expected)).thenReturn(expected);
        Product actual = productService.addProduct(expected);
        Assert.assertEquals(expected, actual);
    }

    @Test
    void deleteById() {
        Product product = buildProductToAdd();
        Brand brand = buildBrandToAdd();
        Type type = buildTypeToAdd();
        Mockito.when(productRepo.findByName(anyString())).thenReturn(null);
        Mockito.when(brandService.getByName(anyString())).thenReturn(brand);
        Mockito.when(typeService.getByName(anyString())).thenReturn(type);
        Mockito.when(productRepo.save(product)).thenReturn(product);
        Mockito.when(productRepo.findById(PRODUCT_TO_ADD_ID)).thenReturn(Optional.of(product));
        Assert.assertEquals(productService.deleteById(PRODUCT_TO_ADD_ID), PRODUCT_TO_ADD_ID);
    }

    @Test
    void deleteByIdFail() {
        Assert.assertThrows(ProductNotFoundException.class, () -> productService.deleteById(PRODUCT_ID));
    }

    private Product buildExpectedProduct() {
        Product product = new Product();
        Brand brand = new Brand("Apple");
        Type type = new Type("headphones");
        product.setId(PRODUCT_ID);
        product.setName("Apple Airpods Pro 2");
        product.setPrice(700.0);
        type.setId(TYPE_ID);
        brand.setId(BRAND_ID);
        product.setBrand(brand);
        product.setType(type);
        return product;
    }

    private Product buildProductToAdd() {
        Product product = new Product();
        product.setId(PRODUCT_TO_ADD_ID);
        product.setName("Huawei P50 Pro");
        product.setPrice(1300.0);
        product.setBrand(buildBrandToAdd());
        product.setType(buildTypeToAdd());
        return product;
    }

    private Brand buildBrandToAdd() {
        Brand brand = new Brand("Huawei");
        brand.setId(BRAND_ID);
        return brand;
    }

    private Type buildTypeToAdd() {
        Type type = new Type("smartphone");
        type.setId(TYPE_ID);
        return type;
    }
}