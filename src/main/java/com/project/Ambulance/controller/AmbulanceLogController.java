package com.project.Ambulance.controller;

import com.project.Ambulance.model.Ambulance;
import com.project.Ambulance.model.AmbulanceLog;
import com.project.Ambulance.model.Driver;
import com.project.Ambulance.model.User;
import com.project.Ambulance.service.AmbulanceLogService;
import com.project.Ambulance.service.AmbulanceService;
import com.project.Ambulance.service.DriverService;
import com.project.Ambulance.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.time.LocalDateTime;
import java.util.List;

@Controller
@RequestMapping("/admin/ambulance-log")
public class AmbulanceLogController {

    @Autowired
    private AmbulanceLogService ambulanceLogService;

    @Autowired
    private AmbulanceService ambulanceService;

    @Autowired
    private DriverService driverService;

    @Autowired
    private UserService userService;

    // Danh sách toàn bộ nhật ký vận hành
    @GetMapping
    public String listAmbulanceLogs(Model model) {
        model.addAttribute("logs", ambulanceLogService.getAllAmbulanceLog());
        return "admin/pages/ambulanceLog/list";
    }

    // Form thêm mới nhật ký vận hành
    @GetMapping("/add")
    public String showAddForm(Model model) {
        model.addAttribute("ambulanceLog", new AmbulanceLog());
        model.addAttribute("ambulances", ambulanceService.getAllAmbulance());
        model.addAttribute("drivers", driverService.getAllDriver());
        model.addAttribute("action", "Thêm");
        return "admin/pages/ambulanceLog/form";
    }

    // Form chỉnh sửa nhật ký vận hành
    @GetMapping("/edit/{id}")
    public String showEditForm(@PathVariable("id") Long id, Model model) {
        AmbulanceLog log = ambulanceLogService.getAmbulanceLogById(id);
        model.addAttribute("ambulanceLog", log);
        model.addAttribute("ambulances", ambulanceService.getAllAmbulance());
        model.addAttribute("drivers", driverService.getAllDriver());
        model.addAttribute("action", "Sửa");
        return "admin/pages/ambulanceLog/form";
    }

    // Xoá nhật ký vận hành
    @GetMapping("/delete/{id}")
    public String deleteAmbulanceLog(@PathVariable("id") Long id) {
        ambulanceLogService.deleteAmbulanceLog(id);
        return "redirect:/admin/ambulance-log";
    }

    // Lưu nhật ký vận hành (Add + Edit)
    @PostMapping("/save")
    public String saveAmbulanceLog(@ModelAttribute("ambulanceLog") AmbulanceLog ambulanceLog,
                                   @RequestParam("ambulanceId") Long ambulanceId,
                                   @RequestParam("driverId") Long driverId,
                                   RedirectAttributes ra) {

        AmbulanceLog oldLog = ambulanceLogService.getAmbulanceLogById(ambulanceLog.getId());
        if (oldLog != null) {
            ambulanceLog.setTripStart(oldLog.getTripStart());
        } else {
            ambulanceLog.setTripStart(LocalDateTime.now());
        }

        ambulanceLog.setAmbulance(ambulanceService.getAmbulanceById(ambulanceId));
        ambulanceLog.setDriver(driverService.getDriverById(driverId));

        ambulanceLogService.saveAmbulanceLog(ambulanceLog);
        return "redirect:/admin/ambulance-log";
    }
}
