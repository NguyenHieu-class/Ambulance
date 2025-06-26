package com.project.Ambulance.controller;

import com.project.Ambulance.model.Province;
import com.project.Ambulance.model.User;
import com.project.Ambulance.service.ProvinceService;
import com.project.Ambulance.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.time.LocalDate;

@Controller
@RequestMapping("/admin/province")
public class ProvinceController {

    @Autowired
    private ProvinceService provinceService;

    @Autowired
    private UserService userService;

    // Danh sách tỉnh
    @GetMapping
    public String listProvinces(Model model) {
        model.addAttribute("provinces", provinceService.getAllProvince());
        return "admin/pages/province/list";
    }

    // Form thêm mới
    @GetMapping("/add")
    public String showAddForm(Model model) {
        model.addAttribute("province", new Province());
        model.addAttribute("action", "Thêm");
        return "admin/pages/province/form";
    }

    // Form chỉnh sửa
    @GetMapping("/edit/{id}")
    public String showEditForm(@PathVariable("id") Long id, Model model) {
        Province province = provinceService.getProvinceById(id);
        model.addAttribute("province", province);
        model.addAttribute("action", "Sửa");
        return "admin/pages/province/form";
    }

    // Xoá
    @GetMapping("/delete/{id}")
    public String deleteProvince(@PathVariable("id") Long id) {
        provinceService.deleteProvince(id);
        return "redirect:/admin/province";
    }

    // Lưu (Add + Edit)
    @PostMapping("/save")
    public String saveProvince(@ModelAttribute("province") Province province, RedirectAttributes ra) {

        Province oldProvince = provinceService.getProvinceById(province.getId());
        if (oldProvince != null) {
            province.setCreateDate(oldProvince.getCreateDate());
        } else {
            province.setCreateDate(LocalDate.now());
        }
        province.setUpdateDate(LocalDate.now());

        boolean exists = provinceService.existsByName(province.getName());
        if (exists && (oldProvince == null || !oldProvince.getName().equals(province.getName()))) {
            ra.addFlashAttribute("error", "Tên tỉnh đã tồn tại!");
        } else {
            provinceService.saveProvince(province);
        }
        return "redirect:/admin/province";
    }
}
