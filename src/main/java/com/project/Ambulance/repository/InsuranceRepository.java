package com.project.Ambulance.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.project.Ambulance.model.Insurance;
@Repository
public interface InsuranceRepository extends JpaRepository<Insurance, Integer> {

	long count();
}
