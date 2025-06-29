package com.project.Ambulance.repository;

import com.project.Ambulance.model.Driver;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import jakarta.transaction.Transactional;
import java.util.List;

@Repository
public interface DriverRepository extends JpaRepository<Driver, Integer> {

    // Tìm chính xác theo số điện thoại
    Driver findByPhone(String phone);

    // Tìm theo tên (gần đúng, không phân biệt hoa thường)
    List<Driver> findByNameContainingIgnoreCaseOrderByNameAsc(String name);

    // Tìm theo số điện thoại gần đúng
    List<Driver> findByPhoneContainingIgnoreCaseOrderByNameAsc(String phone);

    // Tìm theo số bằng lái gần đúng
    List<Driver> findByLicenseNumberContainingIgnoreCaseOrderByNameAsc(String licenseNumber);

    // Tìm theo trạng thái
    List<Driver> findByStatusOrderByNameAsc(int status);

    // Lấy tất cả tài xế theo tên tăng dần
    List<Driver> findAllByOrderByNameAsc();

    // Tìm theo bệnh viện
    List<Driver> findByHospitalIdHospitalOrderByNameAsc(int hospitalId);

    // Tìm theo bệnh viện và trạng thái
    List<Driver> findByHospitalIdHospitalAndStatusOrderByNameAsc(int hospitalId, int status);

    // Đếm số lượng theo trạng thái
    int countByStatus(int status);

    // Đếm số tài xế theo bệnh viện
    int countByHospitalIdHospital(int hospitalId);

    // Cập nhật trạng thái tài xế
    @Modifying
    @Transactional
    @Query("UPDATE Driver d SET d.status = :status WHERE d.idDriver = :id")
    void updateStatus(@Param("id") int idDriver, @Param("status") int status);
}
