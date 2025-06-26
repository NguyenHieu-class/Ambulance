package com.project.Ambulance.controller;

import com.project.Ambulance.model.MedicalStaff;
import com.project.Ambulance.service.MedicalStaffService;
import com.project.Ambulance.model.User;
import com.project.Ambulance.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.time.LocalDate;
import java.util.List;

@Controller
@RequestMapping("/admin/medical-staff")
public class MedicalStaffController {

    @Autowired
    private MedicalStaffService medicalStaffService;

    @Autowired
    private UserService userService; // để kiểm tra quyền đăng nhập

    // Hiển thị danh sách nhân viên y tế
    @GetMapping
    public String listMedicalStaff(Model model) {
        model.addAttribute("medicalStaffs", medicalStaffService.getAllMedicalStaff());
        return "admin/pages/medicalStaff/list";
    }

    // Hiển thị form thêm mới nhân viên y tế
    @GetMapping("/add")
    public String showAddForm(Model model) {
        model.addAttribute("medicalStaff", new MedicalStaff());
        model.addAttribute("action", "Thêm");
        return "admin/pages/medicalStaff/form";
    }

    // Hiển thị form chỉnh sửa nhân viên y tế
    @GetMapping("/edit/{id}")
    public String showEditForm(@PathVariable("id") Long id, Model model) {
        MedicalStaff staff = medicalStaffService.getMedicalStaffById(id);
        model.addAttribute("medicalStaff", staff);
        model.addAttribute("action", "Sửa");
        return "admin/pages/medicalStaff/form";
    }

    // Xoá nhân viên y tế
    @GetMapping("/delete/{id}")
    public String deleteMedicalStaff(@PathVariable("id") Long id) {
        medicalStaffService.deleteMedicalStaff(id);
        return "redirect:/admin/medical-staff";
    }

    // Lưu nhân viên y tế (Add + Edit)
    @PostMapping("/save")
    public String saveMedicalStaff(@ModelAttribute("medicalStaff") MedicalStaff medicalStaff,
                                   RedirectAttributes ra) {

        MedicalStaff oldStaff = medicalStaffService.getMedicalStaffById(medicalStaff.getId());
        if (oldStaff != null) {
            medicalStaff.setCreateDate(oldStaff.getCreateDate());
        } else {
            medicalStaff.setCreateDate(LocalDate.now());
        }
        medicalStaff.setUpdateDate(LocalDate.now());

        boolean exists = medicalStaffService.existsByUsername(medicalStaff.getUsername());
        if (exists && (oldStaff == null || !oldStaff.getUsername().equals(medicalStaff.getUsername()))) {
            ra.addFlashAttribute("error", "Username đã tồn tại!");
        } else {
            medicalStaffService.saveMedicalStaff(medicalStaff);
        }
        return "redirect:/admin/medical-staff";
    }
}
