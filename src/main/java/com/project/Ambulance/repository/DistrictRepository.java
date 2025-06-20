package com.project.Ambulance.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.project.Ambulance.model.District;

public interface DistrictRepository extends JpaRepository<District, Integer> {
}
