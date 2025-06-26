package com.project.Ambulance.controller;

import com.project.Ambulance.model.*;
import com.project.Ambulance.service.*;
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
@RequestMapping("/admin/hospital")
public class HospitalController {

    @Autowired
    private HospitalService hospitalService;

    @Autowired
    private ProvinceService provinceService;

    @Autowired
    private DistrictService districtService;

    @Autowired
    private WardService wardService;

    @Autowired
    private UserService userService; // dùng cho kiểm tra role

    // Danh sách toàn bộ bệnh viện
    @GetMapping
    public String listHospitals(Model model, HttpServletRequest request) {
        HttpSession session = request.getSession();
        User sessionUser = (User) session.getAttribute("sessionUser");

        if (sessionUser != null && sessionUser.getRole().getRoleName().equals("ADMIN")) {
            model.addAttribute("hospitals", hospitalService.getAllHospital());
            return "admin/pages/hospital/list";
        }
        return "redirect:/login";
    }

    // Form thêm mới bệnh viện
    @GetMapping("/add")
    public String showAddForm(Model model, HttpServletRequest request) {
        HttpSession session = request.getSession();
        User sessionUser = (User) session.getAttribute("sessionUser");

        if (sessionUser != null && sessionUser.getRole().getRoleName().equals("ADMIN")) {
            model.addAttribute("hospital", new Hospital());
            model.addAttribute("provinces", provinceService.getAllProvince());
            model.addAttribute("action", "Thêm");
            return "admin/pages/hospital/form";
        }
        return "redirect:/login";
    }

    // Form chỉnh sửa bệnh viện
    @GetMapping("/edit/{id}")
    public String showEditForm(Model model, @PathVariable("id") Long id, HttpServletRequest request) {
        HttpSession session = request.getSession();
        User sessionUser = (User) session.getAttribute("sessionUser");

        if (sessionUser != null && sessionUser.getRole().getRoleName().equals("ADMIN")) {
            Hospital hospital = hospitalService.getHospitalById(id);
            model.addAttribute("hospital", hospital);
            model.addAttribute("provinces", provinceService.getAllProvince());
            model.addAttribute("districts", districtService.getDistrictsByProvinceId(hospital.getProvince().getId()));
            model.addAttribute("wards", wardService.getWardsByDistrictId(hospital.getDistrict().getId()));
            model.addAttribute("action", "Sửa");
            return "admin/pages/hospital/form";
        }
        return "redirect:/login";
    }

    // Xoá bệnh viện
    @GetMapping("/delete/{id}")
    public String deleteHospital(@PathVariable("id") Long id, HttpServletRequest request) {
        HttpSession session = request.getSession();
        User sessionUser = (User) session.getAttribute("sessionUser");

        if (sessionUser != null && sessionUser.getRole().getRoleName().equals("ADMIN")) {
            hospitalService.deleteHospital(id);
            return "redirect:/admin/hospital";
        }
        return "redirect:/login";
    }

    // Lưu bệnh viện (Add + Edit)
    @PostMapping("/save")
    public String saveHospital(@ModelAttribute("hospital") Hospital hospital,
                               @RequestParam("provinceId") Long provinceId,
                               @RequestParam("districtId") Long districtId,
                               @RequestParam("wardId") Long wardId,
                               RedirectAttributes ra,
                               HttpServletRequest request) {

        HttpSession session = request.getSession();
        User sessionUser = (User) session.getAttribute("sessionUser");

        if (sessionUser != null && sessionUser.getRole().getRoleName().equals("ADMIN")) {

            Hospital oldHospital = hospitalService.getHospitalById(hospital.getId());
            if (oldHospital != null) {
                hospital.setCreateDate(oldHospital.getCreateDate());
            } else {
                hospital.setCreateDate(LocalDate.now());
            }
            hospital.setUpdateDate(LocalDate.now());

            hospital.setProvince(provinceService.getProvinceById(provinceId));
            hospital.setDistrict(districtService.getDistrictById(districtId));
            hospital.setWard(wardService.getWardById(wardId));

            boolean exists = hospitalService.existsByName(hospital.getName());
            if (exists && (oldHospital == null || !oldHospital.getName().equals(hospital.getName()))) {
                ra.addFlashAttribute("error", "Tên bệnh viện đã tồn tại!");
            } else {
                hospitalService.saveHospital(hospital);
            }
            return "redirect:/admin/hospital";
        }
        return "redirect:/login";
    }
}
