package com.project.Ambulance.repository;

import com.project.Ambulance.model.Role;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * Repository quản lý bảng Role (Phân quyền người dùng)
 * Kế thừa JpaRepository có đầy đủ CRUD
 */
@Repository
public interface RoleRepository extends JpaRepository<Role, Long> {

    /**
     * Tìm quyền theo tên quyền (ADMIN, DRIVER, MEDICAL_STAFF,...)
     */
    Role findByRoleName(String roleName);

    /**
     * Kiểm tra role đã tồn tại chưa
     */
    boolean existsByRoleName(String roleName);
}
