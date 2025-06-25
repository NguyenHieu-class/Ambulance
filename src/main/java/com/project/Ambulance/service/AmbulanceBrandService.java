package com.project.Ambulance.service;

import com.project.Ambulance.model.AmbulanceBrand;

import java.util.List;

public interface AmbulanceBrandService {

    List<AmbulanceBrand> getAllAmbulanceBrand();

    AmbulanceBrand getAmbulanceBrandById(Long id);

    void saveAmbulanceBrand(AmbulanceBrand ambulanceBrand);

    void deleteAmbulanceBrand(Long id);

    long countAmbulanceBrand();

    AmbulanceBrand getAmbulanceBrandByBrandName(String brandName);

    boolean existsByBrandName(String brandName);
}
