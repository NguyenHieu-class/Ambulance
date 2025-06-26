package com.project.Ambulance.service;

import com.project.Ambulance.model.AmbulanceLog;
import com.project.Ambulance.repository.AmbulanceLogRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
public class AmbulanceLogServiceImpl implements AmbulanceLogService {

    @Autowired
    private AmbulanceLogRepository ambulanceLogRepository;

    @Override
    public List<AmbulanceLog> getAllAmbulanceLog() {
        return ambulanceLogRepository.findAll();
    }

    @Override
    public AmbulanceLog getAmbulanceLogById(Long id) {
        Optional<AmbulanceLog> optional = ambulanceLogRepository.findById(id);
        if (optional.isPresent()) {
            return optional.get();
        } else {
            throw new RuntimeException("Không tìm thấy nhật ký vận hành với ID: " + id);
        }
    }

    @Override
    public void saveAmbulanceLog(AmbulanceLog ambulanceLog) {
        ambulanceLogRepository.save(ambulanceLog);
    }

    @Override
    public void deleteAmbulanceLog(Long id) {
        ambulanceLogRepository.deleteById(id);
    }

    @Override
    public long countAmbulanceLog() {
        return ambulanceLogRepository.count();
    }

    @Override
    public List<AmbulanceLog> getLogsByAmbulanceId(Long ambulanceId) {
        return ambulanceLogRepository.findByAmbulance_Id(ambulanceId);
    }

    @Override
    public List<AmbulanceLog> getLogsByDriverId(Long driverId) {
        return ambulanceLogRepository.findByDriver_Id(driverId);
    }

    @Override
    public List<AmbulanceLog> getLogsBetween(LocalDateTime start, LocalDateTime end) {
        return ambulanceLogRepository.findByTripStartBetween(start, end);
    }

    @Override
    public List<AmbulanceLog> getLogsByDistanceGreaterThan(double distance) {
        return ambulanceLogRepository.findByDistanceTraveledGreaterThan(distance);
    }
}
