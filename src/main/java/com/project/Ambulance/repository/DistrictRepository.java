package com.project.Ambulance.repository;

import com.project.Ambulance.model.District;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.List;

/**
 * Repository quản lý bảng District (Quận/Huyện)
 * Kế thừa JpaRepository có đầy đủ CRUD
 */
@Repository
public interface DistrictRepository extends JpaRepository<District, Long> {

    /**
     * Tìm kiếm quận/huyện theo tên
     */
    District findByName(String name);

    /**
     * Kiểm tra xem quận/huyện có tồn tại chưa
     */
    boolean existsByName(String name);

    /**
     * Tìm các quận/huyện thuộc một tỉnh (tìm qua liên kết Province)
     */
    List<District> findByProvince_Id(Long provinceId);
}
