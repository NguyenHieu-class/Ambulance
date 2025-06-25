package com.project.Ambulance.model;

import jakarta.persistence.*;
import lombok.*;
import java.time.LocalDate;

/**
 * Entity đại diện cho xe cứu thương trong hệ thống
 */
@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class Ambulance {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id; // Khóa chính - ID xe cứu thương

    @Column(nullable = false, unique = true, length = 20)
    private String licensePlate; // Biển số xe cứu thương

    @Column(nullable = false, length = 100)
    private String name; // Tên định danh xe

    @Column(length = 200)
    private String avatarImage;

    @Column(length = 2000)
    private String imageGallery;

    private int modelYear; // Năm sản xuất xe

    @Enumerated(EnumType.STRING)
    private FuelType fuelType; // Loại nhiên liệu

    private float fuelConsumptionPer100km; // Mức tiêu hao nhiên liệu (lít hoặc kWh/100km)

    private int maximumOperatingDistance; // Quãng đường vận hành tối đa mỗi ca trực

    private int capacity; // Số giường bệnh nhân có thể chở

    // Các trang thiết bị y tế trên xe cứu thương
    private boolean ventilator;      // Có máy thở
    private boolean oxygenSystem;    // Có hệ thống oxy
    private boolean monitor;         // Có máy monitor sinh hiệu
    private boolean defibrillator;   // Có máy sốc tim
    private boolean infusionPump;    // Có bơm truyền dịch
    private boolean stretcher;       // Có cáng cứu thương
    private boolean suctionDevice;   // Có máy hút dịch
    private boolean incubator;       // Có lồng ấp sơ sinh

    // Các thiết bị phụ trợ tiện ích
    private boolean gpsLocator;
    private boolean dashCamera;
    private boolean reverseCamera;
    private boolean camera360;
    private boolean parkingCamera;
    private boolean tpms;            // Cảm biến áp suất lốp
    private boolean speedWarning;
    private boolean impactSensor;
    private boolean sunroof;
    private boolean dvdScreen;
    private boolean usb;
    private boolean airbags;
    private boolean manualTransmission; // Số tay hay số tự động

    private LocalDate lastMaintenanceDate; // Ngày bảo trì gần nhất

    private LocalDate createDate; // Ngày tạo
    private LocalDate updateDate; // Ngày cập nhật

    @Enumerated(EnumType.STRING)
    private Status status; // Trạng thái hoạt động của xe

    /**
     * Liên kết với bảng thương hiệu AmbulanceBrand
     */
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "brand_id")
    private AmbulanceBrand ambulanceBrand;

    /**
     * Enum loại nhiên liệu
     */
    public enum FuelType {
        GASOLINE, DIESEL, ELECTRIC, HYBRID
    }

    /**
     * Enum trạng thái hoạt động
     */
    public enum Status {
        AVAILABLE, IN_SERVICE, UNDER_MAINTENANCE, OUT_OF_SERVICE
    }
}
