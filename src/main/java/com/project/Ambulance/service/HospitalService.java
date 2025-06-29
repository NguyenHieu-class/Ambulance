package com.project.Ambulance.service;

import com.project.Ambulance.model.Hospital;
import java.util.List;

public interface HospitalService {

    List<Hospital> getAllHospitals();

    List<Hospital> getHospitalsByProvince(int provinceId);

    List<Hospital> searchHospitalsByName(String keyword);

    List<Hospital> searchHospitalsByAddress(String address);

    Hospital getHospitalById(int id);

    Hospital getHospitalByName(String name);

    Hospital saveHospital(Hospital hospital);

    void deleteHospital(int id);

    void updateDirectorName(int id, String newDirector);

    int countByProvince(int provinceId);

    long countAll();
} 
