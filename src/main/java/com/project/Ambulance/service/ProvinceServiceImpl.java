package com.project.Ambulance.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.project.Ambulance.model.Province;
import com.project.Ambulance.repository.ProvinceRepository;

@Service
public class ProvinceServiceImpl  implements ProvinceService{

	@Autowired
	private ProvinceRepository repo;

	public List<Province> getAllProvinceOrderByName() {
		
		return repo.findAllProvinceOrderByName();
	}

       @Override
       public void saveProvince(Province p) {
               repo.save(p);

       }

	@Override
	public Province getProvince(int id) {
		Optional<Province> optional = repo.findById(id);
		Province province = null;
		if (optional.isPresent()) {
			province = optional.get();
		} else {
			throw new RuntimeException(" Province not found for id :: " + id);
		}
		return province;
		
	}

	@Override
	public void deleteProvince(int id) {
		this.repo.deleteById(id);
		
	}



}
