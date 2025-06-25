package com.project.Ambulance.repository;

import com.project.Ambulance.model.AmbulanceBrand;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * Repository quản lý thương hiệu xe cứu thương (AmbulanceBrand)
 * Kế thừa JpaRepository tự động có đầy đủ CRUD
 */
@Repository
public interface AmbulanceBrandRepository extends JpaRepository<AmbulanceBrand, Long> {

    /**
     * Tìm kiếm thương hiệu theo tên chính xác
     */
    AmbulanceBrand findByBrandName(String brandName);

    /**
     * Kiểm tra xem thương hiệu đã tồn tại chưa (phục vụ validate khi thêm mới)
     */
    boolean existsByBrandName(String brandName);
}
