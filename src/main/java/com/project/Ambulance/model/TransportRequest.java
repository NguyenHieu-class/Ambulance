package com.project.Ambulance.model;

import jakarta.persistence.*;
import lombok.*;
import java.time.LocalDateTime;

/**
 * Entity đại diện cho yêu cầu vận chuyển xe cứu thương
 */
@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class TransportRequest {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id; // Khóa chính

    private LocalDateTime requestDate; // Thời điểm tạo yêu cầu vận chuyển

    @Column(length = 200)
    private String reason; // Lý do vận chuyển (VD: cấp cứu, vận chuyển nội viện...)

    /**
     * Người yêu cầu vận chuyển (liên kết với nhân viên y tế)
     */
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "medical_staff_id")
    private MedicalStaff requester;

    /**
     * Bệnh viện thực hiện yêu cầu vận chuyển
     */
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "hospital_id")
    private Hospital hospital;

    /**
     * Xe cứu thương được phân công cho yêu cầu này
     */
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "ambulance_id")
    private Ambulance assignedAmbulance;

    /**
     * Tài xế được phân công cho yêu cầu này
     */
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "driver_id")
    private Driver assignedDriver;

    @Enumerated(EnumType.STRING)
    private Status status; // Trạng thái xử lý yêu cầu

    /**
     * Trạng thái của yêu cầu vận chuyển
     */
    public enum Status {
        PENDING,    // Đang chờ xử lý
        ASSIGNED,   // Đã phân công xe và tài xế
        COMPLETED   // Đã hoàn thành vận chuyển
    }
}
