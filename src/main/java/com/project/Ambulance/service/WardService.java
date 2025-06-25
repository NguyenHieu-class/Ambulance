package com.project.Ambulance.service;

import com.project.Ambulance.model.Ward;

import java.util.List;

public interface WardService {

    List<Ward> getAllWard();

    Ward getWardById(Long id);

    void saveWard(Ward ward);

    void deleteWard(Long id);

    long countWard();

    Ward getWardByName(String name);

    boolean existsByName(String name);

    List<Ward> getWardsByDistrictId(Long districtId);
}
