package com.project.Ambulance.repository;

import com.project.Ambulance.model.Booking;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import jakarta.transaction.Transactional;
import java.util.Date;
import java.util.List;

@Repository
public interface BookingRepository extends JpaRepository<Booking, Integer> {

    // Tất cả booking mới nhất trước
    List<Booking> findAllByOrderByRequestTimeDesc();

    // Tìm theo trạng thái
    List<Booking> findByStatusOrderByRequestTimeDesc(int status);

    // Tìm theo xe cứu thương
    List<Booking> findByAmbulanceIdAmbulanceOrderByRequestTimeDesc(int ambulanceId);

    // Tìm theo người tạo (user)
    List<Booking> findByUserIdUserOrderByRequestTimeDesc(int userId);

    // Tìm theo khoảng thời gian
    List<Booking> findByRequestTimeBetweenOrderByRequestTimeDesc(Date start, Date end);

    // Đếm số ca theo trạng thái
    int countByStatus(int status);

    // Cập nhật trạng thái
    @Modifying
    @Transactional
    @Query("UPDATE Booking b SET b.status = :status WHERE b.idBooking = :id")
    void updateStatus(@Param("id") int idBooking, @Param("status") int status);

    // Tìm kiếm theo tên người yêu cầu và trạng thái
    List<Booking> findByRequesterNameContainingIgnoreCaseAndStatusOrderByRequestTimeDesc(String name, int status);

    // Tổng số booking
    long count();
}
