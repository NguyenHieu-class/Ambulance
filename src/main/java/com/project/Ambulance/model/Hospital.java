package com.project.Ambulance.model;

import java.util.Date;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "hospitals")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class Hospital {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int idHospital;

    // Tên bệnh viện
    @Column(columnDefinition = "nvarchar(100) not null", unique = true)
    private String name;

    // Địa chỉ cụ thể (VD: "12 Nguyễn Văn A, phường B...")
    @Column(columnDefinition = "nvarchar(255)")
    private String addressDetail;

    // Số điện thoại liên hệ chính
    @Column(columnDefinition = "nvarchar(20)")
    private String phone;

    // Email quản lý (tùy chọn)
    @Column(columnDefinition = "nvarchar(100)")
    private String email;

    // Tên người đại diện (giám đốc)
    @Column(columnDefinition = "nvarchar(100)")
    private String directorName;

    private Date createDate;
    private Date updateDate;

    // === Quan hệ với địa lý ===

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "province_id")
    private Province province;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "district_id")
    private District district;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "ward_id")
    private Ward ward;

    // === Quan hệ ngược ===

    // Danh sách xe cứu thương thuộc bệnh viện
    @OneToMany(mappedBy = "hospital", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @EqualsAndHashCode.Exclude
    @ToString.Exclude
    @JsonIgnore
    private List<Ambulance> ambulances;

    // Danh sách nhân viên y tế thuộc bệnh viện
    @OneToMany(mappedBy = "hospital", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @EqualsAndHashCode.Exclude
    @ToString.Exclude
    @JsonIgnore
    private List<MedicalStaff> medicalStaffList;
}
