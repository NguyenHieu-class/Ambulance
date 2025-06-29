package com.project.Ambulance.service;

import java.util.List;

import com.project.Ambulance.model.Ward;

public interface WardService {

	List<Ward> getAllWardWithDistrictWithProvinces();
	List<Ward> getAllWardWithDistrict();
	List<Ward> getAllWard();
	Ward getAWard(int id);
	void saveWard(Ward ward);
	void deleteWard(int id);
       List<Ward> getAllWardByIdDistrict(int idDistrict);
	
 	
}
