package com.project.Ambulance.service;

import com.project.Ambulance.model.Role;

import java.util.List;

public interface RoleService {

    List<Role> getAllRoles();

    Role getRoleById(int id);

    Role saveRole(Role role);

    void deleteRole(int id);

    Role getRoleByName(String name);

    List<Role> searchRolesByKeyword(String keyword);

    long count();
}
