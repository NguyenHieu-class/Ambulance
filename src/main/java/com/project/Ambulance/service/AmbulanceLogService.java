package com.project.Ambulance.service;

import com.project.Ambulance.model.AmbulanceLog;

import java.time.LocalDateTime;
import java.util.List;

public interface AmbulanceLogService {

    List<AmbulanceLog> getAllAmbulanceLog();

    AmbulanceLog getAmbulanceLogById(Long id);

    void saveAmbulanceLog(AmbulanceLog ambulanceLog);

    void deleteAmbulanceLog(Long id);

    long countAmbulanceLog();

    List<AmbulanceLog> getLogsByAmbulanceId(Long ambulanceId);

    List<AmbulanceLog> getLogsByDriverId(Long driverId);

    List<AmbulanceLog> getLogsBetween(LocalDateTime start, LocalDateTime end);

    List<AmbulanceLog> getLogsByDistanceGreaterThan(double distance);
}
