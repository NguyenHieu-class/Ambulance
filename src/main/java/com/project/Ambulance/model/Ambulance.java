package com.project.Ambulance.model;

import java.util.Date;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "ambulances")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class Ambulance {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int idAmbulance;

    // Tên xe (VD: "Cấp cứu 01", "Xe HN-001")
    @Column(columnDefinition = "nvarchar(100) not null")
    private String name;

    // Biển số xe
    @Column(columnDefinition = "nvarchar(20)", unique = true)
    private String licensePlate;

    // Mô tả chi tiết về xe
    @Column(columnDefinition = "nvarchar(2000)")
    private String description;

    // Năm sản xuất
    private int modelYear;

    // Trạng thái hoạt động: 0 = hoạt động, 1 = bảo trì, 2 = hỏng, 3 = dừng sử dụng
    private int status;

    // Loại nhiên liệu: true = xăng, false = dầu
    private boolean fuelType;

    // Mức tiêu thụ nhiên liệu (lít/100km)
    private float fuelConsumptionPer100km;

    // Số ghế ngồi (tài xế + y tế + bệnh nhân)
    private int numberOfSeats;

    // === Thiết bị y tế ===
    private boolean medicalEquipment;     // Có thiết bị y tế
    private boolean oxygenTank;           // Có bình oxy
    private boolean defibrillator;        // Máy sốc tim
    private boolean patientMonitor;       // Máy theo dõi bệnh nhân
    private boolean stretcher;            // Băng ca
    private boolean infusionSupport;      // Hệ thống treo dịch truyền
    private boolean incubatorSupport;     // Có thể chở lồng kính cho trẻ sơ sinh

    // === Hệ thống hỗ trợ lái xe ===
    private boolean gpsLocator;
    private boolean camera360;
    private boolean reverseCamera;
    private boolean dashCamera;
    private boolean tpms;                 // Cảm biến áp suất lốp
    private boolean impactSensor;         // Cảnh báo va chạm
    private boolean speedWarning;         // Cảnh báo tốc độ
    private boolean airConditioning;

    // Hộp số: true = số sàn, false = tự động
    private boolean manualTransmission;

    // Hình ảnh xe (đường dẫn URL hoặc tên file)
    @Column(columnDefinition = "nvarchar(1000)")
    private String image;

    // Vị trí hiện tại của xe (có thể cập nhật theo GPS)
    @Column(columnDefinition = "nvarchar(255)")
    private String currentLocation;

    private Date createDate;
    private Date updateDate;

    // === Quan hệ với thương hiệu xe ===
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "brand_ambulance_id")
    @JsonIgnore
    private BrandAmbulance brandAmbulance;

    // === Quan hệ với bệnh viện ===
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "hospital_id")
    @JsonIgnore
    private Hospital hospital;

    // === Quan hệ với tài xế chính ===
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "driver_id")
    @JsonIgnore
    private Driver driver;

    // === Quan hệ với các nhân viên y tế đi kèm ===
    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(
        name = "ambulance_medicalstaff",
        joinColumns = @JoinColumn(name = "ambulance_id"),
        inverseJoinColumns = @JoinColumn(name = "medicalstaff_id")
    )
    @EqualsAndHashCode.Exclude
    @ToString.Exclude
    @JsonIgnore
    private List<MedicalStaff> medicalStaffList;

    // === Danh sách các lần điều động xe ===
    @OneToMany(mappedBy = "ambulance", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @EqualsAndHashCode.Exclude
    @ToString.Exclude
    @JsonIgnore
    private List<Booking> bookings;
}
