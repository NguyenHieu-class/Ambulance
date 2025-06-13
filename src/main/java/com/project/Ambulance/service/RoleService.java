package com.project.Ambulance.service;

import java.util.List;

import com.project.Ambulance.model.Role;

public interface RoleService {
	List <Role> getAllRole();
	Role getRoleById(int id);
	void saveRole(Role role);
	boolean deteleRole(int id);
}
