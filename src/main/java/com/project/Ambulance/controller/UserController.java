package com.project.Ambulance.controller;

import com.project.Ambulance.model.*;
import com.project.Ambulance.service.*;
import com.project.Ambulance.service.UploadFileService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
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

    // Hiển thị danh sách tài khoản người dùng
    @GetMapping
    public String listUsers(Model model, HttpServletRequest request) {
        HttpSession session = request.getSession();
        User sessionUser = (User) session.getAttribute("sessionUser");

        if (sessionUser != null && sessionUser.getRole().getRoleName().equals("ADMIN")) {
            model.addAttribute("users", userService.getAllUser());
            return "admin/pages/user/list";
        }
        return "redirect:/login";
    }

    // Xóa tài khoản người dùng
    @GetMapping("/delete/{id}")
    public String deleteUser(@PathVariable("id") Long id, HttpServletRequest request) {
        HttpSession session = request.getSession();
        User sessionUser = (User) session.getAttribute("sessionUser");

        if (sessionUser != null && sessionUser.getRole().getRoleName().equals("ADMIN")) {
            userService.deleteUser(id);
            return "redirect:/admin/user";
        }
        return "redirect:/login";
    }

    // Reset mật khẩu về mặc định
    @GetMapping("/reset-password/{id}")
    public String resetPassword(@PathVariable("id") Long id, RedirectAttributes ra, HttpServletRequest request) {
        HttpSession session = request.getSession();
        User sessionUser = (User) session.getAttribute("sessionUser");

        if (sessionUser != null && sessionUser.getRole().getRoleName().equals("ADMIN")) {
            User user = userService.getUserById(id);
            user.setPassword("123"); // Tạm hard-code reset (sau này dùng bcrypt encoder chuẩn)
            userService.saveUser(user);
            ra.addFlashAttribute("messResetPass", "Reset mật khẩu thành công");
            return "redirect:/admin/user";
        }
        return "redirect:/login";
    }

    // ================= PHẦN CHO CHÍNH NGƯỜI DÙNG CẬP NHẬT THÔNG TIN ===================

    // Upload avatar mới
    @PostMapping("/upload-avatar")
    public String uploadAvatar(HttpServletRequest request,
                               @RequestParam("avatarImage") MultipartFile image) {
        HttpSession session = request.getSession();
        User user = (User) session.getAttribute("sessionUser");

        if (user != null) {
            uploadFileService.removeFile(user.getImage());
            user.setImage(uploadFileService.uploadSingleFile(image));
            userService.saveUser(user);
            return "redirect:/edit-profile";
        }
        return "redirect:/login";
    }

    // Cập nhật thông tin cá nhân
    @PostMapping("/update-info")
    public String updateUserInfo(HttpServletRequest request,
                                 @RequestParam("nameDisplay") String nameDisplay,
                                 @RequestParam("dateOfBrith") String dateOfBrith,
                                 @RequestParam("sex") int sex,
                                 @RequestParam("provinceId") Long provinceId,
                                 @RequestParam("districtId") Long districtId,
                                 @RequestParam("wardId") Long wardId,
                                 @RequestParam("addressDetail") String addressDetail) {

        HttpSession session = request.getSession();
        User user = (User) session.getAttribute("sessionUser");

        if (user != null) {
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
        return "redirect:/login";
    }

    // Cập nhật số điện thoại
    @PostMapping("/update-phone")
    public String updatePhone(HttpServletRequest request, @RequestParam("phone") String phone) {
        HttpSession session = request.getSession();
        User user = (User) session.getAttribute("sessionUser");

        if (user != null) {
            user.setPhone(phone);
            userService.saveUser(user);
            return "redirect:/edit-profile";
        }
        return "redirect:/login";
    }

    // Cập nhật email
    @PostMapping("/update-email")
    public String updateEmail(HttpServletRequest request, @RequestParam("email") String email) {
        HttpSession session = request.getSession();
        User user = (User) session.getAttribute("sessionUser");

        if (user != null) {
            user.setEmail(email);
            userService.saveUser(user);
            return "redirect:/edit-profile";
        }
        return "redirect:/login";
    }

    // Cập nhật bằng lái (driving license)
    @PostMapping("/update-driving-license")
    public String updateDrivingLicense(HttpServletRequest request,
                                       @RequestParam("drivingLicense") String drivingLicense,
                                       @RequestParam(name = "licenseImage", required = false) MultipartFile licenseImage) {

        HttpSession session = request.getSession();
        User user = (User) session.getAttribute("sessionUser");

        if (user != null) {
            user.setDrivingLicense(drivingLicense);
            if (licenseImage != null && !licenseImage.isEmpty()) {
                uploadFileService.removeFile(user.getImgDrivingLicense());
                user.setImgDrivingLicense(uploadFileService.uploadSingleFile(licenseImage));
            }
            userService.saveUser(user);
            return "redirect:/edit-profile";
        }
        return "redirect:/login";
    }
}
