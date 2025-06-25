package com.project.Ambulance.service;

import com.project.Ambulance.model.Ambulance;
import com.project.Ambulance.model.Ambulance.Status;
import com.project.Ambulance.model.Ambulance.FuelType;
import com.project.Ambulance.repository.AmbulanceRepository;
import com.project.Ambulance.service.AmbulanceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Service
public class AmbulanceServiceImpl implements AmbulanceService {

    @Autowired
    private AmbulanceRepository ambulanceRepository;

    @Override
    public List<Ambulance> getAllAmbulance() {
        return ambulanceRepository.findAll();
    }

    @Override
    public Ambulance getAmbulanceById(Long id) {
        Optional<Ambulance> optional = ambulanceRepository.findById(id);
        if (optional.isPresent()) {
            return optional.get();
        } else {
            throw new RuntimeException("Không tìm thấy xe cứu thương với ID: " + id);
        }
    }

    @Override
    public void saveAmbulance(Ambulance ambulance) {
        ambulanceRepository.save(ambulance);
    }

    @Override
    public void deleteAmbulance(Long id) {
        ambulanceRepository.deleteById(id);
    }

    @Override
    public long countAmbulance() {
        return ambulanceRepository.count();
    }

    // ------- Các phương thức tìm kiếm nâng cao --------

    @Override
    public Ambulance getAmbulanceByLicensePlate(String licensePlate) {
        return ambulanceRepository.findByLicensePlate(licensePlate);
    }

    @Override
    public List<Ambulance> getAmbulancesByStatus(Status status) {
        return ambulanceRepository.findByStatus(status);
    }

    @Override
    public List<Ambulance> getAmbulancesByFuelType(FuelType fuelType) {
        return ambulanceRepository.findByFuelType(fuelType);
    }

    @Override
    public List<Ambulance> getAmbulancesByLastMaintenanceAfter(LocalDate date) {
        return ambulanceRepository.findByLastMaintenanceDateAfter(date);
    }

    @Override
    public List<Ambulance> getAmbulancesWithMonitorAndOxygen() {
        return ambulanceRepository.findByMonitorTrueAndOxygenSystemTrue();
    }

    @Override
    public List<Ambulance> getAmbulancesWithVentilator() {
        return ambulanceRepository.findByVentilatorTrue();
    }

    @Override
    public List<Ambulance> getAmbulancesByCapacityGreaterThan(int capacity) {
        return ambulanceRepository.findByCapacityGreaterThan(capacity);
    }

    @Override
    public List<Ambulance> getAmbulancesByBrandName(String brandName) {
        return ambulanceRepository.findByAmbulanceBrand_BrandName(brandName);
    }
}
