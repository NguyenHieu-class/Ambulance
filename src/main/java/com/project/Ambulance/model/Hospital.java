package com.project.Ambulance.model;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;

/**
 * Entity đại diện cho bệnh viện trong hệ thống
 */
@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class Hospital {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id; // Khóa chính - ID bệnh viện

    @Column(nullable = false, unique = true, length = 100)
    private String name; // Tên bệnh viện

    @Column(length = 300)
    private String address; // Địa chỉ bệnh viện

    @Column(length = 15)
    private String phoneNumber; // Số điện thoại liên hệ

    @Column(length = 100)
    private String email; // Email bệnh viện

    @Column
    private LocalDate createDate;

    @Column
    private LocalDate updateDate;


    /**
     * Liên kết địa lý hành chính: Tỉnh/Thành phố
     */
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "province_id")
    private Province province;

    /**
     * Liên kết địa lý hành chính: Quận/Huyện
     */
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "district_id")
    private District district;

    /**
     * Liên kết địa lý hành chính: Phường/Xã
     */
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "ward_id")
    private Ward ward;
}
