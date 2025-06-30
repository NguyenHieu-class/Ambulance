package com.project.Ambulance.repository;

import com.project.Ambulance.model.MedicalStaff;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import jakarta.transaction.Transactional;
import java.util.List;

@Repository
public interface MedicalStaffRepository extends JpaRepository<MedicalStaff, Integer> {

    // Lấy tất cả theo tên tăng dần
    List<MedicalStaff> findAllByOrderByNameAsc();

    // Tìm chính xác theo số điện thoại
    MedicalStaff findByPhone(String phone);

    // Tìm gần đúng theo số điện thoại (bổ sung)
    List<MedicalStaff> findByPhoneContainingIgnoreCaseOrderByNameAsc(String phone);

    // Tìm theo tên gần đúng
    List<MedicalStaff> findByNameContainingIgnoreCaseOrderByNameAsc(String name);

    // Tìm theo số giấy phép hành nghề
    List<MedicalStaff> findByLicenseNumberContainingIgnoreCaseOrderByNameAsc(String licenseNumber);

    // Tìm theo trạng thái
    List<MedicalStaff> findByStatusOrderByNameAsc(int status);


    // Tìm theo bệnh viện
    List<MedicalStaff> findByHospitalIdHospitalOrderByNameAsc(int hospitalId);

    // Tìm theo bệnh viện + trạng thái
    List<MedicalStaff> findByHospitalIdHospitalAndStatusOrderByNameAsc(int hospitalId, int status);

    // Cập nhật trạng thái
    @Modifying
    @Transactional
    @Query("UPDATE MedicalStaff m SET m.status = :status WHERE m.idMedicalStaff = :id")
    void updateStatus(@Param("id") int id, @Param("status") int status);

    // Đếm theo bệnh viện
    int countByHospitalIdHospital(int hospitalId);

    long count();

    // Lấy danh sách theo nhiều id
    List<MedicalStaff> findByIdMedicalStaffIn(List<Integer> ids);
}
