package com.project.Ambulance.service;

import com.project.Ambulance.model.Driver;

import java.util.List;

public interface DriverService {

    List<Driver> getAllDriver();

    Driver getDriverById(Long id);

    void saveDriver(Driver driver);

    void deleteDriver(Long id);

    long countDriver();

    Driver getDriverByUsername(String username);

    boolean existsByUsername(String username);

    List<Driver> getDriversByYearsOfExperienceGreaterThan(int years);

    List<Driver> getDriversByLicenseNumber(String licenseNumber);
}
