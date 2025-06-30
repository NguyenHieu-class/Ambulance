package com.project.Ambulance.model;

import java.util.Date;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "bookings")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class Booking {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int idBooking;

    // Tên người yêu cầu cấp cứu (có thể là người nhà bệnh nhân)
    @Column(columnDefinition = "nvarchar(100)")
    private String requesterName;

    // Số điện thoại liên hệ khẩn cấp
    @Column(columnDefinition = "nvarchar(20)")
    private String phone;

    // Địa chỉ nơi cần cấp cứu (có thể tự nhập hoặc gợi ý từ danh sách địa lý)
    @Column(columnDefinition = "nvarchar(255)")
    private String pickupAddress;

    // Địa điểm chuyển đến (thường là bệnh viện)
    @Column(columnDefinition = "nvarchar(255)")
    private String destinationAddress;

    // Ghi chú tình trạng bệnh nhân
    @Column(columnDefinition = "nvarchar(1000)")
    private String patientConditionNote;

    // Thời gian gọi xe
    private Date requestTime;

    // Thời gian xe bắt đầu di chuyển
    private Date departureTime;

    // Thời gian đến nơi tiếp nhận bệnh nhân
    private Date arrivalTime;

    // Trạng thái: 0 = chờ xác nhận, 1 = đang xử lý, 2 = hoàn thành, 3 = hủy
    private int status;

    private Date createDate;
    private Date updateDate;

    // === Quan hệ với xe cứu thương ===
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "ambulance_id")
    @JsonIgnore
    private Ambulance ambulance;

    // === Người tạo yêu cầu này (nếu là nhân viên quản lý đăng nhập) ===
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    @JsonIgnore
    private User user;

    // === Tài xế phụ trách ca này ===
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "driver_id")
    @JsonIgnore
    private Driver driver;

    // === Nhân viên y tế tham gia ca này ===
    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(
        name = "booking_medicalstaff",
        joinColumns = @JoinColumn(name = "booking_id"),
        inverseJoinColumns = @JoinColumn(name = "medicalstaff_id")
    )
    @EqualsAndHashCode.Exclude
    @ToString.Exclude
    @JsonIgnore
    private List<MedicalStaff> medicalStaffList;
}
