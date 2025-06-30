package com.project.Ambulance.controller;

import com.project.Ambulance.model.Booking;
import com.project.Ambulance.service.BookingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.List;

@RestController
@RequestMapping("/api")
public class BookingController {

    @Autowired
    private BookingService bookingService;

    // === ADMIN APIs ===
    @GetMapping("/admin/bookings")
    public ResponseEntity<?> getAllBookings() {
        List<Booking> bookings = bookingService.getAllBookings();
        return ResponseEntity.ok(bookings);
    }

    @GetMapping("/admin/booking/{id}")
    public ResponseEntity<?> getBookingById(@PathVariable int id) {
        Booking booking = bookingService.getBookingById(id);
        if (booking == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(booking);
    }

    @PostMapping("/admin/bookings")
    public ResponseEntity<?> createBooking(@RequestBody Booking booking) {
        Booking saved = bookingService.saveBooking(booking);
        return ResponseEntity.ok(saved);
    }

    @PutMapping("/admin/booking/{id}")
    public ResponseEntity<?> updateBooking(@PathVariable int id,
                                           @RequestBody Booking booking) {
        booking.setIdBooking(id);
        Booking updated = bookingService.saveBooking(booking);
        return ResponseEntity.ok(updated);
    }

    @DeleteMapping("/admin/booking/{id}")
    public ResponseEntity<?> deleteBooking(@PathVariable int id) {
        bookingService.deleteBooking(id);
        return ResponseEntity.noContent().build();
    }

    @PutMapping("/admin/booking/{id}/status")
    public ResponseEntity<?> updateBookingStatus(@PathVariable int id,
                                                 @RequestParam int status) {
        try {
            bookingService.updateStatus(id, status);
            return ResponseEntity.ok().build();
        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest().build();
        }
    }

    @GetMapping("/admin/bookings/search")
    public ResponseEntity<?> searchBookings(
            @RequestParam(required = false) Integer status,
            @RequestParam(required = false) Integer userId,
            @RequestParam(required = false) Integer ambulanceId,
            @RequestParam(required = false) @DateTimeFormat(pattern = "yyyy-MM-dd") Date startDate,
            @RequestParam(required = false) @DateTimeFormat(pattern = "yyyy-MM-dd") Date endDate) {
        List<Booking> bookings;
        if (status != null) {
            bookings = bookingService.getBookingsByStatus(status);
        } else if (userId != null) {
            bookings = bookingService.getBookingsByUser(userId);
        } else if (ambulanceId != null) {
            bookings = bookingService.getBookingsByAmbulance(ambulanceId);
        } else if (startDate != null && endDate != null) {
            bookings = bookingService.getBookingsByDateRange(startDate, endDate);
        } else {
            bookings = bookingService.getAllBookings();
        }
        return ResponseEntity.ok(bookings);
    }
}
