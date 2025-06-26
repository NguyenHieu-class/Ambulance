package com.project.Ambulance.controller;

import com.project.Ambulance.model.Driver;
import com.project.Ambulance.model.User;
import com.project.Ambulance.service.DriverService;
import com.project.Ambulance.service.UserService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.List;

@Controller
@RequestMapping("/admin/driver")
public class DriverController {

    @Autowired
    private DriverService driverService;

    @Autowired
    private UserService userService; // dùng để check phân quyền như mẫu cũ

    // Hiển thị danh sách tài xế
    @GetMapping
    public String listDrivers(Model model, HttpServletRequest request) {
        HttpSession session = request.getSession();
        User sessionUser = (User) session.getAttribute("sessionUser");

        if (sessionUser != null && sessionUser.getRole().getRoleName().equals("ADMIN")) {
            model.addAttribute("drivers", driverService.getAllDriver());
            return "admin/pages/driver/list";
        }
        return "redirect:/login";
    }

    // Form thêm mới tài xế
    @GetMapping("/add")
    public String showAddForm(Model model, HttpServletRequest request) {
        HttpSession session = request.getSession();
        User sessionUser = (User) session.getAttribute("sessionUser");

        if (sessionUser != null && sessionUser.getRole().getRoleName().equals("ADMIN")) {
            model.addAttribute("driver", new Driver());
            model.addAttribute("action", "Thêm");
            return "admin/pages/driver/form";
        }
        return "redirect:/login";
    }

    // Form chỉnh sửa tài xế
    @GetMapping("/edit/{id}")
    public String showEditForm(@PathVariable("id") Long id, Model model, HttpServletRequest request) {
        HttpSession session = request.getSession();
        User sessionUser = (User) session.getAttribute("sessionUser");

        if (sessionUser != null && sessionUser.getRole().getRoleName().equals("ADMIN")) {
            Driver driver = driverService.getDriverById(id);
            model.addAttribute("driver", driver);
            model.addAttribute("action", "Sửa");
            return "admin/pages/driver/form";
        }
        return "redirect:/login";
    }

    // Xoá tài xế
    @GetMapping("/delete/{id}")
    public String deleteDriver(@PathVariable("id") Long id, HttpServletRequest request) {
        HttpSession session = request.getSession();
        User sessionUser = (User) session.getAttribute("sessionUser");

        if (sessionUser != null && sessionUser.getRole().getRoleName().equals("ADMIN")) {
            driverService.deleteDriver(id);
            return "redirect:/admin/driver";
        }
        return "redirect:/login";
    }

    // Lưu tài xế (Add + Edit)
    @PostMapping("/save")
    public String saveDriver(@ModelAttribute("driver") Driver driver,
                             RedirectAttributes ra,
                             HttpServletRequest request) {

        HttpSession session = request.getSession();
        User sessionUser = (User) session.getAttribute("sessionUser");

        if (sessionUser != null && sessionUser.getRole().getRoleName().equals("ADMIN")) {

            Driver oldDriver = driverService.getDriverById(driver.getId());
            if (oldDriver != null) {
                driver.setCreateDate(oldDriver.getCreateDate());
            }

            boolean exists = driverService.existsByUsername(driver.getUsername());
            if (exists && (oldDriver == null || !oldDriver.getUsername().equals(driver.getUsername()))) {
                ra.addFlashAttribute("error", "Username tài xế đã tồn tại!");
            } else {
                driverService.saveDriver(driver);
            }
            return "redirect:/admin/driver";
        }
        return "redirect:/login";
    }
}
