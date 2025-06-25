package com.project.Ambulance.service;

import com.project.Ambulance.model.Ambulance;
import com.project.Ambulance.model.Ambulance.FuelType;
import com.project.Ambulance.model.Ambulance.Status;

import java.time.LocalDate;
import java.util.List;

public interface AmbulanceService {

    List<Ambulance> getAllAmbulance();

    Ambulance getAmbulanceById(Long id);

    void saveAmbulance(Ambulance ambulance);

    void deleteAmbulance(Long id);

    long countAmbulance();

    // Các chức năng tìm kiếm nâng cao
    Ambulance getAmbulanceByLicensePlate(String licensePlate);

    List<Ambulance> getAmbulancesByStatus(Status status);

    List<Ambulance> getAmbulancesByFuelType(FuelType fuelType);

    List<Ambulance> getAmbulancesByLastMaintenanceAfter(LocalDate date);

    List<Ambulance> getAmbulancesWithMonitorAndOxygen();

    List<Ambulance> getAmbulancesWithVentilator();

    List<Ambulance> getAmbulancesByCapacityGreaterThan(int capacity);

    List<Ambulance> getAmbulancesByBrandName(String brandName);
}
