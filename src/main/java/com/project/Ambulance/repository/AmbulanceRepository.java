package com.project.Ambulance.repository;

import com.project.Ambulance.model.Ambulance;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import jakarta.transaction.Transactional;
import java.util.List;

@Repository
public interface AmbulanceRepository extends JpaRepository<Ambulance, Integer> {

    // Lấy danh sách xe theo tên, sắp xếp tăng dần
    List<Ambulance> findAllByOrderByNameAsc();

    // Tìm xe theo biển số
    Ambulance findByLicensePlate(String licensePlate);

    // Tìm xe theo trạng thái
    List<Ambulance> findByStatusOrderByNameAsc(int status);

    // Tìm xe theo bệnh viện
    List<Ambulance> findByHospitalIdHospitalOrderByNameAsc(int idHospital);

    // Tìm xe theo tài xế
    List<Ambulance> findByDriverIdDriverOrderByNameAsc(int idDriver);

    // Tìm xe theo từ khoá trong vị trí hiện tại
    List<Ambulance> findByCurrentLocationContainingIgnoreCaseOrderByNameAsc(String keyword);

    // Đếm số xe theo trạng thái
    int countByStatus(int status);

    // Cập nhật trạng thái xe
    @Modifying
    @Transactional
    @Query("UPDATE Ambulance a SET a.status = :status WHERE a.idAmbulance = :id")
    void updateStatus(@Param("id") int idAmbulance, @Param("status") int status);

    // Tìm các xe theo trạng thái và thuộc một bệnh viện
    List<Ambulance> findByHospitalIdHospitalAndStatusOrderByNameAsc(int idHospital, int status);

    // Đếm số lượng xe theo bệnh viện
    int countByHospitalIdHospital(int idHospital);

    // Tìm xe có thiết bị y tế và đang hoạt động
    List<Ambulance> findByMedicalEquipmentTrueAndStatusOrderByNameAsc(int status);

    // Đếm tổng số xe cứu thương
    long count();
}
