package com.project.Ambulance.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ModelAttribute;

import com.project.Ambulance.model.Ambulance;
import com.project.Ambulance.service.CarService;

@Controller
public class AmbulanceController {

    @Autowired
    private CarService carService;

    @GetMapping("/admin/ambulances")
    public String list(Model model) {
        model.addAttribute("ambulances", carService.findAll());
        return "admin/pages/ambulance/list";
    }

    @GetMapping("/admin/ambulance/add")
    public String form(Model model) {
        model.addAttribute("ambulance", new Ambulance());
        return "admin/pages/ambulance/form";
    }

    @PostMapping("/admin/ambulance/save")
    public String save(@ModelAttribute Ambulance ambulance) {
        carService.save(ambulance);
        return "redirect:/admin/ambulances";
    }

    @GetMapping("/admin/ambulance/delete/{id}")
    public String delete(@PathVariable int id) {
        carService.deleteById(id);
        return "redirect:/admin/ambulances";
    }
}
