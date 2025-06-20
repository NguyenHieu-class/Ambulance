package com.project.Ambulance.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.project.Ambulance.model.Contract;

@Repository
public interface ContractRepository extends JpaRepository<Contract, Integer> {

}
