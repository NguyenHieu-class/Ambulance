package com.project.Ambulance.service;

import com.project.Ambulance.model.Province;

import java.util.List;

public interface ProvinceService {

    List<Province> getAllProvince();

    Province getProvinceById(Long id);

    void saveProvince(Province province);

    void deleteProvince(Long id);

    long countProvince();

    Province getProvinceByName(String name);

    boolean existsByName(String name);
}
