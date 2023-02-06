package com.syberry.server.service.impl;

import com.syberry.server.entity.User;
import com.syberry.server.exception.UsernameNotFoundException;
import com.syberry.server.repo.UserRepo;
import com.syberry.server.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    private final UserRepo userRepo;

    @Override
    public User getById(Long id) {
        return userRepo.findById(id).orElseThrow(UsernameNotFoundException::new);
    }

    @Override
    public Iterable<User> getUsers() {
        return userRepo.findAll();
    }

    @Override
    public User getByUsername(String username) {
        return userRepo.findByUsername(username).orElseThrow(UsernameNotFoundException::new);
    }

    @Override
    public User addUserDetails(User newUser, Long id) {
        return userRepo.findById(id)
                .map(user -> {
                    user.setFullName(newUser.getFullName());
                    user.setCountry(newUser.getCountry());
                    user.setCity(newUser.getCity());
                    user.setStreet(newUser.getStreet());
                    user.setPhone(newUser.getPhone());
                    return userRepo.save(user);
                })
                .orElseGet(() -> userRepo.save(newUser));
    }
}
