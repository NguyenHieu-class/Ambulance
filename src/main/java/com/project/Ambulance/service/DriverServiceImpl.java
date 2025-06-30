package com.project.Ambulance.service;

import com.project.Ambulance.model.Driver;
import com.project.Ambulance.repository.DriverRepository;
import com.project.Ambulance.constants.AppConstants;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DriverServiceImpl implements DriverService {

    @Autowired
    private DriverRepository driverRepository;

    @Override
    public List<Driver> getAllDrivers() {
        return driverRepository.findAllByOrderByNameAsc();
    }

    @Override
    public List<Driver> getDriversByHospital(int hospitalId) {
        return driverRepository.findByHospitalIdHospitalOrderByNameAsc(hospitalId);
    }

    @Override
    public List<Driver> getDriversByStatus(int status) {
        return driverRepository.findByStatusOrderByNameAsc(status);
    }

    @Override
    public List<Driver> getDriversByHospitalAndStatus(int hospitalId, int status) {
        return driverRepository.findByHospitalIdHospitalAndStatusOrderByNameAsc(hospitalId, status);
    }

    @Override
    public List<Driver> searchDriversByName(String name) {
        return driverRepository.findByNameContainingIgnoreCaseOrderByNameAsc(name);
    }

    @Override
    public List<Driver> searchDriversByPhone(String phone) {
        return driverRepository.findByPhoneContainingIgnoreCaseOrderByNameAsc(phone);
    }

    @Override
    public List<Driver> searchDriversByLicenseNumber(String licenseNumber) {
        return driverRepository.findByLicenseNumberContainingIgnoreCaseOrderByNameAsc(licenseNumber);
    }

    @Override
    public Driver getDriverById(int id) {
        return driverRepository.findById(id).orElse(null);
    }

    @Override
    public Driver saveDriver(Driver driver) {
        return driverRepository.save(driver);
    }

    @Override
    public void deleteDriver(int id) {
        driverRepository.deleteById(id);
    }

    @Override
    public void updateStatus(int id, int status) {
        if (status != AppConstants.DRIVER_STATUS_AVAILABLE &&
            status != AppConstants.DRIVER_STATUS_ON_DUTY &&
            status != AppConstants.DRIVER_STATUS_SUSPENDED) {
            throw new IllegalArgumentException("Invalid driver status: " + status);
        }
        driverRepository.updateStatus(id, status);
    }

    @Override
    public int countByHospital(int hospitalId) {
        return driverRepository.countByHospitalIdHospital(hospitalId);
    }

    @Override
    public long countAll() {
        return driverRepository.count();
    }
}
