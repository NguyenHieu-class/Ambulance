package com.project.Ambulance.controller;

import com.project.Ambulance.model.Ambulance;
import com.project.Ambulance.model.Ambulance.FuelType;
import com.project.Ambulance.model.Ambulance.Status;
import com.project.Ambulance.model.AmbulanceBrand;
import com.project.Ambulance.model.Province;
import com.project.Ambulance.model.District;
import com.project.Ambulance.model.Ward;
import com.project.Ambulance.service.AmbulanceBrandService;
import com.project.Ambulance.service.AmbulanceService;
import com.project.Ambulance.service.ProvinceService;
import com.project.Ambulance.service.UploadFileService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.List;

@Controller
@RequestMapping("/ambulances")
public class AmbulanceController {

    @Autowired
    private AmbulanceService ambulanceService;

    @Autowired
    private AmbulanceBrandService ambulanceBrandService;

    @Autowired
    private ProvinceService provinceService;

    @Autowired
    private UploadFileService uploadFileService;

    /**
     * Hiển thị danh sách toàn bộ xe cứu thương
     */
    @GetMapping
    public String listAmbulances(Model model) {
        List<Ambulance> ambulances = ambulanceService.getAllAmbulance();
        model.addAttribute("ambulances", ambulances);
        return "ambulance/list";
    }

    /**
     * Hiển thị form thêm mới xe cứu thương
     */
    @GetMapping("/add")
    public String showAddForm(Model model) {
        model.addAttribute("ambulance", new Ambulance());
        model.addAttribute("statuses", Status.values());
        model.addAttribute("fuelTypes", FuelType.values());
        model.addAttribute("brands", ambulanceBrandService.getAllAmbulanceBrand());
        model.addAttribute("provinces", provinceService.getAllProvince());
        return "ambulance/add";
    }

    /**
     * Lưu xe cứu thương mới
     */
    @PostMapping("/save")
    public String saveAmbulance(@ModelAttribute("ambulance") Ambulance ambulance,
                                @RequestParam("brandId") Long brandId,
                                @RequestParam("provinceId") Long provinceId,
                                @RequestParam("districtId") Long districtId,
                                @RequestParam("wardId") Long wardId,
                                @RequestParam("avatarImage") MultipartFile avatarFile,
                                @RequestParam(value = "galleryImages", required = false) MultipartFile[] galleryFiles,
                                RedirectAttributes redirectAttributes) {

        // Kiểm tra biển số xe đã tồn tại
        Ambulance existing = ambulanceService.getAmbulanceByLicensePlate(ambulance.getLicensePlate());
        if (existing != null) {
            redirectAttributes.addFlashAttribute("error", "Biển số xe đã tồn tại!");
            return "redirect:/ambulances/add";
        }

        ambulance.setAmbulanceBrand(ambulanceBrandService.getAmbulanceBrandById(brandId));
        ambulance.setStatus(Status.AVAILABLE); // Mặc định trạng thái ban đầu

        // Upload avatar (ảnh chính)
        if (!avatarFile.isEmpty()) {
            String avatarFileName = uploadFileService.uploadSingleFile(avatarFile);
            ambulance.setAvatarImage(avatarFileName);
        }

        // Upload gallery images (ảnh phụ)
        if (galleryFiles != null && galleryFiles.length > 0) {
            String gallery = uploadFileService.uploadMultiFile(galleryFiles);
            ambulance.setImageGallery(gallery);
        }

        ambulanceService.saveAmbulance(ambulance);
        redirectAttributes.addFlashAttribute("success", "Thêm mới xe cứu thương thành công!");
        return "redirect:/ambulances";
    }

    /**
     * Hiển thị form chỉnh sửa xe cứu thương
     */
    @GetMapping("/edit/{id}")
    public String showEditForm(@PathVariable("id") Long id, Model model) {
        Ambulance ambulance = ambulanceService.getAmbulanceById(id);
        model.addAttribute("ambulance", ambulance);
        model.addAttribute("statuses", Status.values());
        model.addAttribute("fuelTypes", FuelType.values());
        model.addAttribute("brands", ambulanceBrandService.getAllAmbulanceBrand());
        model.addAttribute("provinces", provinceService.getAllProvince());
        return "ambulance/edit";
    }

    /**
     * Cập nhật xe cứu thương
     */
    @PostMapping("/update/{id}")
    public String updateAmbulance(@PathVariable("id") Long id,
                                  @ModelAttribute("ambulance") Ambulance ambulance,
                                  @RequestParam("brandId") Long brandId,
                                  @RequestParam("avatarImage") MultipartFile avatarFile,
                                  @RequestParam(value = "galleryImages", required = false) MultipartFile[] galleryFiles,
                                  RedirectAttributes redirectAttributes) {

        Ambulance existing = ambulanceService.getAmbulanceById(id);
        ambulance.setId(existing.getId());
        ambulance.setCreateDate(existing.getCreateDate());
        ambulance.setAmbulanceBrand(ambulanceBrandService.getAmbulanceBrandById(brandId));

        // Nếu có avatar mới thì upload thay thế
        if (!avatarFile.isEmpty()) {
            // Xoá ảnh cũ nếu có
            if (existing.getAvatarImage() != null) {
                uploadFileService.removeFile(existing.getAvatarImage());
            }
            String avatarFileName = uploadFileService.uploadSingleFile(avatarFile);
            ambulance.setAvatarImage(avatarFileName);
        } else {
            ambulance.setAvatarImage(existing.getAvatarImage());
        }

        // Nếu có gallery mới thì upload thay thế
        if (galleryFiles != null && galleryFiles.length > 0) {
            if (existing.getImageGallery() != null) {
                String[] oldFiles = existing.getImageGallery().split(";");
                for (String old : oldFiles) {
                    uploadFileService.removeFile(old);
                }
            }
            String gallery = uploadFileService.uploadMultiFile(galleryFiles);
            ambulance.setImageGallery(gallery);
        } else {
            ambulance.setImageGallery(existing.getImageGallery());
        }

        ambulanceService.saveAmbulance(ambulance);
        redirectAttributes.addFlashAttribute("success", "Cập nhật xe cứu thương thành công!");
        return "redirect:/ambulances";
    }

    /**
     * Xoá xe cứu thương
     */
    @GetMapping("/delete/{id}")
    public String deleteAmbulance(@PathVariable("id") Long id, RedirectAttributes redirectAttributes) {
        Ambulance ambulance = ambulanceService.getAmbulanceById(id);
        if (ambulance != null) {
            // Xoá file ảnh
            if (ambulance.getAvatarImage() != null) {
                uploadFileService.removeFile(ambulance.getAvatarImage());
            }
            if (ambulance.getImageGallery() != null) {
                String[] galleryFiles = ambulance.getImageGallery().split(";");
                for (String img : galleryFiles) {
                    uploadFileService.removeFile(img);
                }
            }
            ambulanceService.deleteAmbulance(id);
            redirectAttributes.addFlashAttribute("success", "Đã xoá xe cứu thương!");
        }
        return "redirect:/ambulances";
    }
}
