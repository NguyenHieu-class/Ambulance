package com.project.Ambulance.repository;

import com.project.Ambulance.model.AmbulanceLog;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;

/**
 * Repository quản lý bảng AmbulanceLog (Nhật ký vận hành xe)
 * Kế thừa JpaRepository có đầy đủ CRUD
 */
@Repository
public interface AmbulanceLogRepository extends JpaRepository<AmbulanceLog, Long> {

    /**
     * Tìm tất cả lịch sử vận hành của một xe cứu thương
     */
    List<AmbulanceLog> findByAmbulance_Id(Long ambulanceId);

    /**
     * Tìm tất cả lịch sử vận hành của một tài xế
     */
    List<AmbulanceLog> findByDriver_Id(Long driverId);

    /**
     * Tìm các chuyến đi trong khoảng thời gian cụ thể (để thống kê nhật ký vận hành)
     */
    List<AmbulanceLog> findByTripStartBetween(LocalDateTime start, LocalDateTime end);

    /**
     * Tìm tất cả log có quãng đường đi lớn hơn X (thống kê các chuyến đi dài)
     */
    List<AmbulanceLog> findByDistanceTraveledGreaterThan(double distance);
}
