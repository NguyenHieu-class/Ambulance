package com.project.Ambulance.repository;

import com.project.Ambulance.model.MaintenanceHistory;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;

/**
 * Repository quản lý bảng MaintenanceHistory (Lịch sử bảo trì)
 * Kế thừa JpaRepository có đầy đủ CRUD
 */
@Repository
public interface MaintenanceHistoryRepository extends JpaRepository<MaintenanceHistory, Long> {

    /**
     * Tìm tất cả lịch sử bảo trì của một xe cứu thương
     */
    List<MaintenanceHistory> findByAmbulance_Id(Long ambulanceId);

    /**
     * Tìm các lần bảo trì gần đây (từ ngày X trở đi)
     */
    List<MaintenanceHistory> findByMaintenanceDateAfter(LocalDate fromDate);

    /**
     * Tìm các lần bảo trì thuộc một loại bảo trì cụ thể (ví dụ: Thay dầu, kiểm tra hệ thống phanh,...)
     */
    List<MaintenanceHistory> findByMaintenanceTypeContainingIgnoreCase(String maintenanceType);
}
