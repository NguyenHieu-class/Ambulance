package com.project.Ambulance.controller;

import com.project.Ambulance.model.Role;
import com.project.Ambulance.service.RoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
public class RoleController {

    @Autowired
    private RoleService roleService;

    // === ADMIN APIs ===
    @GetMapping("/admin/roles")
    public ResponseEntity<?> getAllRoles() {
        List<Role> roles = roleService.getAllRoles();
        return ResponseEntity.ok(roles);
    }

    @GetMapping("/admin/role/{id}")
    public ResponseEntity<?> getRoleById(@PathVariable int id) {
        Role role = roleService.getRoleById(id);
        if (role == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(role);
    }

    @PostMapping("/admin/roles")
    public ResponseEntity<?> createRole(@RequestBody Role role) {
        Role saved = roleService.saveRole(role);
        return ResponseEntity.ok(saved);
    }

    @PutMapping("/admin/role/{id}")
    public ResponseEntity<?> updateRole(@PathVariable int id, @RequestBody Role role) {
        role.setIdRole(id);
        Role updated = roleService.saveRole(role);
        return ResponseEntity.ok(updated);
    }

    @DeleteMapping("/admin/role/{id}")
    public ResponseEntity<?> deleteRole(@PathVariable int id) {
        roleService.deleteRole(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/admin/roles/search")
    public ResponseEntity<?> searchRoles(@RequestParam String keyword) {
        List<Role> roles = roleService.searchRolesByKeyword(keyword);
        return ResponseEntity.ok(roles);
    }
}
