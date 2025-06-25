package com.project.Ambulance.service;

import com.project.Ambulance.model.Hospital;
import com.project.Ambulance.repository.HospitalRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class HospitalServiceImpl implements HospitalService {

    @Autowired
    private HospitalRepository hospitalRepository;

    @Override
    public List<Hospital> getAllHospital() {
        return hospitalRepository.findAll();
    }

    @Override
    public Hospital getHospitalById(Long id) {
        Optional<Hospital> optional = hospitalRepository.findById(id);
        if (optional.isPresent()) {
            return optional.get();
        } else {
            throw new RuntimeException("Không tìm thấy bệnh viện với ID: " + id);
        }
    }

    @Override
    public void saveHospital(Hospital hospital) {
        hospitalRepository.save(hospital);
    }

    @Override
    public void deleteHospital(Long id) {
        hospitalRepository.deleteById(id);
    }

    @Override
    public long countHospital() {
        return hospitalRepository.count();
    }

    @Override
    public Hospital getHospitalByName(String name) {
        return hospitalRepository.findByName(name);
    }

    @Override
    public boolean existsByName(String name) {
        return hospitalRepository.existsByName(name);
    }

    @Override
    public List<Hospital> getHospitalsByProvinceName(String provinceName) {
        return hospitalRepository.findByProvince_Name(provinceName);
    }

    @Override
    public List<Hospital> getHospitalsByProvinceAndDistrict(String provinceName, String districtName) {
        return hospitalRepository.findByProvince_NameAndDistrict_Name(provinceName, districtName);
    }
}
