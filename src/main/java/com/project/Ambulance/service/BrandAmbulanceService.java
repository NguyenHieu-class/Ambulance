package com.project.Ambulance.service;

import com.project.Ambulance.model.BrandAmbulance;
import java.util.List;

public interface BrandAmbulanceService {

    List<BrandAmbulance> getAllBrands();

    BrandAmbulance getBrandById(int id);

    BrandAmbulance saveBrand(BrandAmbulance brand);

    void deleteBrand(int id);

    List<BrandAmbulance> searchBrandByName(String name);

    long count();
}
