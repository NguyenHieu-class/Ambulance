package com.project.Ambulance.controller;

import com.project.Ambulance.model.*;
import com.project.Ambulance.service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.time.LocalDate;
import java.util.List;

@Controller
@RequestMapping("/admin/schedule")
public class ScheduleController {

    @Autowired
    private ScheduleService scheduleService;

    @Autowired
    private AmbulanceService ambulanceService;

    @Autowired
    private DriverService driverService;

    @Autowired
    private UserService userService;

    // Hiển thị danh sách lịch trực
    @GetMapping
    public String listSchedules(Model model) {
        model.addAttribute("schedules", scheduleService.getAllSchedule());
        return "admin/pages/schedule/list";
    }

    // Form thêm mới lịch trực
    @GetMapping("/add")
    public String showAddForm(Model model) {
        model.addAttribute("schedule", new Schedule());
        model.addAttribute("ambulances", ambulanceService.getAllAmbulance());
        model.addAttribute("drivers", driverService.getAllDriver());
        model.addAttribute("shifts", Schedule.Shift.values());
        model.addAttribute("action", "Thêm");
        return "admin/pages/schedule/form";
    }

    // Form chỉnh sửa lịch trực
    @GetMapping("/edit/{id}")
    public String showEditForm(@PathVariable("id") Long id, Model model) {
        Schedule schedule = scheduleService.getScheduleById(id);
        model.addAttribute("schedule", schedule);
        model.addAttribute("ambulances", ambulanceService.getAllAmbulance());
        model.addAttribute("drivers", driverService.getAllDriver());
        model.addAttribute("shifts", Schedule.Shift.values());
        model.addAttribute("action", "Sửa");
        return "admin/pages/schedule/form";
    }

    // Xoá lịch trực
    @GetMapping("/delete/{id}")
    public String deleteSchedule(@PathVariable("id") Long id) {
        scheduleService.deleteSchedule(id);
        return "redirect:/admin/schedule";
    }

    // Lưu lịch trực (Add + Edit)
    @PostMapping("/save")
    public String saveSchedule(@ModelAttribute("schedule") Schedule schedule,
                               @RequestParam("ambulanceId") Long ambulanceId,
                               @RequestParam("driverId") Long driverId,
                               RedirectAttributes ra) {

        Schedule oldSchedule = scheduleService.getScheduleById(schedule.getId());
        if (oldSchedule != null) {
            schedule.setDate(oldSchedule.getDate());
        }

        boolean ambulanceConflict = scheduleService.existsByAmbulanceAndDateAndShift(ambulanceId, schedule.getDate(), schedule.getShift());
        boolean driverConflict = scheduleService.existsByDriverAndDateAndShift(driverId, schedule.getDate(), schedule.getShift());

        if (ambulanceConflict) {
            ra.addFlashAttribute("error", "Xe cứu thương đã được phân công trong ca này!");
            return "redirect:/admin/schedule/add";
        }

        if (driverConflict) {
            ra.addFlashAttribute("error", "Tài xế đã được phân công trong ca này!");
            return "redirect:/admin/schedule/add";
        }

        schedule.setAmbulance(ambulanceService.getAmbulanceById(ambulanceId));
        schedule.setDriver(driverService.getDriverById(driverId));

        scheduleService.saveSchedule(schedule);
        return "redirect:/admin/schedule";
    }
}
