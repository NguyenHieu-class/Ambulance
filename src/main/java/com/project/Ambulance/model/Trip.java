package com.project.Ambulance.model;

import java.util.Date;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "trips")
@Data
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class Trip {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int idTrip;

    private Date startTime;

    private Date endTime;

    @Column(columnDefinition = "nvarchar(200)")
    private String originAddress;

    @Column(columnDefinition = "nvarchar(200)")
    private String destinationAddress;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_vehicle")
    @JsonIgnore
    private Vehicle vehicle;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_driver")
    @JsonIgnore
    private User driver;

    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(name = "trip_medical_staff",
            joinColumns = @JoinColumn(name = "id_trip"),
            inverseJoinColumns = @JoinColumn(name = "id_user"))
    @JsonIgnore
    private List<User> medicalStaff;
}
