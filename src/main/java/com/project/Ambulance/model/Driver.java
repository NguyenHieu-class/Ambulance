package com.project.Ambulance.model;

import jakarta.persistence.*;
import lombok.*;
import java.time.LocalDate;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

/**
 * Entity đại diện cho tài xế xe cứu thương
 */
@Entity
@EntityListeners(AuditingEntityListener.class)
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class Driver {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id; // Khóa chính

    @Column(nullable = false, unique = true, length = 30)
    private String username; // Tên tài khoản đăng nhập

    @Column(nullable = false, length = 200)
    private String password; // Mật khẩu đăng nhập

    @Column(nullable = false, length = 200)
    private String fullName; // Họ tên tài xế

    @Column(length = 100)
    private String profileImage; // Ảnh đại diện tài xế

    @Column(length = 12)
    private String phone; // Số điện thoại

    @Column(length = 30)
    private String email; // Email tài xế

    @Column(length = 500)
    private String address; // Địa chỉ tài xế

    @Column(length = 12)
    private String dateOfBirth; // Ngày sinh

    private boolean gender; // true: Nam, false: Nữ

    @Column(length = 20)
    private String drivingLicense; // Số bằng lái xe

    @Column(length = 100)
    private String drivingLicenseImage; // Ảnh chụp bằng lái xe

    @CreatedDate
    private LocalDate createDate; // Ngày tạo tài khoản
    @LastModifiedDate
    private LocalDate updateDate; // Ngày cập nhật tài khoản
}
