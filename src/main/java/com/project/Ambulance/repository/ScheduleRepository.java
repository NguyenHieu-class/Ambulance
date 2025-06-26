package com.project.Ambulance.repository;

import com.project.Ambulance.model.Schedule;
import com.project.Ambulance.model.Schedule.Shift;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;

/**
 * Repository quản lý bảng Schedule (Lịch trực)
 * Kế thừa JpaRepository có đầy đủ CRUD
 */
@Repository
public interface ScheduleRepository extends JpaRepository<Schedule, Long> {

    /**
     * Tìm lịch trực theo ngày
     */
    List<Schedule> findByDate(LocalDate date);

    /**
     * Tìm lịch trực theo ngày và ca trực (ca sáng, chiều, tối)
     */
    List<Schedule> findByDateAndShift(LocalDate date, Shift shift);

    /**
     * Tìm tất cả lịch trực của một xe cứu thương cụ thể
     */
    List<Schedule> findByAmbulance_Id(Long ambulanceId);

    /**
     * Tìm tất cả lịch trực của một tài xế cụ thể
     */
    List<Schedule> findByDriver_Id(Long driverId);

    /**
     * Kiểm tra xem xe có bị phân công trùng lịch hay chưa (để validate tránh trùng ca)
     */
    boolean existsByDateAndShiftAndAmbulance_Id(LocalDate date, Shift shift, Long ambulanceId);

    /**
     * Kiểm tra xem tài xế có bị phân công trùng lịch hay chưa (để validate)
     */
    boolean existsByDateAndShiftAndDriver_Id(LocalDate date, Shift shift, Long driverId);
}
