package com.project.Ambulance.service;

import com.project.Ambulance.model.AmbulanceBrand;
import com.project.Ambulance.repository.AmbulanceBrandRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class AmbulanceBrandServiceImpl implements AmbulanceBrandService {

    @Autowired
    private AmbulanceBrandRepository ambulanceBrandRepository;

    @Override
    public List<AmbulanceBrand> getAllAmbulanceBrand() {
        return ambulanceBrandRepository.findAll();
    }

    @Override
    public AmbulanceBrand getAmbulanceBrandById(Long id) {
        Optional<AmbulanceBrand> optional = ambulanceBrandRepository.findById(id);
        if (optional.isPresent()) {
            return optional.get();
        } else {
            throw new RuntimeException("Không tìm thấy thương hiệu với ID: " + id);
        }
    }

    @Override
    public void saveAmbulanceBrand(AmbulanceBrand ambulanceBrand) {
        ambulanceBrandRepository.save(ambulanceBrand);
    }

    @Override
    public void deleteAmbulanceBrand(Long id) {
        ambulanceBrandRepository.deleteById(id);
    }

    @Override
    public long countAmbulanceBrand() {
        return ambulanceBrandRepository.count();
    }

    @Override
    public AmbulanceBrand getAmbulanceBrandByBrandName(String brandName) {
        return ambulanceBrandRepository.findByBrandName(brandName);
    }

    @Override
    public boolean existsByBrandName(String brandName) {
        return ambulanceBrandRepository.existsByBrandName(brandName);
    }
}
