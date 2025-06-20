package com.project.Ambulance.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.project.Ambulance.model.Event;

@Repository
public interface EventRepository extends JpaRepository<Event,Integer> {

}
