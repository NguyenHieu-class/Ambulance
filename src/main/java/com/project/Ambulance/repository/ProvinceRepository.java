package com.project.Ambulance.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.project.Ambulance.model.Province;

public interface ProvinceRepository extends JpaRepository<Province, Integer> {
}
