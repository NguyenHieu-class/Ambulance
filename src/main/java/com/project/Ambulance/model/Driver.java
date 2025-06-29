package com.project.Ambulance.model;

import java.util.Date;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "drivers")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class Driver {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int idDriver;

    // Họ tên tài xế
    @Column(columnDefinition = "nvarchar(100) not null")
    private String name;

    // Số điện thoại liên hệ
    @Column(columnDefinition = "nvarchar(20)")
    private String phone;

    // Email cá nhân (nếu có)
    @Column(columnDefinition = "nvarchar(100)")
    private String email;

    // Ngày sinh
    @Column(columnDefinition = "nvarchar(12)")
    private String dateOfBirth;

    // Giới tính: true = nam, false = nữ
    private boolean sex;

    // Số bằng lái xe
    @Column(columnDefinition = "nvarchar(50)")
    private String licenseNumber;

    // Hình ảnh (tùy chọn)
    @Column(columnDefinition = "nvarchar(255)")
    private String avatar;

    // Trạng thái tài xế: 0 = đang rảnh, 1 = đang làm nhiệm vụ, 2 = tạm ngưng hoạt động
    private int status;

    private Date createDate;
    private Date updateDate;

    // === Quan hệ với bệnh viện ===
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "hospital_id")
    @JsonIgnore
    private Hospital hospital;

    // Danh sách xe mà tài xế phụ trách (1-n nhiều theo thời gian)
    @OneToMany(mappedBy = "driver", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @EqualsAndHashCode.Exclude
    @ToString.Exclude
    @JsonIgnore
    private List<Ambulance> ambulances;
}
