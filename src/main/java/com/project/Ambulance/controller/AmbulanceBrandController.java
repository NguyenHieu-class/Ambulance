package com.project.Ambulance.controller;

import com.project.Ambulance.model.AmbulanceBrand;
import com.project.Ambulance.model.User;
import com.project.Ambulance.service.AmbulanceBrandService;
import com.project.Ambulance.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.time.LocalDate;
import java.util.List;

@Controller
@RequestMapping("/admin/ambulance-brand")
public class AmbulanceBrandController {

    @Autowired
    private AmbulanceBrandService ambulanceBrandService;

    @Autowired
    private UserService userService; // Giữ lại check quyền giống project cũ

    // Danh sách thương hiệu xe cứu thương
    @GetMapping
    public String listAmbulanceBrand(Model model) {
        model.addAttribute("brandList", ambulanceBrandService.getAllAmbulanceBrand());
        return "admin/pages/ambulanceBrand/list";
    }

    // Hiển thị form thêm mới
    @GetMapping("/add")
    public String showAddForm(Model model) {
        model.addAttribute("ambulanceBrand", new AmbulanceBrand());
        model.addAttribute("action", "Thêm");
        return "admin/pages/ambulanceBrand/form";
    }

    // Hiển thị form chỉnh sửa
    @GetMapping("/edit/{id}")
    public String showEditForm(Model model, @PathVariable("id") Long id) {
        AmbulanceBrand ambulanceBrand = ambulanceBrandService.getAmbulanceBrandById(id);
        model.addAttribute("ambulanceBrand", ambulanceBrand);
        model.addAttribute("action", "Sửa");
        return "admin/pages/ambulanceBrand/form";
    }

    // Xoá thương hiệu
    @GetMapping("/delete/{id}")
    public String deleteAmbulanceBrand(@PathVariable("id") Long id) {
        ambulanceBrandService.deleteAmbulanceBrand(id);
        return "redirect:/admin/ambulance-brand";
    }

    // Lưu thương hiệu (add & edit)
    @PostMapping("/save")
    public String saveAmbulanceBrand(@ModelAttribute("ambulanceBrand") AmbulanceBrand ambulanceBrand,
                                     RedirectAttributes ra) {
        AmbulanceBrand oldBrand = ambulanceBrandService.getAmbulanceBrandById(ambulanceBrand.getId());
        if (oldBrand != null) {
            ambulanceBrand.setCreateDate(oldBrand.getCreateDate());
        } else {
            ambulanceBrand.setCreateDate(LocalDate.now());
        }
        ambulanceBrand.setUpdateDate(LocalDate.now());

        boolean exists = ambulanceBrandService.existsByBrandName(ambulanceBrand.getBrandName());
        if (exists && (oldBrand == null || !oldBrand.getBrandName().equals(ambulanceBrand.getBrandName()))) {
            ra.addFlashAttribute("error", "Tên thương hiệu đã tồn tại!");
        } else {
            ambulanceBrandService.saveAmbulanceBrand(ambulanceBrand);
        }
        return "redirect:/admin/ambulance-brand";
    }
}
