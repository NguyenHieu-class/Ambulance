package com.project.Ambulance.controller;

import com.project.Ambulance.model.Province;
import com.project.Ambulance.model.User;
import com.project.Ambulance.service.ProvinceService;
import com.project.Ambulance.service.UserService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;


@Controller
@RequestMapping("/admin/province")
public class ProvinceController {

    @Autowired
    private ProvinceService provinceService;

    @Autowired
    private UserService userService;

    // Danh sách tỉnh
    @GetMapping
    public String listProvinces(Model model, HttpServletRequest request) {
        HttpSession session = request.getSession();
        User sessionUser = (User) session.getAttribute("sessionUser");
        if (sessionUser != null && sessionUser.getRole().getRoleName().equals("ADMIN")) {
            model.addAttribute("provinces", provinceService.getAllProvince());
            return "admin/pages/province/list";
        }
        return "redirect:/login";
    }

    // Form thêm mới
    @GetMapping("/add")
    public String showAddForm(Model model, HttpServletRequest request) {
        HttpSession session = request.getSession();
        User sessionUser = (User) session.getAttribute("sessionUser");
        if (sessionUser != null && sessionUser.getRole().getRoleName().equals("ADMIN")) {
            model.addAttribute("province", new Province());
            model.addAttribute("action", "Thêm");
            return "admin/pages/province/form";
        }
        return "redirect:/login";
    }

    // Form chỉnh sửa
    @GetMapping("/edit/{id}")
    public String showEditForm(@PathVariable("id") Long id, Model model, HttpServletRequest request) {
        HttpSession session = request.getSession();
        User sessionUser = (User) session.getAttribute("sessionUser");
        if (sessionUser != null && sessionUser.getRole().getRoleName().equals("ADMIN")) {
            Province province = provinceService.getProvinceById(id);
            model.addAttribute("province", province);
            model.addAttribute("action", "Sửa");
            return "admin/pages/province/form";
        }
        return "redirect:/login";
    }

    // Xoá
    @GetMapping("/delete/{id}")
    public String deleteProvince(@PathVariable("id") Long id, HttpServletRequest request) {
        HttpSession session = request.getSession();
        User sessionUser = (User) session.getAttribute("sessionUser");
        if (sessionUser != null && sessionUser.getRole().getRoleName().equals("ADMIN")) {
            provinceService.deleteProvince(id);
            return "redirect:/admin/province";
        }
        return "redirect:/login";
    }

    // Lưu (Add + Edit)
    @PostMapping("/save")
    public String saveProvince(@ModelAttribute("province") Province province, RedirectAttributes ra, HttpServletRequest request) {
        HttpSession session = request.getSession();
        User sessionUser = (User) session.getAttribute("sessionUser");
        if (sessionUser != null && sessionUser.getRole().getRoleName().equals("ADMIN")) {

            Province oldProvince = provinceService.getProvinceById(province.getId());
            if (oldProvince != null) {
                province.setCreateDate(oldProvince.getCreateDate());
            }

            boolean exists = provinceService.existsByName(province.getName());
            if (exists && (oldProvince == null || !oldProvince.getName().equals(province.getName()))) {
                ra.addFlashAttribute("error", "Tên tỉnh đã tồn tại!");
            } else {
                provinceService.saveProvince(province);
            }
            return "redirect:/admin/province";
        }
        return "redirect:/login";
    }
}
