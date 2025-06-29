package com.project.Ambulance.model;

import java.util.Date;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "medical_staff")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class MedicalStaff {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int idMedicalStaff;

    // Họ tên nhân viên y tế
    @Column(columnDefinition = "nvarchar(100) not null")
    private String name;

    // Số điện thoại
    @Column(columnDefinition = "nvarchar(20)")
    private String phone;

    // Email (nếu có)
    @Column(columnDefinition = "nvarchar(100)")
    private String email;

    // Ngày sinh
    @Column(columnDefinition = "nvarchar(12)")
    private String dateOfBirth;

    // Giới tính: true = nam, false = nữ
    private boolean sex;

    // Số giấy phép hành nghề (nếu có)
    @Column(columnDefinition = "nvarchar(100)")
    private String licenseNumber;

    // Chuyên môn (VD: bác sĩ, y tá, cấp cứu viên...)
    @Column(columnDefinition = "nvarchar(100)")
    private String specialization;

    // Hình ảnh đại diện (nếu có)
    @Column(columnDefinition = "nvarchar(255)")
    private String avatar;

    // Trạng thái: 0 = rảnh, 1 = đang làm việc, 2 = tạm nghỉ
    private int status;

    private Date createDate;
    private Date updateDate;

    // === Quan hệ với bệnh viện ===
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "hospital_id")
    @JsonIgnore
    private Hospital hospital;

    // === Quan hệ với các xe đã từng làm việc cùng ===
    @ManyToMany(mappedBy = "medicalStaffList", fetch = FetchType.LAZY)
    @EqualsAndHashCode.Exclude
    @ToString.Exclude
    @JsonIgnore
    private List<Ambulance> ambulances;
}
