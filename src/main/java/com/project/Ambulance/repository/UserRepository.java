package com.project.Ambulance.repository;

import com.project.Ambulance.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * Repository quản lý bảng User (tài khoản hệ thống)
 * Kế thừa JpaRepository có đầy đủ CRUD
 */
@Repository
public interface UserRepository extends JpaRepository<User, Long> {

    /**
     * Tìm người dùng theo username (phục vụ đăng nhập, kiểm tra tài khoản)
     */
    User findByUsername(String username);

    /**
     * Kiểm tra username có tồn tại chưa (để validate khi tạo tài khoản mới)
     */
    boolean existsByUsername(String username);
}
