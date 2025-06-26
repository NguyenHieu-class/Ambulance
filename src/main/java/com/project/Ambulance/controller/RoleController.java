package com.project.Ambulance.controller;

import com.project.Ambulance.model.Role;
import com.project.Ambulance.model.User;
import com.project.Ambulance.service.RoleService;
import com.project.Ambulance.service.UserService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.List;

@Controller
@RequestMapping("/admin/role")
public class RoleController {

    @Autowired
    private RoleService roleService;

    @Autowired
    private UserService userService;

    // Danh sách tất cả các quyền
    @GetMapping
    public String listRoles(Model model, HttpServletRequest request) {
        HttpSession session = request.getSession();
        User sessionUser = (User) session.getAttribute("sessionUser");
        if (sessionUser != null && sessionUser.getRole().getRoleName().equals("ADMIN")) {
            model.addAttribute("roles", roleService.getAllRole());
            return "admin/pages/role/list";
        }
        return "redirect:/login";
    }

    // Form thêm mới quyền
    @GetMapping("/add")
    public String showAddForm(Model model, HttpServletRequest request) {
        HttpSession session = request.getSession();
        User sessionUser = (User) session.getAttribute("sessionUser");
        if (sessionUser != null && sessionUser.getRole().getRoleName().equals("ADMIN")) {
            model.addAttribute("role", new Role());
            model.addAttribute("action", "Thêm");
            return "admin/pages/role/form";
        }
        return "redirect:/login";
    }

    // Form chỉnh sửa quyền
    @GetMapping("/edit/{id}")
    public String showEditForm(@PathVariable("id") Long id, Model model, HttpServletRequest request) {
        HttpSession session = request.getSession();
        User sessionUser = (User) session.getAttribute("sessionUser");
        if (sessionUser != null && sessionUser.getRole().getRoleName().equals("ADMIN")) {
            Role role = roleService.getRoleById(id);
            model.addAttribute("role", role);
            model.addAttribute("action", "Sửa");
            return "admin/pages/role/form";
        }
        return "redirect:/login";
    }

    // Xoá quyền
    @GetMapping("/delete/{id}")
    public String deleteRole(@PathVariable("id") Long id, HttpServletRequest request) {
        HttpSession session = request.getSession();
        User sessionUser = (User) session.getAttribute("sessionUser");
        if (sessionUser != null && sessionUser.getRole().getRoleName().equals("ADMIN")) {
            roleService.deleteRole(id);
            return "redirect:/admin/role";
        }
        return "redirect:/login";
    }

    // Lưu quyền (add + edit)
    @PostMapping("/save")
    public String saveRole(@ModelAttribute("role") Role role, RedirectAttributes ra, HttpServletRequest request) {
        HttpSession session = request.getSession();
        User sessionUser = (User) session.getAttribute("sessionUser");
        if (sessionUser != null && sessionUser.getRole().getRoleName().equals("ADMIN")) {

            Role oldRole = roleService.getRoleById(role.getId());
            if (oldRole != null) {
                role.setCreateDate(oldRole.getCreateDate());
            }

            boolean exists = roleService.existsByRoleName(role.getRoleName());
            if (exists && (oldRole == null || !oldRole.getRoleName().equals(role.getRoleName()))) {
                ra.addFlashAttribute("error", "Tên quyền đã tồn tại!");
            } else {
                roleService.saveRole(role);
            }
            return "redirect:/admin/role";
        }
        return "redirect:/login";
    }
}
