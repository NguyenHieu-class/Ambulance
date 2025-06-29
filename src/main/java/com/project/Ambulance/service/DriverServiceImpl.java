package com.project.Ambulance.service;

import com.project.Ambulance.model.Driver;
import com.project.Ambulance.repository.DriverRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DriverServiceImpl implements DriverService {

    @Autowired
    private DriverRepository driverRepository;

    @Override
    public List<Driver> getAllDrivers() {
        return driverRepository.findAllByOrderByFullNameAsc();
    }

    @Override
    public List<Driver> getDriversByHospital(int hospitalId) {
        return driverRepository.findByHospitalIdHospitalOrderByFullNameAsc(hospitalId);
    }

    @Override
    public List<Driver> getDriversByStatus(int status) {
        return driverRepository.findByStatusOrderByFullNameAsc(status);
    }

    @Override
    public List<Driver> getDriversByHospitalAndStatus(int hospitalId, int status) {
        return driverRepository.findByHospitalIdHospitalAndStatusOrderByFullNameAsc(hospitalId, status);
    }

    @Override
    public List<Driver> searchDriversByName(String name) {
        return driverRepository.findByFullNameContainingIgnoreCaseOrderByFullNameAsc(name);
    }

    @Override
    public List<Driver> searchDriversByPhone(String phone) {
        return driverRepository.findByPhoneContainingIgnoreCaseOrderByFullNameAsc(phone);
    }

    @Override
    public List<Driver> searchDriversByLicenseNumber(String licenseNumber) {
        return driverRepository.findByLicenseNumberContainingIgnoreCaseOrderByFullNameAsc(licenseNumber);
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
