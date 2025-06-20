package com.project.Ambulance.model;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "hospitals")
@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class Hospital {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int idHospital;

    @Column(columnDefinition = "nvarchar(200) not null")
    private String nameHospital;

    @Column(columnDefinition = "nvarchar(200)")
    private String streetAddress;

    @Column(columnDefinition = "nvarchar(100)")
    private String city;

    @Column(columnDefinition = "nvarchar(100)")
    private String phone;

    @Column(columnDefinition = "nvarchar(100)")
    private String email;
}
