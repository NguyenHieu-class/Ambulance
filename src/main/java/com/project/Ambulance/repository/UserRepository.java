package com.project.Ambulance.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.project.Ambulance.model.User;

public interface UserRepository extends JpaRepository<User, Integer> {
}
