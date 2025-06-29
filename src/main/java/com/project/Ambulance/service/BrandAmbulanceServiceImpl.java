package com.project.Ambulance.service;

import com.project.Ambulance.model.BrandAmbulance;
import com.project.Ambulance.repository.BrandAmbulanceRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BrandAmbulanceServiceImpl implements BrandAmbulanceService {

    @Autowired
    private BrandAmbulanceRepository brandAmbulanceRepository;

    @Override
    public List<BrandAmbulance> getAllBrands() {
        return brandAmbulanceRepository.findAllByOrderByNameBrandAsc();
    }

    @Override
    public BrandAmbulance getBrandById(int id) {
        return brandAmbulanceRepository.findById(id).orElse(null);
    }

    @Override
    public BrandAmbulance saveBrand(BrandAmbulance brand) {
        return brandAmbulanceRepository.save(brand);
    }

    @Override
    public void deleteBrand(int id) {
        brandAmbulanceRepository.deleteById(id);
    }

    @Override
    public List<BrandAmbulance> searchBrandByName(String name) {
        return brandAmbulanceRepository.findByNameBrandContainingIgnoreCaseOrderByNameBrandAsc(name);
    }

    @Override
    public long count() {
        return brandAmbulanceRepository.count();
    }
}
