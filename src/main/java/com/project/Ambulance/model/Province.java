package com.project.Ambulance.model;

import jakarta.persistence.*;
import lombok.*;
import com.fasterxml.jackson.annotation.JsonIgnore;
import java.time.LocalDate;
import java.util.List;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

/**
 * Tỉnh / Thành phố
 */
@Entity
@EntityListeners(AuditingEntityListener.class)
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class Province {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id; // ID Tỉnh

    @Column(nullable = false, length = 70)
    private String name; // Tên tỉnh / thành phố

    private int zipCode; // Mã bưu chính

    private String image; // Hình ảnh đại diện (nếu có)

    @CreatedDate
    private LocalDate createDate;
    @LastModifiedDate
    private LocalDate updateDate;

    @OneToMany(mappedBy = "province", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @EqualsAndHashCode.Exclude
    @ToString.Exclude
    @JsonIgnore
    private List<District> districts;
}
