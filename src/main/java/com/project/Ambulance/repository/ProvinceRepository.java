package com.project.Ambulance.repository;

import com.project.Ambulance.model.Province;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * Repository quản lý bảng Province (Tỉnh/Thành phố)
 * Kế thừa JpaRepository có đầy đủ CRUD
 */
@Repository
public interface ProvinceRepository extends JpaRepository<Province, Long> {

    /**
     * Tìm kiếm tỉnh/thành theo tên
     */
    Province findByName(String name);

    /**
     * Kiểm tra xem tỉnh có tồn tại chưa
     */
    boolean existsByName(String name);
}
