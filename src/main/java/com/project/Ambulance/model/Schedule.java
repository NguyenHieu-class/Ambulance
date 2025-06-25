package com.project.Ambulance.model;

import jakarta.persistence.*;
import lombok.*;
import java.time.LocalDate;

/**
 * Entity đại diện cho lịch phân ca trực xe cứu thương
 */
@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class Schedule {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id; // Khóa chính

    private LocalDate date; // Ngày trực

    @Enumerated(EnumType.STRING)
    private Shift shift; // Ca trực

    /**
     * Liên kết tới xe cứu thương phân công cho ca trực
     */
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "ambulance_id")
    private Ambulance ambulance;

    /**
     * Liên kết tới tài xế phân công cho ca trực
     */
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "driver_id")
    private Driver driver;

    /**
     * Enum xác định ca trực
     */
    public enum Shift {
        MORNING,    // Ca sáng
        AFTERNOON,  // Ca chiều
        NIGHT       // Ca tối
    }
}
