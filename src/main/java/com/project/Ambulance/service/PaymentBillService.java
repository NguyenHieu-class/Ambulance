package com.project.Ambulance.service;

import java.util.List;

import com.project.Ambulance.model.PaymentBill;

public interface PaymentBillService {

	void savePaymentBill(PaymentBill paymentBill);
	PaymentBill getAPaymentBill(int idPaymentBill);
	void deletePaymentBill(int idPaymentBill);
	List<PaymentBill> getAllPaymentBill();
}
