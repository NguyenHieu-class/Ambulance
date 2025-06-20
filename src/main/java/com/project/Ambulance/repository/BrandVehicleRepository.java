package com.project.Ambulance.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.project.Ambulance.model.BrandVehicle;

public interface BrandVehicleRepository extends JpaRepository<BrandVehicle, Integer> {
}
