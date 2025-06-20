package com.project.Ambulance.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.project.Ambulance.model.PaymentBill;

@Repository
public interface PaymentBillReopository  extends JpaRepository<PaymentBill,Integer>{

}
