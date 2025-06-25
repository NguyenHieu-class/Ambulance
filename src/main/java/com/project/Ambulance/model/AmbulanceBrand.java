package com.project.Ambulance.model;

import jakarta.persistence.*;
import lombok.*;
import com.fasterxml.jackson.annotation.JsonIgnore;
import java.time.LocalDate;
import java.util.List;

/**
 * Entity đại diện cho thương hiệu xe cứu thương
 */
@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class AmbulanceBrand {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id; // Khóa chính - ID thương hiệu

    @Column(nullable = false, unique = true, length = 100)
    private String brandName; // Tên thương hiệu (VD: Ford, Toyota, Mercedes...)

    private LocalDate createDate; // Ngày tạo thương hiệu
    private LocalDate updateDate; // Ngày cập nhật thương hiệu

    /**
     * Danh sách các xe thuộc thương hiệu này
     * Một thương hiệu có thể có nhiều xe
     */
    @OneToMany(mappedBy = "ambulanceBrand", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @EqualsAndHashCode.Exclude
    @ToString.Exclude
    @JsonIgnore
    private List<Ambulance> ambulances;
}
