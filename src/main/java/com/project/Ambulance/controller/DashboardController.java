package com.project.Ambulance.controller;

import com.project.Ambulance.model.Ambulance;
import com.project.Ambulance.model.Driver;
import com.project.Ambulance.model.Hospital;
import com.project.Ambulance.model.MedicalStaff;
import com.project.Ambulance.service.AmbulanceService;
import com.project.Ambulance.service.BookingService;
import com.project.Ambulance.service.DriverService;
import com.project.Ambulance.service.HospitalService;
import com.project.Ambulance.service.MedicalStaffService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
public class DashboardController {

    @Autowired
    private AmbulanceService ambulanceService;

    @Autowired
    private BookingService bookingService;

    @Autowired
    private DriverService driverService;

    @Autowired
    private HospitalService hospitalService;

    @Autowired
    private MedicalStaffService medicalStaffService;

    @GetMapping("/admin/dashboard")
    public String adminDashboard(Model model) {
        model.addAttribute("ambulanceCount", ambulanceService.countAll());
        model.addAttribute("driverCount", driverService.countAll());
        model.addAttribute("hospitalCount", hospitalService.countAll());
        model.addAttribute("bookingCount", bookingService.count());
        return "admin/dashboard";
    }

    @GetMapping("/driver/dashboard")
    public String driverDashboard(Model model) {
        model.addAttribute("bookings", bookingService.getAllBookings());
        return "driver/dashboard";
    }

    @GetMapping("/driver/profile")
    public String driverProfile(Model model) {
        Driver driver = driverService.getAllDrivers().stream().findFirst().orElse(null);
        model.addAttribute("driver", driver);
        return "driver/profile";
    }

    @PostMapping("/driver/profile")
    public String updateDriver(@ModelAttribute("driver") Driver driver) {
        driverService.saveDriver(driver);
        return "redirect:/driver/profile";
    }

    @GetMapping("/driver/schedule")
    public String driverSchedule(Model model) {
        model.addAttribute("bookings", bookingService.getAllBookings());
        return "driver/schedule";
    }

    @GetMapping("/medical/dashboard")
    public String medicalDashboard(Model model) {
        model.addAttribute("bookings", bookingService.getAllBookings());
        return "medical/dashboard";
    }

    @GetMapping("/medical/profile")
    public String medicalProfile(Model model) {
        MedicalStaff medicalStaff = medicalStaffService.getAllMedicalStaff()
                .stream()
                .findFirst()
                .orElse(null);
        model.addAttribute("medicalStaff", medicalStaff);
        return "medical/profile";
    }

    @PostMapping("/medical/profile")
    public String updateMedical(@ModelAttribute("medicalStaff") MedicalStaff medicalStaff) {
        medicalStaffService.save(medicalStaff);
        return "redirect:/medical/profile";
    }

    @GetMapping("/medical/schedule")
    public String medicalSchedule(Model model) {
        model.addAttribute("bookings", bookingService.getAllBookings());
        return "medical/schedule";
    }

    @GetMapping("/admin/ambulances")
    public String manageAmbulances(Model model) {
        model.addAttribute("ambulances", ambulanceService.getAllAmbulances());
        return "admin/ambulances";
    }

    @GetMapping("/admin/ambulance/add")
    public String addAmbulanceForm(Model model) {
        model.addAttribute("ambulanceForm", new Ambulance());
        return "admin/ambulance-add";
    }

    @PostMapping("/admin/ambulances")
    public String createAmbulance(@ModelAttribute("ambulanceForm") Ambulance ambulance) {
        ambulanceService.saveAmbulance(ambulance);
        return "redirect:/admin/ambulances";
    }

    @GetMapping("/admin/ambulance/{id}/delete")
    public String deleteAmbulance(@PathVariable int id) {
        ambulanceService.deleteAmbulance(id);
        return "redirect:/admin/ambulances";
    }

    @GetMapping("/admin/ambulance/{id}/edit")
    public String editAmbulanceForm(@PathVariable int id, Model model) {
        Ambulance ambulance = ambulanceService.getAmbulanceById(id);
        model.addAttribute("ambulanceForm", ambulance);
        return "admin/ambulance-edit";
    }

    @PostMapping("/admin/ambulance/{id}/edit")
    public String updateAmbulance(@PathVariable int id,
                                  @ModelAttribute("ambulanceForm") Ambulance ambulance) {
        ambulance.setIdAmbulance(id);
        ambulanceService.saveAmbulance(ambulance);
        return "redirect:/admin/ambulances";
    }

    @GetMapping("/admin/hospitals")
    public String manageHospitals(Model model) {
        model.addAttribute("hospitals", hospitalService.getAllHospitals());
        return "admin/hospitals";
    }

    @GetMapping("/admin/hospital/add")
    public String addHospitalForm(Model model) {
        model.addAttribute("hospitalForm", new Hospital());
        return "admin/hospital-add";
    }

    @PostMapping("/admin/hospitals")
    public String createHospital(@ModelAttribute("hospitalForm") Hospital hospital) {
        hospitalService.saveHospital(hospital);
        return "redirect:/admin/hospitals";
    }

    @GetMapping("/admin/hospital/{id}/delete")
    public String deleteHospital(@PathVariable int id) {
        hospitalService.deleteHospital(id);
        return "redirect:/admin/hospitals";
    }

    @GetMapping("/admin/hospital/{id}/edit")
    public String editHospitalForm(@PathVariable int id, Model model) {
        Hospital hospital = hospitalService.getHospitalById(id);
        model.addAttribute("hospitalForm", hospital);
        return "admin/hospital-edit";
    }

    @PostMapping("/admin/hospital/{id}/edit")
    public String updateHospital(@PathVariable int id,
                                 @ModelAttribute("hospitalForm") Hospital hospital) {
        hospital.setIdHospital(id);
        hospitalService.saveHospital(hospital);
        return "redirect:/admin/hospitals";
    }

    @GetMapping("/admin/drivers")
    public String manageDrivers(Model model) {
        model.addAttribute("drivers", driverService.getAllDrivers());
        return "admin/drivers";
    }

    @GetMapping("/admin/driver/add")
    public String addDriverForm(Model model) {
        model.addAttribute("driverForm", new Driver());
        return "admin/driver-add";
    }

    @PostMapping("/admin/drivers")
    public String createDriver(@ModelAttribute("driverForm") Driver driver) {
        driverService.saveDriver(driver);
        return "redirect:/admin/drivers";
    }

    @GetMapping("/admin/driver/{id}/delete")
    public String deleteDriver(@PathVariable int id) {
        driverService.deleteDriver(id);
        return "redirect:/admin/drivers";
    }

    @GetMapping("/admin/driver/{id}/edit")
    public String editDriverForm(@PathVariable int id, Model model) {
        Driver driver = driverService.getDriverById(id);
        model.addAttribute("driverForm", driver);
        return "admin/driver-edit";
    }

    @PostMapping("/admin/driver/{id}/edit")
    public String updateDriver(@PathVariable int id,
                               @ModelAttribute("driverForm") Driver driver) {
        driver.setIdDriver(id);
        driverService.saveDriver(driver);
        return "redirect:/admin/drivers";
    }

    @GetMapping("/admin/bookings")
    public String bookingHistory(Model model) {
        model.addAttribute("bookings", bookingService.getAllBookings());
        return "admin/booking-history";
    }
}
