package com.project.Ambulance.service;

import com.project.Ambulance.model.User;

import java.util.List;

public interface UserService {

    List<User> getAllUsers();

    User getUserById(int id);

    User getUserByUsername(String username);

    List<User> searchUsersByName(String name);

    List<User> searchUsersByPhone(String phone);

    List<User> searchUsersByEmail(String email);

    User saveUser(User user);

    void deleteUser(int id);

    long count();
}
