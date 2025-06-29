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
    List<Driver> findByFullNameContainingIgnoreCaseOrderByFullNameAsc(String fullName);

    // Tìm theo số điện thoại gần đúng
    List<Driver> findByPhoneContainingIgnoreCaseOrderByFullNameAsc(String phone);

    // Tìm theo số bằng lái gần đúng
    List<Driver> findByLicenseNumberContainingIgnoreCaseOrderByFullNameAsc(String licenseNumber);

    // Tìm theo trạng thái
    List<Driver> findByStatusOrderByFullNameAsc(int status);

    // Lấy tất cả tài xế theo tên tăng dần
    List<Driver> findAllByOrderByFullNameAsc();

    // Tìm theo bệnh viện
    List<Driver> findByHospitalIdHospitalOrderByFullNameAsc(int hospitalId);

    // Tìm theo bệnh viện và trạng thái
    List<Driver> findByHospitalIdHospitalAndStatusOrderByFullNameAsc(int hospitalId, int status);

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
