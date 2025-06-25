package com.project.Ambulance.model;

import jakarta.persistence.*;
import lombok.*;
import java.time.LocalDateTime;

/**
 * Entity ghi lại nhật ký vận hành từng lần sử dụng xe cứu thương
 */
@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class AmbulanceLog {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id; // Khóa chính

    private LocalDateTime tripStart; // Thời gian bắt đầu chuyến đi
    private LocalDateTime tripEnd;   // Thời gian kết thúc chuyến đi

    private double distanceTraveled; // Tổng quãng đường đã đi (km)

    @Column(length = 1000)
    private String notes; // Ghi chú thêm (nếu có)

    /**
     * Xe được sử dụng trong chuyến đi
     */
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "ambulance_id")
    private Ambulance ambulance;

    /**
     * Tài xế thực hiện chuyến đi
     */
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "driver_id")
    private Driver driver;
}
