package com.project.Ambulance.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.project.Ambulance.model.Ward;

public interface WardRepository extends JpaRepository<Ward, Integer> {
}
