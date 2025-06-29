package com.project.Ambulance.service;

import com.project.Ambulance.model.Role;
import com.project.Ambulance.repository.RoleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RoleServiceImpl implements RoleService {

    @Autowired
    private RoleRepository roleRepository;

    @Override
    public List<Role> getAllRoles() {
        return roleRepository.findAllByOrderByNameAsc();
    }

    @Override
    public Role getRoleById(int id) {
        return roleRepository.findById(id).orElse(null);
    }

    @Override
    public Role saveRole(Role role) {
        return roleRepository.save(role);
    }

    @Override
    public void deleteRole(int id) {
        roleRepository.deleteById(id);
    }

    @Override
    public Role getRoleByName(String name) {
        return roleRepository.findByName(name);
    }

    @Override
    public List<Role> searchRolesByKeyword(String keyword) {
        return roleRepository.findByNameContainingIgnoreCaseOrderByNameAsc(keyword);
    }

    @Override
    public long count() {
        return roleRepository.count();
    }
}
