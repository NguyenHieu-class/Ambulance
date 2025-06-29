package com.project.Ambulance.repository;

import com.project.Ambulance.model.Role;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface RoleRepository extends JpaRepository<Role, Integer> {

    // Tìm vai trò theo tên (ví dụ: ADMIN, STAFF)
    Role findByName(String name);

    // Lấy tất cả theo thứ tự ABC
    List<Role> findAllByOrderByNameAsc();

    // Kiểm tra trùng tên role
    boolean existsByName(String name);

    // Đếm theo tên vai trò gần đúng
    int countByNameContainingIgnoreCase(String keyword);

    // Tìm danh sách role theo tên gần đúng (ABC)
    List<Role> findByNameContainingIgnoreCaseOrderByNameAsc(String keyword);

    // Đếm tổng số vai trò
    long count();
}
