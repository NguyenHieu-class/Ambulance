package com.project.Ambulance.model;

import jakarta.persistence.*;
import lombok.*;
import java.time.LocalDate;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

/**
 * Entity đại diện cho người dùng hệ thống (chủ yếu là quản trị viên)
 */
@Entity
@EntityListeners(AuditingEntityListener.class)
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id; // Khóa chính

    @Column(nullable = false, unique = true, length = 30)
    private String username; // Tài khoản đăng nhập

    @Column(nullable = false, length = 200)
    private String password; // Mật khẩu

    @Column(length = 200)
    private String image;

    @Column(length = 200)
    private String nameDisplay;

    @Column(length = 30)
    private String dateOfBrith;

    @Column(length = 500)
    private String address;

    @Column(length = 12)
    private String phone;

    @Column(length = 30)
    private String email;

    @Column(length = 20)
    private String drivingLicense;

    @Column(length = 100)
    private String imgDrivingLicense;

    @Column
    private boolean sex;


    @CreatedDate
    private LocalDate createDate; // Ngày tạo tài khoản
    @LastModifiedDate
    private LocalDate updateDate; // Ngày cập nhật tài khoản

    /**
     * Liên kết với bảng phân quyền Role
     */
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "role_id")
    private Role role;
}
