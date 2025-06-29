package com.project.Ambulance.service;

import com.project.Ambulance.model.User;
import com.project.Ambulance.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepository userRepository;

    @Override
    public List<User> getAllUsers() {
        return userRepository.findAllByOrderByNameDisplayAsc();
    }

    @Override
    public User getUserById(int id) {
        return userRepository.findById(id).orElse(null);
    }

    @Override
    public User getUserByUsername(String username) {
        return userRepository.findByUsername(username);
    }

    @Override
    public List<User> searchUsersByName(String name) {
        return userRepository.findByNameDisplayContainingIgnoreCaseOrderByNameDisplayAsc(name);
    }

    @Override
    public List<User> searchUsersByPhone(String phone) {
        return userRepository.findByPhoneContainingIgnoreCaseOrderByNameDisplayAsc(phone);
    }

    @Override
    public List<User> searchUsersByEmail(String email) {
        return userRepository.findByEmailContainingIgnoreCaseOrderByNameDisplayAsc(email);
    }

    @Override
    public User saveUser(User user) {
        return userRepository.save(user);
    }

    @Override
    public void deleteUser(int id) {
        userRepository.deleteById(id);
    }

    @Override
    public long count() {
        return userRepository.count();
    }
}
