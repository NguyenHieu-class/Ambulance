package com.project.Ambulance.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class DashboardController {

    @GetMapping("/admin/dashboard")
    public String adminDashboard() {
        return "admin/dashboard";
    }

    @GetMapping("/driver/dashboard")
    public String driverDashboard() {
        return "driver/dashboard";
    }

    @GetMapping("/medical/dashboard")
    public String medicalDashboard() {
        return "medical/dashboard";
    }

    @GetMapping("/admin/ambulances")
    public String manageAmbulances() {
        return "admin/ambulances";
    }

    @GetMapping("/admin/hospitals")
    public String manageHospitals() {
        return "admin/hospitals";
    }

    @GetMapping("/admin/drivers")
    public String manageDrivers() {
        return "admin/drivers";
    }

    @GetMapping("/admin/bookings")
    public String bookingHistory() {
        return "admin/booking-history";
    }
}
