package com.project.Ambulance.controller;

import com.project.Ambulance.model.Ambulance;
import com.project.Ambulance.model.MaintenanceHistory;
import com.project.Ambulance.model.User;
import com.project.Ambulance.service.AmbulanceService;
import com.project.Ambulance.service.MaintenanceHistoryService;
import com.project.Ambulance.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.time.LocalDate;
import java.util.List;

@Controller
@RequestMapping("/admin/maintenance-history")
public class MaintenanceHistoryController {

    @Autowired
    private MaintenanceHistoryService maintenanceHistoryService;

    @Autowired
    private AmbulanceService ambulanceService;

    @Autowired
    private UserService userService;

    // Danh sách toàn bộ lịch sử bảo trì
    @GetMapping
    public String listMaintenanceHistories(Model model) {
        model.addAttribute("histories", maintenanceHistoryService.getAllMaintenanceHistory());
        return "admin/pages/maintenanceHistory/list";
    }

    // Form thêm mới lịch sử bảo trì
    @GetMapping("/add")
    public String showAddForm(Model model) {
        model.addAttribute("maintenanceHistory", new MaintenanceHistory());
        model.addAttribute("ambulances", ambulanceService.getAllAmbulance());
        model.addAttribute("action", "Thêm");
        return "admin/pages/maintenanceHistory/form";
    }

    // Form chỉnh sửa lịch sử bảo trì
    @GetMapping("/edit/{id}")
    public String showEditForm(@PathVariable("id") Long id, Model model) {
        MaintenanceHistory history = maintenanceHistoryService.getMaintenanceHistoryById(id);
        model.addAttribute("maintenanceHistory", history);
        model.addAttribute("ambulances", ambulanceService.getAllAmbulance());
        model.addAttribute("action", "Sửa");
        return "admin/pages/maintenanceHistory/form";
    }

    // Xoá lịch sử bảo trì
    @GetMapping("/delete/{id}")
    public String deleteMaintenanceHistory(@PathVariable("id") Long id) {
        maintenanceHistoryService.deleteMaintenanceHistory(id);
        return "redirect:/admin/maintenance-history";
    }

    // Lưu lịch sử bảo trì (Add + Edit)
    @PostMapping("/save")
    public String saveMaintenanceHistory(@ModelAttribute("maintenanceHistory") MaintenanceHistory maintenanceHistory,
                                         @RequestParam("ambulanceId") Long ambulanceId,
                                         RedirectAttributes ra) {

        MaintenanceHistory oldHistory = maintenanceHistoryService.getMaintenanceHistoryById(maintenanceHistory.getId());
        if (oldHistory != null) {
            maintenanceHistory.setMaintenanceDate(oldHistory.getMaintenanceDate());
        }

        maintenanceHistory.setAmbulance(ambulanceService.getAmbulanceById(ambulanceId));
        maintenanceHistoryService.saveMaintenanceHistory(maintenanceHistory);
        return "redirect:/admin/maintenance-history";
    }
}
