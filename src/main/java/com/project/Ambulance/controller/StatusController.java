package com.project.Ambulance.controller;

import com.project.Ambulance.constants.AppConstants;
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

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

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

    private Map<Integer, String> ambulanceStatusMap() {
        Map<Integer, String> map = new LinkedHashMap<>();
        map.put(AppConstants.AMBULANCE_STATUS_ACTIVE, "Hoạt động");
        map.put(AppConstants.AMBULANCE_STATUS_MAINTENANCE, "Bảo trì");
        map.put(AppConstants.AMBULANCE_STATUS_BROKEN, "Hỏng");
        map.put(AppConstants.AMBULANCE_STATUS_DECOMMISSIONED, "Dừng sử dụng");
        return map;
    }

    private Map<Integer, String> driverStatusMap() {
        Map<Integer, String> map = new LinkedHashMap<>();
        map.put(AppConstants.DRIVER_STATUS_AVAILABLE, "Đang rảnh");
        map.put(AppConstants.DRIVER_STATUS_ON_DUTY, "Đang làm nhiệm vụ");
        map.put(AppConstants.DRIVER_STATUS_SUSPENDED, "Tạm ngưng hoạt động");
        return map;
    }

    private Map<Integer, String> medicalStatusMap() {
        Map<Integer, String> map = new LinkedHashMap<>();
        map.put(AppConstants.MEDICAL_STATUS_AVAILABLE, "Rảnh");
        map.put(AppConstants.MEDICAL_STATUS_ON_DUTY, "Đang làm việc");
        map.put(AppConstants.MEDICAL_STATUS_SUSPENDED, "Tạm nghỉ");
        return map;
    }

    private Map<Integer, String> bookingStatusMap() {
        Map<Integer, String> map = new LinkedHashMap<>();
        map.put(AppConstants.STATUS_PENDING, "Chờ xác nhận");
        map.put(AppConstants.STATUS_IN_PROGRESS, "Đang xử lý");
        map.put(AppConstants.STATUS_COMPLETED, "Hoàn thành");
        map.put(AppConstants.STATUS_CANCELLED, "Hủy");
        return map;
    }

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

        model.addAttribute("ambulanceStatusMap", ambulanceStatusMap());
        model.addAttribute("driverStatusMap", driverStatusMap());
        model.addAttribute("medicalStatusMap", medicalStatusMap());
        model.addAttribute("bookingStatusMap", bookingStatusMap());

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
