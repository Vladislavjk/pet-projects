package com.syberry.server.service;


import com.syberry.server.entity.User;

public interface UserService {
    User getById(Long id);
    Iterable<User> getUsers();
    User getByUsername(String username);
    User addUserDetails(User newUser, Long id);
}
