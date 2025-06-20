package com.project.Ambulance.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.project.Ambulance.model.PaymentBill;
import com.project.Ambulance.repository.PaymentBillRepository;

@Service
public class PaymentBillServiceImpl  implements PaymentBillService{
	
	@Autowired 
	private PaymentBillRepository repository;

	@Override
	public void savePaymentBill(PaymentBill paymentBill) {
	repository.save(paymentBill);
	}

	@Override
	public PaymentBill getAPaymentBill(int idPaymentBill) {
		
		return repository.findById(idPaymentBill).get();
	}

	@Override
	public void deletePaymentBill(int idPaymentBill) {
		repository.deleteById(idPaymentBill);
		
	}

	@Override
	public List<PaymentBill> getAllPaymentBill() {
		return repository.findAll();
	}

}
