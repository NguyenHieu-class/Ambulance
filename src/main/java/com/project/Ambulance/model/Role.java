package com.project.Ambulance.model;

import jakarta.persistence.*;
import lombok.*;
import java.time.LocalDate;
import java.util.List;
import com.fasterxml.jackson.annotation.JsonIgnore;

/**
 * Entity đại diện cho các quyền hạn trong hệ thống
 */
@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class Role {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id; // Khóa chính

    @Column(nullable = false, unique = true, length = 50)
    private String roleName; // Tên quyền (ví dụ: ADMIN, DRIVER, MEDICAL_STAFF)

    private LocalDate createDate; // Ngày tạo quyền
    private LocalDate updateDate; // Ngày cập nhật quyền

    /**
     * Danh sách người dùng thuộc quyền này
     */
    @OneToMany(mappedBy = "role", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @EqualsAndHashCode.Exclude
    @ToString.Exclude
    @JsonIgnore
    private List<User> users;
}
