package com.project.Ambulance.controller;

import com.project.Ambulance.model.AmbulanceBrand;
import com.project.Ambulance.model.User;
import com.project.Ambulance.service.AmbulanceBrandService;
import com.project.Ambulance.service.UserService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
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
    public String listAmbulanceBrand(Model model, HttpServletRequest request) {
        HttpSession session = request.getSession();
        User sessionUser = (User) session.getAttribute("sessionUser");

        if (sessionUser != null && sessionUser.getRole().getRoleName().equals("ADMIN")) {
            model.addAttribute("brandList", ambulanceBrandService.getAllAmbulanceBrand());
            return "admin/pages/ambulanceBrand/list";
        }
        return "redirect:/login";
    }

    // Hiển thị form thêm mới
    @GetMapping("/add")
    public String showAddForm(Model model, HttpServletRequest request) {
        HttpSession session = request.getSession();
        User sessionUser = (User) session.getAttribute("sessionUser");

        if (sessionUser != null && sessionUser.getRole().getRoleName().equals("ADMIN")) {
            model.addAttribute("ambulanceBrand", new AmbulanceBrand());
            model.addAttribute("action", "Thêm");
            return "admin/pages/ambulanceBrand/form";
        }
        return "redirect:/login";
    }

    // Hiển thị form chỉnh sửa
    @GetMapping("/edit/{id}")
    public String showEditForm(Model model, @PathVariable("id") Long id, HttpServletRequest request) {
        HttpSession session = request.getSession();
        User sessionUser = (User) session.getAttribute("sessionUser");

        if (sessionUser != null && sessionUser.getRole().getRoleName().equals("ADMIN")) {
            AmbulanceBrand ambulanceBrand = ambulanceBrandService.getAmbulanceBrandById(id);
            model.addAttribute("ambulanceBrand", ambulanceBrand);
            model.addAttribute("action", "Sửa");
            return "admin/pages/ambulanceBrand/form";
        }
        return "redirect:/login";
    }

    // Xoá thương hiệu
    @GetMapping("/delete/{id}")
    public String deleteAmbulanceBrand(@PathVariable("id") Long id, HttpServletRequest request) {
        HttpSession session = request.getSession();
        User sessionUser = (User) session.getAttribute("sessionUser");

        if (sessionUser != null && sessionUser.getRole().getRoleName().equals("ADMIN")) {
            ambulanceBrandService.deleteAmbulanceBrand(id);
            return "redirect:/admin/ambulance-brand";
        }
        return "redirect:/login";
    }

    // Lưu thương hiệu (add & edit)
    @PostMapping("/save")
    public String saveAmbulanceBrand(@ModelAttribute("ambulanceBrand") AmbulanceBrand ambulanceBrand,
                                     RedirectAttributes ra,
                                     HttpServletRequest request) {

        HttpSession session = request.getSession();
        User sessionUser = (User) session.getAttribute("sessionUser");

        if (sessionUser != null && sessionUser.getRole().getRoleName().equals("ADMIN")) {
            AmbulanceBrand oldBrand = ambulanceBrandService.getAmbulanceBrandById(ambulanceBrand.getId());
            if (oldBrand != null) {
                ambulanceBrand.setCreateDate(oldBrand.getCreateDate());
            } else {
                ambulanceBrand.setCreateDate(LocalDate.now());
            }
            ambulanceBrand.setUpdateDate(LocalDate.now());

            // Kiểm tra tên thương hiệu đã tồn tại
            boolean exists = ambulanceBrandService.existsByBrandName(ambulanceBrand.getBrandName());
            if (exists && (oldBrand == null || !oldBrand.getBrandName().equals(ambulanceBrand.getBrandName()))) {
                ra.addFlashAttribute("error", "Tên thương hiệu đã tồn tại!");
            } else {
                ambulanceBrandService.saveAmbulanceBrand(ambulanceBrand);
            }
            return "redirect:/admin/ambulance-brand";
        }
        return "redirect:/login";
    }
}
