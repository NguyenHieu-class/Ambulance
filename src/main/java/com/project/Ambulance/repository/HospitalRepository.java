package com.project.Ambulance.repository;

import com.project.Ambulance.model.Hospital;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import jakarta.transaction.Transactional;
import java.util.List;

@Repository
public interface HospitalRepository extends JpaRepository<Hospital, Integer> {

    // Tìm bệnh viện theo tên
    Hospital findByName(String name);

    // Đếm số bệnh viện theo tỉnh
    int countByProvinceIdProvince(int idProvince);

    // Lấy danh sách bệnh viện theo tỉnh, sắp xếp theo tên
    List<Hospital> findByProvinceIdProvinceOrderByNameAsc(int idProvince);

    // Lấy tất cả bệnh viện có tên chứa từ khoá
    List<Hospital> findByNameContainingIgnoreCaseOrderByNameAsc(String name);

    // Cập nhật tên giám đốc
    @Modifying
    @Transactional
    @Query("UPDATE Hospital h SET h.directorName = :directorName WHERE h.idHospital = :id")
    void updateDirectorName(@Param("id") int id, @Param("directorName") String directorName);

    // Lấy các bệnh viện có chứa địa chỉ giống như từ khoá (gợi ý tìm kiếm)
    List<Hospital> findByAddressDetailContainingIgnoreCaseOrderByNameAsc(String address);

    // Đếm tổng số bệnh viện
    long count();
}
