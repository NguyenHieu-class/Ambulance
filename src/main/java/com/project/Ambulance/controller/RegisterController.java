package com.project.Ambulance.controller;

import com.project.Ambulance.model.Role;
import com.project.Ambulance.model.User;
import com.project.Ambulance.service.RoleService;
import com.project.Ambulance.service.UploadFileService;
import com.project.Ambulance.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import java.time.LocalDate;

@Controller
public class RegisterController {

    @Autowired
    private UserService userService;

    @Autowired
    private RoleService roleService;

    @Autowired
    private UploadFileService uploadFileService;

    @GetMapping("/register")
    public String showRegisterForm() {
        return "pages/register";
    }

    @PostMapping("/register")
    public String registerUser(@RequestParam("username") String username,
                               @RequestParam("password") String password,
                               @RequestParam("nameDisplay") String nameDisplay,
                               @RequestParam("phone") String phone,
                               @RequestParam("email") String email,
                               @RequestParam("address") String address,
                               @RequestParam(value = "imageFile", required = false) MultipartFile imageFile,
                               @RequestParam("roleId") Long roleId) {

        User user = new User();
        user.setUsername(username);
        user.setPassword(password);
        user.setNameDisplay(nameDisplay);
        user.setPhone(phone);
        user.setEmail(email);
        user.setAddress(address);
        user.setCreateDate(LocalDate.now());
        user.setUpdateDate(LocalDate.now());

        if (imageFile != null && !imageFile.isEmpty()) {
            String fileName = uploadFileService.uploadSingleFile(imageFile);
            user.setImage(fileName);
        }

        Role role = roleService.getRoleById(roleId);
        user.setRole(role);

        userService.saveUser(user);
        return "redirect:/login";
    }
}
