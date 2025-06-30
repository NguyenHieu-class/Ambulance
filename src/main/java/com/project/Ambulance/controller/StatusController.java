package com.project.Ambulance.controller;

import com.project.Ambulance.util.StatusMappingUtil;
import com.project.Ambulance.model.Ambulance;
import com.project.Ambulance.model.Driver;
import com.project.Ambulance.model.MedicalStaff;
import com.project.Ambulance.model.Booking;
import com.project.Ambulance.service.AmbulanceService;
import com.project.Ambulance.service.DriverService;
import com.project.Ambulance.service.MedicalStaffService;
import com.project.Ambulance.service.BookingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
public class StatusController {

    @Autowired
    private AmbulanceService ambulanceService;

    @Autowired
    private DriverService driverService;

    @Autowired
    private MedicalStaffService medicalStaffService;

    @Autowired
    private BookingService bookingService;


    @GetMapping("/admin/status")
    public String statusPage(Model model) {
        List<Ambulance> ambulances = ambulanceService.getAllAmbulances();
        List<Driver> drivers = driverService.getAllDrivers();
        List<MedicalStaff> medicalStaff = medicalStaffService.getAllMedicalStaff();
        List<Booking> bookings = bookingService.getAllBookings();

        model.addAttribute("ambulances", ambulances);
        model.addAttribute("drivers", drivers);
        model.addAttribute("medicalStaff", medicalStaff);
        model.addAttribute("bookings", bookings);

        model.addAttribute("ambulanceStatusMap", StatusMappingUtil.ambulanceStatusMap());
        model.addAttribute("driverStatusMap", StatusMappingUtil.driverStatusMap());
        model.addAttribute("medicalStatusMap", StatusMappingUtil.medicalStatusMap());
        model.addAttribute("bookingStatusMap", StatusMappingUtil.bookingStatusMap());

        return "pages/status/index.status";
    }

    @PostMapping("/admin/status/ambulance/{id}")
    public String updateAmbulanceStatus(@PathVariable int id, @RequestParam int status) {
        ambulanceService.updateStatus(id, status);
        return "redirect:/admin/status";
    }

    @PostMapping("/admin/status/driver/{id}")
    public String updateDriverStatus(@PathVariable int id, @RequestParam int status) {
        driverService.updateStatus(id, status);
        return "redirect:/admin/status";
    }

    @PostMapping("/admin/status/medical/{id}")
    public String updateMedicalStatus(@PathVariable int id, @RequestParam int status) {
        medicalStaffService.updateStatus(id, status);
        return "redirect:/admin/status";
    }

    @PostMapping("/admin/status/booking/{id}")
    public String updateBookingStatus(@PathVariable int id, @RequestParam int status) {
        bookingService.updateStatus(id, status);
        return "redirect:/admin/status";
    }
}
