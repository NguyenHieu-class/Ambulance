package com.project.Ambulance.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.project.Ambulance.model.PolicyAndTerms;
@Repository
public interface PolicyAndTermsRepository  extends JpaRepository<PolicyAndTerms, Integer>{
	long count();
}
