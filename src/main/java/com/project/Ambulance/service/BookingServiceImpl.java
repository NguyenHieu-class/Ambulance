package com.project.Ambulance.service;

import com.project.Ambulance.model.Booking;
import com.project.Ambulance.repository.BookingRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
public class BookingServiceImpl implements BookingService {

    @Autowired
    private BookingRepository bookingRepository;

    @Override
    public List<Booking> getAllBookings() {
        return bookingRepository.findAllByOrderByRequestTimeDesc();
    }

    @Override
    public Booking getBookingById(int id) {
        return bookingRepository.findById(id).orElse(null);
    }

    @Override
    public List<Booking> getBookingsByStatus(int status) {
        return bookingRepository.findByStatusOrderByRequestTimeDesc(status);
    }

    @Override
    public List<Booking> getBookingsByUser(int userId) {
        return bookingRepository.findByUserIdUserOrderByRequestTimeDesc(userId);
    }

    @Override
    public List<Booking> getBookingsByAmbulance(int ambulanceId) {
        return bookingRepository.findByAmbulanceIdAmbulanceOrderByRequestTimeDesc(ambulanceId);
    }

    @Override
    public List<Booking> getBookingsByDateRange(Date start, Date end) {
        return bookingRepository.findByRequestTimeBetweenOrderByRequestTimeDesc(start, end);
    }

    @Override
    public Booking saveBooking(Booking booking) {
        return bookingRepository.save(booking);
    }

    @Override
    public void deleteBooking(int id) {
        bookingRepository.deleteById(id);
    }

    @Override
    public void updateStatus(int id, int status) {
        bookingRepository.updateStatus(id, status);
    }

    @Override
    public long count() {
        return bookingRepository.count();
    }

    @Override
    public int countByStatus(int status) {
        return bookingRepository.countByStatus(status);
    }
}
