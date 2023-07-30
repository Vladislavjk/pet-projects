package com.syberry.server.service.impl;

import com.syberry.server.ServerApplication;
import com.syberry.server.entity.User;
import com.syberry.server.repo.UserRepo;
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

@RunWith(MockitoJUnitRunner.class)
@AutoConfigureTestDatabase
@ActiveProfiles("test")
@TestPropertySource(locations = "classpath:application-test.properties")
@SpringBootTest(classes = ServerApplication.class)
class UserServiceImplTest {

    private static final Long ID = 1L;
    private static final String USERNAME = "admin";
    private static final String PASSWORD = "12345678";

    @Mock
    UserRepo userRepo;

    @InjectMocks
    UserServiceImpl userService;

    @Test
    void getById() {
        User expected = buildExpectedUser();
        Mockito.when(userRepo.findById(ID)).thenReturn(Optional.of(expected));
        User actual = userService.getById(ID);
        Assert.assertEquals(expected, actual);
    }

    @Test
    void getUsers() {
        User user = buildExpectedUser();
        Mockito.when(userRepo.findAll()).thenReturn(List.of(user));
        List<User> actual = (List<User>) userService.getUsers();
        Assert.assertFalse(actual.isEmpty());
    }

    @Test
    void getByUsername() {
        User expected = buildExpectedUser();
        Mockito.when(userRepo.findByUsername(USERNAME)).thenReturn(Optional.of(expected));
        User actual = userService.getByUsername(USERNAME);
        Assert.assertEquals(expected, actual);
    }

    @Test
    void addUserDetails() {
        User user = buildExpectedUser();
        User expected = buildExpectedUserWithDetails();
        Mockito.when(userRepo.findById(ID)).thenReturn(Optional.of(user));
        Mockito.when(userRepo.save(Mockito.any(User.class))).thenReturn(user);
        User actual = userService.addUserDetails(expected, ID);
        Assert.assertEquals(expected, actual);
    }

    private User buildExpectedUser() {
        User user = new User(USERNAME, PASSWORD);
        user.setId(ID);
        return user;
    }

    private User buildExpectedUserWithDetails() {
        User user = new User(USERNAME, PASSWORD);
        user.setId(ID);
        user.setFullName("vladislav");
        user.setStreet("Pushkina");
        user.setPhone("+375291732934");
        user.setCountry("Belarus");
        user.setCity("Minsk");
        return user;
    }
}