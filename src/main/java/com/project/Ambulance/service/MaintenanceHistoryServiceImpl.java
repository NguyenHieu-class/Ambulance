package com.project.Ambulance.service;

import com.project.Ambulance.model.MaintenanceHistory;
import com.project.Ambulance.repository.MaintenanceHistoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Service
public class MaintenanceHistoryServiceImpl implements MaintenanceHistoryService {

    @Autowired
    private MaintenanceHistoryRepository maintenanceHistoryRepository;

    @Override
    public List<MaintenanceHistory> getAllMaintenanceHistory() {
        return maintenanceHistoryRepository.findAll();
    }

    @Override
    public MaintenanceHistory getMaintenanceHistoryById(Long id) {
        Optional<MaintenanceHistory> optional = maintenanceHistoryRepository.findById(id);
        if (optional.isPresent()) {
            return optional.get();
        } else {
            throw new RuntimeException("Không tìm thấy lịch sử bảo trì với ID: " + id);
        }
    }

    @Override
    public void saveMaintenanceHistory(MaintenanceHistory maintenanceHistory) {
        maintenanceHistoryRepository.save(maintenanceHistory);
    }

    @Override
    public void deleteMaintenanceHistory(Long id) {
        maintenanceHistoryRepository.deleteById(id);
    }

    @Override
    public long countMaintenanceHistory() {
        return maintenanceHistoryRepository.count();
    }

    @Override
    public List<MaintenanceHistory> getMaintenanceByAmbulanceId(Long ambulanceId) {
        return maintenanceHistoryRepository.findByAmbulance_Id(ambulanceId);
    }

    @Override
    public List<MaintenanceHistory> getMaintenanceAfterDate(LocalDate fromDate) {
        return maintenanceHistoryRepository.findByMaintenanceDateAfter(fromDate);
    }

    @Override
    public List<MaintenanceHistory> getMaintenanceByType(String maintenanceType) {
        return maintenanceHistoryRepository.findByMaintenanceTypeContainingIgnoreCase(maintenanceType);
    }
}
