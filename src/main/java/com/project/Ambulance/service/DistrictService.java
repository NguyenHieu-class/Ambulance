package com.project.Ambulance.service;

import java.util.List;

import com.project.Ambulance.model.District;


public interface DistrictService {
       List<District> getAllDistrictWithProvince();
	void saveDistrict(District d);
	District getDistrict(int id);
	void deleteDistrict(int id);
	List<District> getAllDistrict();
	List<District> getAllDistrictByIdProvince(int idProvence);
	
}