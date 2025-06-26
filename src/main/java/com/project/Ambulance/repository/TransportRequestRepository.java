package com.project.Ambulance.repository;

import com.project.Ambulance.model.TransportRequest;
import com.project.Ambulance.model.TransportRequest.Status;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Repository quản lý bảng TransportRequest (Yêu cầu vận chuyển)
 * Kế thừa JpaRepository có đầy đủ CRUD
 */
@Repository
public interface TransportRequestRepository extends JpaRepository<TransportRequest, Long> {

    /**
     * Tìm tất cả yêu cầu vận chuyển theo trạng thái
     * Ví dụ: tìm tất cả yêu cầu đang chờ xử lý (PENDING)
     */
    List<TransportRequest> findByStatus(Status status);

    /**
     * Tìm tất cả yêu cầu của một nhân viên y tế cụ thể
     */
    List<TransportRequest> findByRequester_Id(Long medicalStaffId);

    /**
     * Tìm yêu cầu vận chuyển đã phân công cho một tài xế
     */
    List<TransportRequest> findByAssignedDriver_Id(Long driverId);

    /**
     * Tìm yêu cầu vận chuyển của một bệnh viện cụ thể
     */
    List<TransportRequest> findByHospital_Id(Long hospitalId);
}
