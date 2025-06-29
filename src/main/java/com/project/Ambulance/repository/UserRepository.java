package com.project.Ambulance.repository;

import com.project.Ambulance.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import jakarta.transaction.Transactional;
import java.util.List;

@Repository
public interface UserRepository extends JpaRepository<User, Integer> {

    // Tìm user theo username (đăng nhập)
    User findByUsername(String username);

    // Kiểm tra tồn tại username
    boolean existsByUsername(String username);

    // Tất cả user, sắp xếp theo tên
    List<User> findAllByOrderByNameDisplayAsc();

    // Tìm theo tên hiển thị gần đúng
    List<User> findByNameDisplayContainingIgnoreCaseOrderByNameDisplayAsc(String keyword);

    // Tìm theo số điện thoại gần đúng
    List<User> findByPhoneContainingIgnoreCaseOrderByNameDisplayAsc(String phone);

    // Tìm theo email gần đúng
    List<User> findByEmailContainingIgnoreCaseOrderByNameDisplayAsc(String email);

    // Tìm theo role
    List<User> findByRoleIdRoleOrderByNameDisplayAsc(int roleId);

    // Cập nhật mật khẩu
    @Modifying
    @Transactional
    @Query("UPDATE User u SET u.password = :password WHERE u.idUser = :id")
    void updatePassword(@Param("id") int idUser, @Param("password") String password);

    // Cập nhật thông tin liên hệ
    @Modifying
    @Transactional
    @Query("UPDATE User u SET u.email = :email, u.phone = :phone, u.address = :address WHERE u.idUser = :id")
    void updateContactInfo(@Param("id") int idUser, @Param("email") String email,
                           @Param("phone") String phone, @Param("address") String address);

    // Đếm số user theo role
    int countByRoleIdRole(int roleId);

    // Đếm tất cả
    long count();
}
