package com.project.Ambulance.service;

import com.project.Ambulance.model.Ward;
import com.project.Ambulance.repository.WardRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class WardServiceImpl implements WardService {

    @Autowired
    private WardRepository wardRepository;

    @Override
    public List<Ward> getAllWard() {
        return wardRepository.findAll();
    }

    @Override
    public Ward getWardById(Long id) {
        Optional<Ward> optional = wardRepository.findById(id);
        if (optional.isPresent()) {
            return optional.get();
        } else {
            throw new RuntimeException("Không tìm thấy Phường/Xã với ID: " + id);
        }
    }

    @Override
    public void saveWard(Ward ward) {
        wardRepository.save(ward);
    }

    @Override
    public void deleteWard(Long id) {
        wardRepository.deleteById(id);
    }

    @Override
    public long countWard() {
        return wardRepository.count();
    }

    @Override
    public Ward getWardByName(String name) {
        return wardRepository.findByName(name);
    }

    @Override
    public boolean existsByName(String name) {
        return wardRepository.existsByName(name);
    }

    @Override
    public List<Ward> getWardsByDistrictId(Long districtId) {
        return wardRepository.findByDistrict_Id(districtId);
    }
}
