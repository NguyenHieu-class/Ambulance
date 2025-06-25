package com.project.Ambulance.service;

import com.project.Ambulance.model.District;
import com.project.Ambulance.repository.DistrictRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class DistrictServiceImpl implements DistrictService {

    @Autowired
    private DistrictRepository districtRepository;

    @Override
    public List<District> getAllDistrict() {
        return districtRepository.findAll();
    }

    @Override
    public District getDistrictById(Long id) {
        Optional<District> optional = districtRepository.findById(id);
        if (optional.isPresent()) {
            return optional.get();
        } else {
            throw new RuntimeException("Không tìm thấy Quận/Huyện với ID: " + id);
        }
    }

    @Override
    public void saveDistrict(District district) {
        districtRepository.save(district);
    }

    @Override
    public void deleteDistrict(Long id) {
        districtRepository.deleteById(id);
    }

    @Override
    public long countDistrict() {
        return districtRepository.count();
    }

    @Override
    public District getDistrictByName(String name) {
        return districtRepository.findByName(name);
    }

    @Override
    public boolean existsByName(String name) {
        return districtRepository.existsByName(name);
    }

    @Override
    public List<District> getDistrictsByProvinceId(Long provinceId) {
        return districtRepository.findByProvince_Id(provinceId);
    }
}
