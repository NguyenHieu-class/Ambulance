package com.project.Ambulance.repository;

import com.project.Ambulance.model.Driver;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Repository quản lý bảng Driver (Tài xế)
 * Kế thừa JpaRepository có đầy đủ CRUD
 */
@Repository
public interface DriverRepository extends JpaRepository<Driver, Long> {

    /**
     * Tìm kiếm tài xế theo username (phục vụ cho đăng nhập, phân quyền)
     */
    Driver findByUsername(String username);

    /**
     * Kiểm tra xem username tài xế có tồn tại chưa (phục vụ validate khi tạo mới tài khoản)
     */
    boolean existsByUsername(String username);

    /**
     * Tìm kiếm tài xế có số năm kinh nghiệm lớn hơn x năm
     */
    List<Driver> findByYearsOfExperienceGreaterThan(int years);

    /**
     * Tìm kiếm tài xế có bằng lái loại cụ thể (nếu cần mở rộng quản lý nhiều loại bằng lái)
     */
    List<Driver> findByLicenseNumber(String licenseNumber);
}
