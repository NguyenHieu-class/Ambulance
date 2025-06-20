package com.project.Ambulance.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.project.Ambulance.model.FuelType;

public interface FuelTypeRepository extends JpaRepository<FuelType, Integer> {
}
