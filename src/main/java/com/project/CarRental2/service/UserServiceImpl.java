package com.project.Ambulance.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.project.Ambulance.model.User;
import com.project.Ambulance.repository.UserRepository;

@Service
public class UserServiceImpl implements UserService /*, UserDetailsService */ {

    @Autowired
    private UserRepository repo;

    @Override
    public void saveUser(User user) {
        repo.save(user);
    }

    @Override
    public List<User> getAllUser() {
        return repo.findAll();
    }

    @Override
    public User getUser(int id) {
        return repo.findById(id).orElse(null);
    }

    @Override
    public void deleteUser(int id) {
        repo.deleteById(id);
    }

    @Override
    public Optional<User> findByUsername(String username) {
        return Optional.ofNullable(repo.findByUsername(username));
    }
}
