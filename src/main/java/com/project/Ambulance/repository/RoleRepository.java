package com.project.Ambulance.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.project.Ambulance.model.Role;

@Repository
public interface RoleRepository  extends JpaRepository<Role, Integer>{

	long count();
}
