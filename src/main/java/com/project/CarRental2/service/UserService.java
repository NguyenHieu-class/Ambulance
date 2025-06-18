package com.project.Ambulance.service;

import java.util.List;
import java.util.Optional;

import com.project.Ambulance.model.User;

public interface UserService {
    void saveUser(User user);
    List<User> getAllUser();
    User getUser(int id);
    void deleteUser(int id);
    Optional<User> findByUsername(String username);
}
