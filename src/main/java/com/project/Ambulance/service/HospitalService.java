package com.project.Ambulance.service;

import com.project.Ambulance.model.Hospital;

import java.util.List;

public interface HospitalService {

    List<Hospital> getAllHospital();

    Hospital getHospitalById(Long id);

    void saveHospital(Hospital hospital);

    void deleteHospital(Long id);

    long countHospital();

    Hospital getHospitalByName(String name);

    boolean existsByName(String name);

    List<Hospital> getHospitalsByProvinceName(String provinceName);

    List<Hospital> getHospitalsByProvinceAndDistrict(String provinceName, String districtName);
}
