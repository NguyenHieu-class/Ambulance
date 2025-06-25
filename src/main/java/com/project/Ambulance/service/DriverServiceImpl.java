package com.project.Ambulance.service;

import com.project.Ambulance.model.Driver;
import com.project.Ambulance.repository.DriverRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class DriverServiceImpl implements DriverService {

    @Autowired
    private DriverRepository driverRepository;

    @Override
    public List<Driver> getAllDriver() {
        return driverRepository.findAll();
    }

    @Override
    public Driver getDriverById(Long id) {
        Optional<Driver> optional = driverRepository.findById(id);
        if (optional.isPresent()) {
            return optional.get();
        } else {
            throw new RuntimeException("Không tìm thấy tài xế với ID: " + id);
        }
    }

    @Override
    public void saveDriver(Driver driver) {
        driverRepository.save(driver);
    }

    @Override
    public void deleteDriver(Long id) {
        driverRepository.deleteById(id);
    }

    @Override
    public long countDriver() {
        return driverRepository.count();
    }

    @Override
    public Driver getDriverByUsername(String username) {
        return driverRepository.findByUsername(username);
    }

    @Override
    public boolean existsByUsername(String username) {
        return driverRepository.existsByUsername(username);
    }

    @Override
    public List<Driver> getDriversByYearsOfExperienceGreaterThan(int years) {
        return driverRepository.findByYearsOfExperienceGreaterThan(years);
    }

    @Override
    public List<Driver> getDriversByLicenseNumber(String licenseNumber) {
        return driverRepository.findByLicenseNumber(licenseNumber);
    }
}
