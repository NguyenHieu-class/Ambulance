package com.project.Ambulance.model;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "fuel_types")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class FuelType {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int idFuel;  // Mã định danh loại nhiên liệu

    @Column(columnDefinition = "nvarchar(50) not null", unique = true)
    private String fuelName;  // Tên loại nhiên liệu (xăng, diesel, điện, ...)

    @Column(columnDefinition = "nvarchar(200)")
    private String description;  // Mô tả thêm về loại nhiên liệu

    @Column(columnDefinition = "nvarchar(20)")
    private String unit;  // Đơn vị đo (lít, kWh,...)

    private double pricePerUnit;  // Giá cho mỗi đơn vị nhiên liệu
}
