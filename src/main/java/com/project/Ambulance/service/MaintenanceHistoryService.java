package com.project.Ambulance.service;

import com.project.Ambulance.model.MaintenanceHistory;

import java.time.LocalDate;
import java.util.List;

public interface MaintenanceHistoryService {

    List<MaintenanceHistory> getAllMaintenanceHistory();

    MaintenanceHistory getMaintenanceHistoryById(Long id);

    void saveMaintenanceHistory(MaintenanceHistory maintenanceHistory);

    void deleteMaintenanceHistory(Long id);

    long countMaintenanceHistory();

    List<MaintenanceHistory> getMaintenanceByAmbulanceId(Long ambulanceId);

    List<MaintenanceHistory> getMaintenanceAfterDate(LocalDate fromDate);

    List<MaintenanceHistory> getMaintenanceByType(String maintenanceType);
}
