package com.project.Ambulance.model;

import java.util.Date;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "vehicles")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Vehicle {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int idVehicle;  // PK – mã định danh xe

    @Column(columnDefinition = "nvarchar(50) not null")
    private String vehicleName;  // Tên/biển hiệu xe cứu thương

    @Column(columnDefinition = "nvarchar(4000)")
    private String description;  // Giới thiệu tổng quan về xe, trạng thiết bị

    private int pricePerTrip;  // Giá dịch vụ cho mỗi chuyến

    private int standbyFee;  // Phí chờ khi nhân viên chờ tại hiện trường

    @Column(columnDefinition = "nvarchar(20)", unique = true)
    private String licensePlates;  // Biển số xe, đảm bảo duy nhất

    private int modelYear;  // Năm sản xuất

    private int status;
    // Trạng thái xe:
    // 0 = ngưng hoạt động, 1 = sẵn sàng, 2 = đang phục vụ v.v.

    @ManyToOne
    @JoinColumn(name = "fuel_id", nullable = false)
    private FuelType fuelType;
    // Tham chiếu đến bảng FuelType để lấy thông tin chi tiết loại nhiên liệu

    private float fuelConsumptionPer100km;
    // Lượng nhiên liệu tiêu thụ trung bình/100 km

    private int maxDeliveryDistance;
    // Phạm vi phục vụ tối đa (km)

    private int deliveryFeePerKm;
    // Phí thêm mỗi km vượt quá phạm vi cơ bản

    private int maxDistancePerDay;
    // Giới hạn quãng đường chạy mỗi ngày

    private int extraFeePerKm;
    // Phí thêm nếu vượt quá giới hạn km ngày

    @Column(columnDefinition = "nvarchar(2000)")
    private String equipment;
    // Danh sách thiết bị y tế đi kèm (ví dụ: bình oxy, máy thở…)

    @Column(columnDefinition = "nvarchar(1000)")
    private String parkingAddress;
    // Vị trí đỗ/chuẩn bị xe

    @Column(columnDefinition = "nvarchar(200)")
    private String avatarImage;
    // Ảnh đại diện xe

    @Column(columnDefinition = "nvarchar(2000)")
    private String galleryImages;
    // Bộ ảnh xe, có thể là dạng JSON hoặc URL cách nhau dấu phẩy

    @Column(columnDefinition = "nvarchar(2000)")
    private String galleryVideos;
    // Bộ video xe, có thể là dạng JSON hoặc URL cách nhau dấu phẩy

    private int numberOfSeats;
    // Số lượng ghế ngồi cho khoang lái

    // 🎯 Thiết bị y tế tiêu chuẩn trên xe cứu thương:
    private boolean oxygenTank;       // Bình oxy
    private boolean ventilator;       // Máy thở
    private boolean monitor;          // Máy theo dõi sinh hiệu
    private boolean defibrillator;    // Máy sốc tim
    private boolean stretcher;        // Cáng cứu thương
    private boolean infusionPump;     // Bơm truyền dịch

    // 🎯 Bổ sung các thuộc tính chi tiết hơn:
    private boolean suctionMachine;   // Máy hút dịch
    private boolean incubator;        // Lồng ấp sơ sinh
    private boolean wheelchair;       // Ghế lăn
    private boolean spineBoard;       // Cáng cố định cột sống
    private boolean nebulizer;        // Máy xông khí dung
    private boolean emergencyKit;     // Bộ dụng cụ cấp cứu tổng hợp

    private String communicationSystem; // Hệ thống liên lạc nội bộ, bộ đàm
    private boolean gpsTracking;        // Có thiết bị định vị GPS
    private boolean airConditioning;    // Điều hòa nhiệt độ trong khoang bệnh nhân
    private boolean heatingSystem;      // Hệ thống sưởi

    private String insurancePolicy;     // Thông tin hợp đồng bảo hiểm của xe
    private String registrationNumber;  // Mã đăng kiểm xe

    // 🚑 Các trang bị hỗ trợ vận hành và an toàn:
    private boolean reverseCamera;      // Camera lùi
    private boolean dashCamera;         // Camera hành trình
    private boolean camera360;          // Camera 360 độ
    private boolean parkingSensors;     // Cảm biến hỗ trợ đỗ xe
    private boolean tirePressureMonitor; // Hệ thống cảm biến áp suất lốp (TPMS)
    private boolean laneAssist;         // Hỗ trợ giữ làn đường
    private boolean collisionWarning;   // Cảnh báo va chạm
    private boolean blindSpotMonitor;   // Cảnh báo điểm mù

    // 🚑 Tiện nghi khoang bệnh nhân:
    private boolean androidDisplay;     // Màn hình Android điều khiển
    private int maxMedicalStaff;        // Số lượng nhân viên y tế tối đa
    private int maxPatients;            // Số bệnh nhân tối đa có thể vận chuyển

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_brand")
    @JsonIgnore
    private BrandVehicle brandVehicle;

    @OneToMany(mappedBy = "vehicle", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @EqualsAndHashCode.Exclude
    @ToString.Exclude
    @JsonIgnore
    private List<Trip> tripping;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_user")
    @JsonIgnore
    private User user;
}
