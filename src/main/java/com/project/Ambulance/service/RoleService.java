package com.project.Ambulance.service;

import com.project.Ambulance.model.Role;

import java.util.List;

public interface RoleService {

    List<Role> getAllRole();

    Role getRoleById(Long id);

    void saveRole(Role role);

    void deleteRole(Long id);

    long countRole();

    Role getRoleByRoleName(String roleName);

    boolean existsByRoleName(String roleName);
}
