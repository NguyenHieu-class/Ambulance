package com.project.Ambulance.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.project.Ambulance.model.Trip;

public interface TripRepository extends JpaRepository<Trip, Integer> {
}
