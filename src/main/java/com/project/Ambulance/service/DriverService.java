package com.project.Ambulance.service;

import com.project.Ambulance.model.Driver;
import java.util.List;

public interface DriverService {

    List<Driver> getAllDrivers();

    List<Driver> getDriversByHospital(int hospitalId);

    List<Driver> getDriversByStatus(int status);

    List<Driver> getDriversByHospitalAndStatus(int hospitalId, int status);

    List<Driver> searchDriversByName(String name);

    List<Driver> searchDriversByPhone(String phone);

    List<Driver> searchDriversByLicenseNumber(String licenseNumber);

    Driver getDriverById(int id);

    Driver saveDriver(Driver driver);

    void deleteDriver(int id);

    void updateStatus(int id, int status);

    int countByHospital(int hospitalId);

    long countAll();
}
