package com.project.Ambulance.repository;

import com.project.Ambulance.model.Hospital;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Repository quản lý bảng Hospital (Bệnh viện)
 * Kế thừa JpaRepository có đầy đủ CRUD
 */
@Repository
public interface HospitalRepository extends JpaRepository<Hospital, Long> {

    /**
     * Tìm kiếm bệnh viện chính xác theo tên
     */
    Hospital findByName(String name);

    /**
     * Kiểm tra xem bệnh viện có tồn tại chưa
     */
    boolean existsByName(String name);

    /**
     * Tìm tất cả bệnh viện thuộc một tỉnh (truy vấn qua entity liên kết)
     */
    List<Hospital> findByProvince_Name(String provinceName);

    /**
     * Tìm bệnh viện theo tên tỉnh và quận (kết hợp sâu hơn)
     */
    List<Hospital> findByProvince_NameAndDistrict_Name(String provinceName, String districtName);
}
