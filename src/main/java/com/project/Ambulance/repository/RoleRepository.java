package com.project.Ambulance.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.project.Ambulance.model.Role;

public interface RoleRepository extends JpaRepository<Role, Integer> {
}
