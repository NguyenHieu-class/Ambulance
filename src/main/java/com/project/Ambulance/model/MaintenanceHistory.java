package com.project.Ambulance.model;

import jakarta.persistence.*;
import lombok.*;
import java.time.LocalDate;

/**
 * Entity lưu lại lịch sử các lần bảo trì xe cứu thương
 */
@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class MaintenanceHistory {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id; // Khóa chính

    private LocalDate maintenanceDate; // Ngày bảo trì

    @Column(length = 100)
    private String maintenanceType; // Loại bảo trì (ví dụ: Thay dầu, kiểm tra phanh,...)

    @Column(length = 1000)
    private String notes; // Ghi chú thêm (nếu có)

    /**
     * Liên kết với xe cứu thương được bảo trì
     */
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "ambulance_id")
    private Ambulance ambulance;
}
