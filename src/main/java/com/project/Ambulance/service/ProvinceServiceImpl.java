package com.project.Ambulance.service;

import com.project.Ambulance.model.Province;
import com.project.Ambulance.repository.ProvinceRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ProvinceServiceImpl implements ProvinceService {

    @Autowired
    private ProvinceRepository provinceRepository;

    @Override
    public List<Province> getAllProvince() {
        return provinceRepository.findAll();
    }

    @Override
    public Province getProvinceById(Long id) {
        Optional<Province> optional = provinceRepository.findById(id);
        if (optional.isPresent()) {
            return optional.get();
        } else {
            throw new RuntimeException("Không tìm thấy Tỉnh/Thành phố với ID: " + id);
        }
    }

    @Override
    public void saveProvince(Province province) {
        provinceRepository.save(province);
    }

    @Override
    public void deleteProvince(Long id) {
        provinceRepository.deleteById(id);
    }

    @Override
    public long countProvince() {
        return provinceRepository.count();
    }

    @Override
    public Province getProvinceByName(String name) {
        return provinceRepository.findByName(name);
    }

    @Override
    public boolean existsByName(String name) {
        return provinceRepository.existsByName(name);
    }
}
