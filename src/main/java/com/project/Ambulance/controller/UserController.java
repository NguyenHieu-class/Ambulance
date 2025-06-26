package com.project.Ambulance.controller;

import com.project.Ambulance.model.*;
import com.project.Ambulance.service.*;
import com.project.Ambulance.service.UploadFileService;
import org.springframework.security.crypto.password.PasswordEncoder;
import java.security.Principal;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
@RequestMapping("/admin/user")
public class UserController {

    @Autowired
    private UserService userService;

    @Autowired
    private ProvinceService provinceService;

    @Autowired
    private DistrictService districtService;

    @Autowired
    private WardService wardService;

    @Autowired
    private UploadFileService uploadFileService;

    @Autowired
    private PasswordEncoder passwordEncoder;

    // Hiển thị danh sách tài khoản người dùng
    @GetMapping
    public String listUsers(Model model) {
        model.addAttribute("users", userService.getAllUser());
        return "admin/pages/user/list";
    }

    // Xóa tài khoản người dùng
    @GetMapping("/delete/{id}")
    public String deleteUser(@PathVariable("id") Long id) {
        userService.deleteUser(id);
        return "redirect:/admin/user";
    }

    // Reset mật khẩu về mặc định
    @GetMapping("/reset-password/{id}")
    public String resetPassword(@PathVariable("id") Long id, RedirectAttributes ra) {
        User user = userService.getUserById(id);
        user.setPassword(passwordEncoder.encode("123"));
        userService.saveUser(user);
        ra.addFlashAttribute("messResetPass", "Reset mật khẩu thành công");
        return "redirect:/admin/user";
    }

    // ================= PHẦN CHO CHÍNH NGƯỜI DÙNG CẬP NHẬT THÔNG TIN ===================

    // Upload avatar mới
    @PostMapping("/upload-avatar")
    public String uploadAvatar(Principal principal,
                               @RequestParam("avatarImage") MultipartFile image) {
        User user = userService.getUserByUsername(principal.getName());
        uploadFileService.removeFile(user.getImage());
        user.setImage(uploadFileService.uploadSingleFile(image));
        userService.saveUser(user);
        return "redirect:/edit-profile";
    }

    // Cập nhật thông tin cá nhân
    @PostMapping("/update-info")
    public String updateUserInfo(Principal principal,
                                 @RequestParam("nameDisplay") String nameDisplay,
                                 @RequestParam("dateOfBrith") String dateOfBrith,
                                 @RequestParam("sex") int sex,
                                 @RequestParam("provinceId") Long provinceId,
                                 @RequestParam("districtId") Long districtId,
                                 @RequestParam("wardId") Long wardId,
                                 @RequestParam("addressDetail") String addressDetail) {
        User user = userService.getUserByUsername(principal.getName());
        user.setNameDisplay(nameDisplay);
        user.setDateOfBrith(dateOfBrith);
        user.setSex(sex == 1 ? false : true);

        Province province = provinceService.getProvinceById(provinceId);
        District district = districtService.getDistrictById(districtId);
        Ward ward = wardService.getWardById(wardId);

        user.setAddress(addressDetail + ", " + ward.getName() + ", " + district.getName() + ", " + province.getName());
        userService.saveUser(user);
        return "redirect:/edit-profile";
    }

    // Cập nhật số điện thoại
    @PostMapping("/update-phone")
    public String updatePhone(Principal principal, @RequestParam("phone") String phone) {
        User user = userService.getUserByUsername(principal.getName());
        user.setPhone(phone);
        userService.saveUser(user);
        return "redirect:/edit-profile";
    }

    // Cập nhật email
    @PostMapping("/update-email")
    public String updateEmail(Principal principal, @RequestParam("email") String email) {
        User user = userService.getUserByUsername(principal.getName());
        user.setEmail(email);
        userService.saveUser(user);
        return "redirect:/edit-profile";
    }

    // Cập nhật bằng lái (driving license)
    @PostMapping("/update-driving-license")
    public String updateDrivingLicense(Principal principal,
                                       @RequestParam("drivingLicense") String drivingLicense,
                                       @RequestParam(name = "licenseImage", required = false) MultipartFile licenseImage) {
        User user = userService.getUserByUsername(principal.getName());
        user.setDrivingLicense(drivingLicense);
        if (licenseImage != null && !licenseImage.isEmpty()) {
            uploadFileService.removeFile(user.getImgDrivingLicense());
            user.setImgDrivingLicense(uploadFileService.uploadSingleFile(licenseImage));
        }
        userService.saveUser(user);
        return "redirect:/edit-profile";
    }
}
