package com.project.Ambulance.model;

import jakarta.persistence.*;
import lombok.*;
import java.time.LocalDate;

/**
 * Entity đại diện cho nhân viên y tế trong hệ thống
 */
@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class MedicalStaff {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id; // Khóa chính

    @Column(nullable = false, unique = true, length = 30)
    private String username; // Tên tài khoản đăng nhập

    @Column(nullable = false, length = 200)
    private String password; // Mật khẩu đăng nhập

    @Column(nullable = false, length = 200)
    private String fullName; // Họ tên đầy đủ

    @Column(length = 100)
    private String profileImage; // Ảnh đại diện nhân viên

    @Column(length = 12)
    private String phone; // Số điện thoại liên hệ

    @Column(length = 30)
    private String email; // Email nhân viên

    @Column(length = 500)
    private String address; // Địa chỉ liên hệ

    @Column(length = 12)
    private String dateOfBirth; // Ngày sinh (chuỗi đơn giản)

    private boolean gender; // true: Nam, false: Nữ

    @Column(length = 100)
    private String department; // Phòng ban / Khoa công tác

    private LocalDate createDate; // Ngày tạo
    private LocalDate updateDate; // Ngày cập nhật
}
