package com.project.Ambulance.service;

import com.project.Ambulance.model.User;

import java.util.List;

public interface UserService {

    List<User> getAllUser();

    User getUserById(Long id);

    void saveUser(User user);

    void deleteUser(Long id);

    long countUser();

    User getUserByUsername(String username);

    boolean existsByUsername(String username);
}
