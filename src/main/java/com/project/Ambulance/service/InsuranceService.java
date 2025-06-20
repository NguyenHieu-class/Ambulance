package com.project.Ambulance.service;

import java.util.List;

import com.project.Ambulance.model.Insurance;

public interface InsuranceService {
	void saveInsuance(Insurance insurance);
	Insurance getInsuranceById(int id);
	void deleteInsuranceById(int id);
	List<Insurance> getAllInsurance();
}
