package com.project.Ambulance.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.project.Ambulance.model.PaymentBill;

public interface PaymentBillRepository extends JpaRepository<PaymentBill, Integer> {
}
