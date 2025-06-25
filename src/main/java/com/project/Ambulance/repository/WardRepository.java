package com.project.Ambulance.repository;

import com.project.Ambulance.model.Ward;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Repository quản lý bảng Ward (Phường/Xã)
 * Kế thừa JpaRepository có đầy đủ CRUD
 */
@Repository
public interface WardRepository extends JpaRepository<Ward, Long> {

    /**
     * Tìm kiếm phường/xã theo tên
     */
    Ward findByName(String name);

    /**
     * Kiểm tra xem phường/xã có tồn tại chưa
     */
    boolean existsByName(String name);

    /**
     * Tìm các phường/xã thuộc một quận/huyện (tìm qua liên kết District)
     */
    List<Ward> findByDistrict_Id(Long districtId);
}
