package com.project.Ambulance.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.project.Ambulance.model.GuideToWork;

@Repository
public interface GuideToWorkRepository  extends JpaRepository<GuideToWork, Integer>{

	long count();
}
