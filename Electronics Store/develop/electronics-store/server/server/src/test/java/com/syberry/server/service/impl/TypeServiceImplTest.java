package com.syberry.server.service.impl;

import com.syberry.server.ServerApplication;
import com.syberry.server.entity.Type;
import com.syberry.server.exception.TypeNotFoundException;
import com.syberry.server.repo.TypeRepo;
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
class TypeServiceImplTest {

    private static final Long ID = 1L;
    private static final String NAME = "laptop";

    @Mock
    TypeRepo typeRepo;

    @InjectMocks
    TypeServiceImpl typeService;

    @Test
    void getById() {
        Type type = buildExpectedType();
        when(typeRepo.findById(ID)).thenReturn(Optional.of(type));
        Type actual = typeService.getById(ID);
        Assert.assertNotNull(actual);
    }

    @Test
    void getByIdFail() {
        Assert.assertThrows(TypeNotFoundException.class, () -> typeService.getById(ID));
    }

    @Test
    void getTypes() {
        Type type = buildExpectedType();
        when(typeRepo.findAll()).thenReturn(List.of(type));
        Iterable<Type> actual = typeService.getTypes();
        Assert.assertNotNull(actual);
    }

    @Test
    void getByName() {
        Type type = buildExpectedType();
        when(typeRepo.findByName(NAME)).thenReturn(Optional.of(type));
        Type actual = typeService.getByName(NAME);
        Assert.assertNotNull(actual);
    }

    @Test
    void getByNameFail() {
        Assert.assertThrows(TypeNotFoundException.class, () -> typeService.getByName(NAME));
    }

    @Test
    void addType() {
        Type type = buildExpectedType();
        when(typeRepo.save(type)).thenReturn(type);
        Type actual = typeService.addType(type);
        Assert.assertEquals(actual, type);
    }

    @Test
    void deleteById() {
        Type type = buildExpectedType();
        when(typeRepo.findById(ID)).thenReturn(Optional.of(type));
        typeService.deleteById(ID);
        verify(typeRepo, times(1)).deleteById(ID);
    }

    @Test
    void deleteByIdFail() {
        Assert.assertThrows(TypeNotFoundException.class, () -> typeService.deleteById(ID));
    }

    @Test
    void updateType() {
        Type type = buildExpectedType();
        Type newType = buildExpectedType();
        newType.setName("powerbank");
        when(typeRepo.findById(ID)).thenReturn(Optional.of(type));
        when(typeRepo.save(type)).thenReturn(type);
        Type actual = typeService.updateType(newType, ID);
        Assert.assertEquals(actual.getName(), newType.getName());
    }

    private Type buildExpectedType() {
        Type type = new Type();
        type.setId(ID);
        type.setName(NAME);
        return type;
    }
}