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

import java.time.LocalDateTime;
import java.util.List;

@Controller
@RequestMapping("/admin/transport-request")
public class TransportRequestController {

    @Autowired
    private TransportRequestService transportRequestService;

    @Autowired
    private MedicalStaffService medicalStaffService;

    @Autowired
    private HospitalService hospitalService;

    @Autowired
    private AmbulanceService ambulanceService;

    @Autowired
    private DriverService driverService;

    @Autowired
    private UserService userService;

    // Danh sách toàn bộ yêu cầu vận chuyển
    @GetMapping
    public String listTransportRequests(Model model, HttpServletRequest request) {
        HttpSession session = request.getSession();
        User sessionUser = (User) session.getAttribute("sessionUser");

        if (sessionUser != null && sessionUser.getRole().getRoleName().equals("ADMIN")) {
            model.addAttribute("requests", transportRequestService.getAllTransportRequest());
            return "admin/pages/transportRequest/list";
        }
        return "redirect:/login";
    }

    // Form thêm mới yêu cầu vận chuyển
    @GetMapping("/add")
    public String showAddForm(Model model, HttpServletRequest request) {
        HttpSession session = request.getSession();
        User sessionUser = (User) session.getAttribute("sessionUser");

        if (sessionUser != null && sessionUser.getRole().getRoleName().equals("ADMIN")) {
            model.addAttribute("transportRequest", new TransportRequest());
            model.addAttribute("medicalStaffs", medicalStaffService.getAllMedicalStaff());
            model.addAttribute("hospitals", hospitalService.getAllHospital());
            model.addAttribute("ambulances", ambulanceService.getAllAmbulance());
            model.addAttribute("drivers", driverService.getAllDriver());
            model.addAttribute("statuses", TransportRequest.Status.values());
            model.addAttribute("action", "Thêm");
            return "admin/pages/transportRequest/form";
        }
        return "redirect:/login";
    }

    // Form chỉnh sửa yêu cầu vận chuyển
    @GetMapping("/edit/{id}")
    public String showEditForm(@PathVariable("id") Long id, Model model, HttpServletRequest request) {
        HttpSession session = request.getSession();
        User sessionUser = (User) session.getAttribute("sessionUser");

        if (sessionUser != null && sessionUser.getRole().getRoleName().equals("ADMIN")) {
            TransportRequest requestEntity = transportRequestService.getTransportRequestById(id);
            model.addAttribute("transportRequest", requestEntity);
            model.addAttribute("medicalStaffs", medicalStaffService.getAllMedicalStaff());
            model.addAttribute("hospitals", hospitalService.getAllHospital());
            model.addAttribute("ambulances", ambulanceService.getAllAmbulance());
            model.addAttribute("drivers", driverService.getAllDriver());
            model.addAttribute("statuses", TransportRequest.Status.values());
            model.addAttribute("action", "Sửa");
            return "admin/pages/transportRequest/form";
        }
        return "redirect:/login";
    }

    // Xoá yêu cầu vận chuyển
    @GetMapping("/delete/{id}")
    public String deleteTransportRequest(@PathVariable("id") Long id, HttpServletRequest request) {
        HttpSession session = request.getSession();
        User sessionUser = (User) session.getAttribute("sessionUser");

        if (sessionUser != null && sessionUser.getRole().getRoleName().equals("ADMIN")) {
            transportRequestService.deleteTransportRequest(id);
            return "redirect:/admin/transport-request";
        }
        return "redirect:/login";
    }

    // Lưu yêu cầu vận chuyển (Add + Edit)
    @PostMapping("/save")
    public String saveTransportRequest(@ModelAttribute("transportRequest") TransportRequest transportRequest,
                                       @RequestParam("medicalStaffId") Long medicalStaffId,
                                       @RequestParam("hospitalId") Long hospitalId,
                                       @RequestParam("ambulanceId") Long ambulanceId,
                                       @RequestParam("driverId") Long driverId,
                                       RedirectAttributes ra,
                                       HttpServletRequest request) {

        HttpSession session = request.getSession();
        User sessionUser = (User) session.getAttribute("sessionUser");

        if (sessionUser != null && sessionUser.getRole().getRoleName().equals("ADMIN")) {

            TransportRequest oldRequest = transportRequestService.getTransportRequestById(transportRequest.getId());
            if (oldRequest != null) {
                transportRequest.setRequestDate(oldRequest.getRequestDate());
            } else {
                transportRequest.setRequestDate(LocalDateTime.now());
            }

            transportRequest.setRequester(medicalStaffService.getMedicalStaffById(medicalStaffId));
            transportRequest.setHospital(hospitalService.getHospitalById(hospitalId));
            transportRequest.setAssignedAmbulance(ambulanceService.getAmbulanceById(ambulanceId));
            transportRequest.setAssignedDriver(driverService.getDriverById(driverId));

            transportRequestService.saveTransportRequest(transportRequest);
            return "redirect:/admin/transport-request";
        }
        return "redirect:/login";
    }
}
