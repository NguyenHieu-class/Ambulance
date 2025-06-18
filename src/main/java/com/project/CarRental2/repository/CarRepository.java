package com.project.Ambulance.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.project.Ambulance.model.Ambulance;

@Repository
public interface CarRepository extends JpaRepository<Ambulance, Integer> {
}
