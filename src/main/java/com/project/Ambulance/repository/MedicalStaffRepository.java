package com.project.Ambulance.repository;

import com.project.Ambulance.model.MedicalStaff;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Repository quản lý bảng MedicalStaff (Nhân viên y tế)
 * Kế thừa JpaRepository có đầy đủ CRUD
 */
@Repository
public interface MedicalStaffRepository extends JpaRepository<MedicalStaff, Long> {

    /**
     * Tìm kiếm nhân viên y tế theo username (để phục vụ đăng nhập, kiểm tra tài khoản)
     */
    MedicalStaff findByUsername(String username);

    /**
     * Kiểm tra xem username có tồn tại chưa
     */
    boolean existsByUsername(String username);

    /**
     * Tìm kiếm tất cả nhân viên y tế thuộc khoa phòng cụ thể
     */
    List<MedicalStaff> findByDepartment(String department);
}
