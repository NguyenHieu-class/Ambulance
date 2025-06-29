package com.project.Ambulance.service;

import com.project.Ambulance.model.Booking;

import java.util.Date;
import java.util.List;

public interface BookingService {

    List<Booking> getAllBookings();

    Booking getBookingById(int id);

    List<Booking> getBookingsByStatus(int status);

    List<Booking> getBookingsByUser(int userId);

    List<Booking> getBookingsByAmbulance(int ambulanceId);

    List<Booking> getBookingsByDateRange(Date start, Date end);

    Booking saveBooking(Booking booking);

    void deleteBooking(int id);

    void updateStatus(int id, int status);

    long count();

    int countByStatus(int status);
}
