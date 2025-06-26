package com.project.Ambulance.config;

import com.project.Ambulance.model.Role;
import com.project.Ambulance.model.User;
import com.project.Ambulance.service.RoleService;
import com.project.Ambulance.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.time.LocalDate;

@Component
public class DataInitializer implements CommandLineRunner {

    @Autowired
    private RoleService roleService;

    @Autowired
    private UserService userService;

    @Override
    public void run(String... args) {
        if (!roleService.existsByRoleName("ADMIN")) {
            Role role = new Role();
            role.setRoleName("ADMIN");
            role.setCreateDate(LocalDate.now());
            role.setUpdateDate(LocalDate.now());
            roleService.saveRole(role);
        }

        Role adminRole = roleService.getRoleByRoleName("ADMIN");

        if (!userService.existsByUsername("admin")) {
            User admin = new User();
            admin.setUsername("admin");
            admin.setPassword("admin123");
            admin.setCreateDate(LocalDate.now());
            admin.setUpdateDate(LocalDate.now());
            admin.setRole(adminRole);
            userService.saveUser(admin);
        }
    }
}
