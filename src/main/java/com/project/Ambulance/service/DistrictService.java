package com.project.Ambulance.service;

import com.project.Ambulance.model.District;

import java.util.List;

public interface DistrictService {

    List<District> getAllDistrict();

    District getDistrictById(Long id);

    void saveDistrict(District district);

    void deleteDistrict(Long id);

    long countDistrict();

    District getDistrictByName(String name);

    boolean existsByName(String name);

    List<District> getDistrictsByProvinceId(Long provinceId);
}
