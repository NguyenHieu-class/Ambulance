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
}
