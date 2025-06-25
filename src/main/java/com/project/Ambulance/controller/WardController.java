package com.project.Ambulance.controller;

import com.project.Ambulance.model.District;
import com.project.Ambulance.model.Province;
import com.project.Ambulance.model.User;
import com.project.Ambulance.model.Ward;
import com.project.Ambulance.service.DistrictService;
import com.project.Ambulance.service.ProvinceService;
import com.project.Ambulance.service.UserService;
import com.project.Ambulance.service.WardService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.time.LocalDate;

@Controller
@RequestMapping("/admin/ward")
public class WardController {

    @Autowired
    private WardService wardService;

    @Autowired
    private DistrictService districtService;

    @Autowired
    private ProvinceService provinceService;

    @Autowired
    private UserService userService;

    // Danh sách phường/xã
    @GetMapping
    public String listWards(Model model, HttpServletRequest request) {
        HttpSession session = request.getSession();
        User sessionUser = (User) session.getAttribute("sesionUser");
        if (sessionUser != null && sessionUser.getRole().getRoleName().equals("ADMIN")) {
            model.addAttribute("wards", wardService.getAllWard());
            model.addAttribute("districts", districtService.getAllDistrict());
            model.addAttribute("provinces", provinceService.getAllProvince());
            return "admin/pages/ward/list";
        }
        return "redirect:/login";
    }

    // Form thêm mới phường/xã
    @GetMapping("/add")
    public String showAddForm(Model model, HttpServletRequest request) {
        HttpSession session = request.getSession();
        User sessionUser = (User) session.getAttribute("sesionUser");
        if (sessionUser != null && sessionUser.getRole().getRoleName().equals("ADMIN")) {
            model.addAttribute("ward", new Ward());
            model.addAttribute("provinces", provinceService.getAllProvince());
            model.addAttribute("districts", districtService.getAllDistrict());
            model.addAttribute("action", "Thêm");
            return "admin/pages/ward/form";
        }
        return "redirect:/login";
    }

    // Form chỉnh sửa phường/xã
    @GetMapping("/edit/{id}")
    public String showEditForm(@PathVariable("id") Long id, Model model, HttpServletRequest request) {
        HttpSession session = request.getSession();
        User sessionUser = (User) session.getAttribute("sesionUser");
        if (sessionUser != null && sessionUser.getRole().getRoleName().equals("ADMIN")) {
            Ward ward = wardService.getWardById(id);
            model.addAttribute("ward", ward);
            model.addAttribute("provinces", provinceService.getAllProvince());
            model.addAttribute("districts", districtService.getAllDistrict());
            model.addAttribute("action", "Sửa");
            return "admin/pages/ward/form";
        }
        return "redirect:/login";
    }

    // Xoá phường/xã
    @GetMapping("/delete/{id}")
    public String deleteWard(@PathVariable("id") Long id, HttpServletRequest request) {
        HttpSession session = request.getSession();
        User sessionUser = (User) session.getAttribute("sesionUser");
        if (sessionUser != null && sessionUser.getRole().getRoleName().equals("ADMIN")) {
            wardService.deleteWard(id);
            return "redirect:/admin/ward";
        }
        return "redirect:/login";
    }

    // Lưu phường/xã (Add + Edit)
    @PostMapping("/save")
    public String saveWard(@ModelAttribute("ward") Ward ward,
                           @RequestParam("districtId") Long districtId,
                           RedirectAttributes ra,
                           HttpServletRequest request) {

        HttpSession session = request.getSession();
        User sessionUser = (User) session.getAttribute("sesionUser");
        if (sessionUser != null && sessionUser.getRole().getRoleName().equals("ADMIN")) {

            Ward oldWard = wardService.getWardById(ward.getId());
            if (oldWard != null) {
                ward.setCreateDate(oldWard.getCreateDate());
            } else {
                ward.setCreateDate(LocalDate.now());
            }
            ward.setUpdateDate(LocalDate.now());

            ward.setDistrict(districtService.getDistrictById(districtId));

            boolean exists = wardService.existsByName(ward.getName());
            if (exists && (oldWard == null || !oldWard.getName().equals(ward.getName()))) {
                ra.addFlashAttribute("error", "Tên phường/xã đã tồn tại!");
            } else {
                wardService.saveWard(ward);
            }
            return "redirect:/admin/ward";
        }
        return "redirect:/login";
    }
}
