package com.project.Ambulance.controller;

import com.project.Ambulance.model.District;
import com.project.Ambulance.model.Province;
import com.project.Ambulance.model.User;
import com.project.Ambulance.service.DistrictService;
import com.project.Ambulance.service.ProvinceService;
import com.project.Ambulance.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.time.LocalDate;

@Controller
@RequestMapping("/admin/district")
public class DistrictController {

    @Autowired
    private DistrictService districtService;

    @Autowired
    private ProvinceService provinceService;

    @Autowired
    private UserService userService;

    // Danh sách quận/huyện
    @GetMapping
    public String listDistricts(Model model) {
        model.addAttribute("districts", districtService.getAllDistrict());
        model.addAttribute("provinces", provinceService.getAllProvince());
        return "admin/pages/district/list";
    }

    // Form thêm mới quận/huyện
    @GetMapping("/add")
    public String showAddForm(Model model) {
        model.addAttribute("district", new District());
        model.addAttribute("provinces", provinceService.getAllProvince());
        model.addAttribute("action", "Thêm");
        return "admin/pages/district/form";
    }

    // Form chỉnh sửa quận/huyện
    @GetMapping("/edit/{id}")
    public String showEditForm(@PathVariable("id") Long id, Model model) {
        District district = districtService.getDistrictById(id);
        model.addAttribute("district", district);
        model.addAttribute("provinces", provinceService.getAllProvince());
        model.addAttribute("action", "Sửa");
        return "admin/pages/district/form";
    }

    // Xoá quận/huyện
    @GetMapping("/delete/{id}")
    public String deleteDistrict(@PathVariable("id") Long id) {
        districtService.deleteDistrict(id);
        return "redirect:/admin/district";
    }

    // Lưu quận/huyện (Add + Edit)
    @PostMapping("/save")
    public String saveDistrict(@ModelAttribute("district") District district,
                               @RequestParam("provinceId") Long provinceId,
                               RedirectAttributes ra) {

        District oldDistrict = districtService.getDistrictById(district.getId());
        if (oldDistrict != null) {
            district.setCreateDate(oldDistrict.getCreateDate());
        } else {
            district.setCreateDate(LocalDate.now());
        }
        district.setUpdateDate(LocalDate.now());

        district.setProvince(provinceService.getProvinceById(provinceId));

        boolean exists = districtService.existsByName(district.getName());
        if (exists && (oldDistrict == null || !oldDistrict.getName().equals(district.getName()))) {
            ra.addFlashAttribute("error", "Tên quận/huyện đã tồn tại!");
        } else {
            districtService.saveDistrict(district);
        }
        return "redirect:/admin/district";
    }
}
