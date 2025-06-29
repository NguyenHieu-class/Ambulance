package com.project.Ambulance.controller;

import com.project.Ambulance.model.User;
import com.project.Ambulance.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
public class UserController {

    @Autowired
    private UserService userService;

    // === ADMIN APIs ===

    @GetMapping("/admin/users")
    public ResponseEntity<List<User>> getAllUsers() {
        List<User> users = userService.getAllUsers();
        return ResponseEntity.ok(users);
    }

    @GetMapping("/admin/user/{id}")
    public ResponseEntity<User> getUserById(@PathVariable int id) {
        User user = userService.getUserById(id);
        if (user == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(user);
    }

    @PostMapping("/admin/users")
    public ResponseEntity<User> createUser(@RequestBody User user) {
        User saved = userService.saveUser(user);
        return ResponseEntity.ok(saved);
    }

    @PutMapping("/admin/user/{id}")
    public ResponseEntity<User> updateUser(@PathVariable int id, @RequestBody User user) {
        user.setIdUser(id);
        User updated = userService.saveUser(user);
        return ResponseEntity.ok(updated);
    }

    @DeleteMapping("/admin/user/{id}")
    public ResponseEntity<Void> deleteUser(@PathVariable int id) {
        userService.deleteUser(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/admin/users/search")
    public ResponseEntity<List<User>> searchUsers(@RequestParam(required = false) String name,
                                                  @RequestParam(required = false) String phone,
                                                  @RequestParam(required = false) String email) {
        List<User> users;
        if (name != null) {
            users = userService.searchUsersByName(name);
        } else if (phone != null) {
            users = userService.searchUsersByPhone(phone);
        } else if (email != null) {
            users = userService.searchUsersByEmail(email);
        } else {
            users = userService.getAllUsers();
        }
        return ResponseEntity.ok(users);
    }
}

