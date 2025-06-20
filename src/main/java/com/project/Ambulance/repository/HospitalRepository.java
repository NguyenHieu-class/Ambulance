package com.project.Ambulance.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.project.Ambulance.model.Hospital;

public interface HospitalRepository extends JpaRepository<Hospital, Integer> {
}
