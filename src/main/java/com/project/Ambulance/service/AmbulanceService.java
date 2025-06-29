package com.project.Ambulance.service;

import com.project.Ambulance.model.Ambulance;

import java.util.List;

public interface AmbulanceService {

    List<Ambulance> getAllAmbulances();

    List<Ambulance> getAmbulancesByStatus(int status);

    List<Ambulance> getAmbulancesByHospital(int hospitalId);

    List<Ambulance> getAmbulancesByDriver(int driverId);

    List<Ambulance> searchAmbulancesByLocation(String keyword);

    Ambulance getAmbulanceById(int id);

    Ambulance getAmbulanceByLicensePlate(String licensePlate);

    Ambulance saveAmbulance(Ambulance ambulance);

    void deleteAmbulance(int id);

    void updateStatus(int id, int status);

    long countAll();

    int countByStatus(int status);

    List<Ambulance> getAvailableAmbulancesWithMedicalEquipment();
} 
