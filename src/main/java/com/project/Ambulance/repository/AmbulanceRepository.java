package com.project.Ambulance.repository;

import com.project.Ambulance.model.Ambulance;
import com.project.Ambulance.model.Ambulance.FuelType;
import com.project.Ambulance.model.Ambulance.Status;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;

/**
 * Repository quản lý thực thể Ambulance (xe cứu thương)
 * Kế thừa JpaRepository tự động có đầy đủ CRUD
 */
@Repository
public interface AmbulanceRepository extends JpaRepository<Ambulance, Long> {

    /**
     * Tìm kiếm chính xác theo biển số xe
     */
    Ambulance findByLicensePlate(String licensePlate);

    /**
     * Tìm kiếm danh sách xe theo trạng thái hoạt động
     */
    List<Ambulance> findByStatus(Status status);

    /**
     * Tìm kiếm danh sách xe theo loại nhiên liệu
     */
    List<Ambulance> findByFuelType(FuelType fuelType);

    /**
     * Tìm tất cả xe có bảo trì sau ngày truyền vào (dùng khi cần lọc xe bảo trì gần đây)
     */
    List<Ambulance> findByLastMaintenanceDateAfter(LocalDate date);

    /**
     * Tìm xe có đầy đủ thiết bị monitor và hệ thống oxy (thường dùng cho xe cấp cứu chuyên sâu)
     */
    List<Ambulance> findByMonitorTrueAndOxygenSystemTrue();

    /**
     * Tìm xe có trang bị máy thở (ventilator)
     */
    List<Ambulance> findByVentilatorTrue();

    /**
     * Tìm xe có số giường bệnh lớn hơn số chỉ định
     */
    List<Ambulance> findByCapacityGreaterThan(int capacity);

    /**
     * Tìm xe theo tên thương hiệu (bảng liên kết AmbulanceBrand)
     */
    List<Ambulance> findByAmbulanceBrand_BrandName(String brandName);
}
