package com.project.Ambulance.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.project.Ambulance.model.Vehicle;

public interface VehicleRepository extends JpaRepository<Vehicle, Integer> {
}
