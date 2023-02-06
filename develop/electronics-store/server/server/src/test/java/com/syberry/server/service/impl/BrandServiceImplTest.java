package com.syberry.server.service.impl;

import com.syberry.server.ServerApplication;
import com.syberry.server.entity.Brand;
import com.syberry.server.exception.BrandNotFoundException;
import com.syberry.server.repo.BrandRepo;
import org.junit.Assert;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.TestPropertySource;

import java.util.List;
import java.util.Optional;

import static org.mockito.Mockito.*;

@RunWith(MockitoJUnitRunner.class)
@AutoConfigureTestDatabase
@ActiveProfiles("test")
@TestPropertySource(locations = "classpath:application-test.properties")
@SpringBootTest(classes = ServerApplication.class)
class BrandServiceImplTest {

    private static final Long ID = 1L;
    private static final String NAME = "Lenovo";

    @Mock
    BrandRepo brandRepo;

    @InjectMocks
    BrandServiceImpl brandService;

    @Test
    void addBrand() {
        Brand expected = buildExpectedBrand();
        when(brandRepo.save(expected)).thenReturn(expected);
        Brand actual = brandService.addBrand(expected);
        Assert.assertEquals(expected, actual);
    }

    @Test
    void getById() {
        Brand expected = buildExpectedBrand();
        when(brandRepo.findById(ID)).thenReturn(Optional.of(expected));
        Brand actual = brandService.getById(ID);
        Assert.assertEquals(expected, actual);
    }

    @Test
    void getByIdFail() {
        when(brandRepo.findById(ID)).thenReturn(Optional.empty());
        Assert.assertThrows(BrandNotFoundException.class, () -> brandService.getById(ID));
    }

    @Test
    void getByName() {
        Brand expected = buildExpectedBrand();
        when(brandRepo.findByName(NAME)).thenReturn(Optional.of(expected));
        Brand actual = brandService.getByName(NAME);
        Assert.assertEquals(expected, actual);
    }

    @Test
    void getByNameFail() {
        Assert.assertThrows(BrandNotFoundException.class, () -> brandService.getByName(NAME));
    }

    @Test
    void getBrands() {
        Brand brand = buildExpectedBrand();
        when(brandRepo.findAll()).thenReturn(List.of(brand));
        Iterable<Brand> actual = brandService.getBrands();
        Assert.assertNotNull(actual);
    }

    @Test
    void deleteById() {
        Brand expected = buildExpectedBrand();
        when(brandRepo.findById(ID)).thenReturn(Optional.of(expected));
        brandService.deleteById(ID);
        verify(brandRepo, times(1)).deleteById(ID);
    }

    @Test
    void deleteByIdFail() {
        Assert.assertThrows(BrandNotFoundException.class, () -> brandService.deleteById(ID));
    }

    @Test
    void updateBrand() {
        Brand brand = buildExpectedBrand();
        Brand newBrand = buildExpectedBrand();
        newBrand.setName("Philips");
        when(brandRepo.findById(ID)).thenReturn(Optional.of(brand));
        when(brandRepo.save(brand)).thenReturn(brand);
        Brand actual = brandService.updateBrand(newBrand, ID);
        Assert.assertEquals(actual.getName(), newBrand.getName());
    }

    private Brand buildExpectedBrand() {
        Brand brand = new Brand();
        brand.setId(ID);
        brand.setName(NAME);
        return brand;
    }
}