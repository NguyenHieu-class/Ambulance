package com.project.Ambulance.controller;

import com.project.Ambulance.model.Role;
import com.project.Ambulance.model.User;
import com.project.Ambulance.service.RoleService;
import com.project.Ambulance.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import jakarta.servlet.http.HttpSession;
import java.util.Date;

@Controller
public class AuthController {

    @Autowired
    private UserService userService;

    @Autowired
    private RoleService roleService;

    // Show login page
    @GetMapping({"/", "/login"})
    public String showLogin(@RequestParam(value = "success", required = false) String success,
                            Model model) {
        if (success != null) {
            model.addAttribute("success", success);
        }
        return "auth/login";
    }

    // Show register page
    @GetMapping("/register")
    public String showRegister() {
        return "auth/register";
    }

    // Handle login
    @PostMapping("/login")
    public String doLogin(@RequestParam String username,
                          @RequestParam String password,
                          HttpSession session,
                          Model model) {
        User user = userService.getUserByUsername(username);
        if (user != null && password.equals(user.getPassword())) {
            session.setAttribute("loggedInUser", user);
            String roleName = user.getRole().getName();
            if ("ADMIN".equalsIgnoreCase(roleName)) {
                return "redirect:/admin/dashboard";
            } else if ("DRIVER".equalsIgnoreCase(roleName)) {
                return "redirect:/driver/dashboard";
            } else if ("MEDICAL".equalsIgnoreCase(roleName)) {
                return "redirect:/medical/dashboard";
            }
            System.out.println("Entered: " + password);
            System.out.println("In DB:   " + user.getPassword());
            return "redirect:/";
        }
        model.addAttribute("error", "Sai tên đăng nhập hoặc mật khẩu");
        return "auth/login";
    }

    // Handle register
    @PostMapping("/register")
    public String doRegister(@RequestParam String username,
                             @RequestParam String password,
                             @RequestParam String nameDisplay,
                             @RequestParam(required = false) String email,
                             @RequestParam(required = false) String phone,
                             @RequestParam(required = false) String address,
                             Model model) {
        if (userService.getUserByUsername(username) != null) {
            model.addAttribute("error", "Username đã tồn tại");
            return "auth/register";
        }
        User user = new User();
        user.setUsername(username);
        user.setPassword(password);
        user.setNameDisplay(nameDisplay);
        user.setEmail(email);
        user.setPhone(phone);
        user.setAddress(address);
        user.setCreateDate(new Date());
        Role staffRole = roleService.getRoleByName("STAFF");
        user.setRole(staffRole);
        userService.saveUser(user);
        model.addAttribute("success", "Đăng ký thành công");
        return "auth/login";
    }

    // Logout
    @GetMapping("/logout")
    public String logout(HttpSession session) {
        session.invalidate();
        return "redirect:/logout-success";
    }

    @GetMapping("/logout-success")
    public String logoutSuccess(Model model) {
        model.addAttribute("success", "Đăng xuất thành công");
        return "common/logout";
    }
}
