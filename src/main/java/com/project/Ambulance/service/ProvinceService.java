package com.project.Ambulance.service;

import java.util.List;

import com.project.Ambulance.model.Province;

public interface ProvinceService {
	
       List<Province> getAllProvinceOrderByName();
       void saveProvince(Province p);
       Province getProvince(int id);
       void deleteProvince(int id);

}
