package com.project.Ambulance.service;

import com.project.Ambulance.model.Ambulance;
import com.project.Ambulance.repository.AmbulanceRepository;
import com.project.Ambulance.constants.AppConstants;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AmbulanceServiceImpl implements AmbulanceService {

    @Autowired
    private AmbulanceRepository ambulanceRepository;

    @Override
    public List<Ambulance> getAllAmbulances() {
        return ambulanceRepository.findAllByOrderByNameAsc();
    }

    @Override
    public List<Ambulance> getAmbulancesByStatus(int status) {
        return ambulanceRepository.findByStatusOrderByNameAsc(status);
    }

    @Override
    public List<Ambulance> getAmbulancesByHospital(int hospitalId) {
        return ambulanceRepository.findByHospitalIdHospitalOrderByNameAsc(hospitalId);
    }

    @Override
    public List<Ambulance> getAmbulancesByDriver(int driverId) {
        return ambulanceRepository.findByDriverIdDriverOrderByNameAsc(driverId);
    }

    @Override
    public List<Ambulance> searchAmbulancesByLocation(String keyword) {
        return ambulanceRepository.findByCurrentLocationContainingIgnoreCaseOrderByNameAsc(keyword);
    }

    @Override
    public Ambulance getAmbulanceById(int id) {
        return ambulanceRepository.findById(id).orElse(null);
    }

    @Override
    public Ambulance getAmbulanceByLicensePlate(String licensePlate) {
        return ambulanceRepository.findByLicensePlate(licensePlate);
    }

    @Override
    public Ambulance saveAmbulance(Ambulance ambulance) {
        return ambulanceRepository.save(ambulance);
    }

    @Override
    public void deleteAmbulance(int id) {
        ambulanceRepository.deleteById(id);
    }

    @Override
    public void updateStatus(int id, int status) {
        if (status != AppConstants.AMBULANCE_STATUS_ACTIVE &&
            status != AppConstants.AMBULANCE_STATUS_MAINTENANCE &&
            status != AppConstants.AMBULANCE_STATUS_BROKEN &&
            status != AppConstants.AMBULANCE_STATUS_DECOMMISSIONED) {
            throw new IllegalArgumentException("Invalid ambulance status: " + status);
        }
        ambulanceRepository.updateStatus(id, status);
    }

    @Override
    public long countAll() {
        return ambulanceRepository.count();
    }

    @Override
    public int countByStatus(int status) {
        return ambulanceRepository.countByStatus(status);
    }

    @Override
    public List<Ambulance> getAvailableAmbulancesWithMedicalEquipment() {
        return ambulanceRepository.findByMedicalEquipmentTrueAndStatusOrderByNameAsc(AppConstants.AMBULANCE_STATUS_ACTIVE);
    }
}
