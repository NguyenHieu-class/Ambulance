package com.project.Ambulance.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.project.Ambulance.model.Trip;

public interface TripRepository extends JpaRepository<Trip, Integer> {
    List<Trip> findByDriver_IdUser(int driverId);

    @Query("SELECT t FROM Trip t JOIN t.medicalStaff m WHERE m.idUser = :userId")
    List<Trip> findByMedicalStaffId(@Param("userId") int userId);
}
