package com.project.Ambulance.service;

import com.project.Ambulance.model.Hospital;
import com.project.Ambulance.repository.HospitalRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class HospitalServiceImpl implements HospitalService {

    @Autowired
    private HospitalRepository hospitalRepository;

    @Override
    public List<Hospital> getAllHospitals() {
        return hospitalRepository.findAll();
    }

    @Override
    public List<Hospital> getHospitalsByProvince(int provinceId) {
        return hospitalRepository.findByProvinceIdProvinceOrderByNameAsc(provinceId);
    }

    @Override
    public List<Hospital> searchHospitalsByName(String keyword) {
        return hospitalRepository.findByNameContainingIgnoreCaseOrderByNameAsc(keyword);
    }

    @Override
    public List<Hospital> searchHospitalsByAddress(String address) {
        return hospitalRepository.findByAddressDetailContainingIgnoreCaseOrderByNameAsc(address);
    }

    @Override
    public Hospital getHospitalById(int id) {
        return hospitalRepository.findById(id).orElse(null);
    }

    @Override
    public Hospital getHospitalByName(String name) {
        return hospitalRepository.findByName(name);
    }

    @Override
    public Hospital saveHospital(Hospital hospital) {
        return hospitalRepository.save(hospital);
    }

    @Override
    public void deleteHospital(int id) {
        hospitalRepository.deleteById(id);
    }

    @Override
    public void updateDirectorName(int id, String newDirector) {
        hospitalRepository.updateDirectorName(id, newDirector);
    }

    @Override
    public int countByProvince(int provinceId) {
        return hospitalRepository.countByProvinceIdProvince(provinceId);
    }

    @Override
    public long countAll() {
        return hospitalRepository.count();
    }
}
