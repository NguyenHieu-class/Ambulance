package com.project.Ambulance.service;

import java.util.List;

import com.project.Ambulance.model.BrandCar;

public interface BrandCarService {
	
	BrandCar getBrandCarById(int id);
	List<BrandCar> getAllBrachCar();
	void deleteBrandCar(int id);
	void saveBrandCar(BrandCar brandCar);
	boolean checkNameBranchExist(List<BrandCar> listBrandCar, BrandCar branchCar);
	List<BrandCar> getAllBrandCarOderByNameAsc();

}
