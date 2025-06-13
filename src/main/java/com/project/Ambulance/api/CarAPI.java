package com.project.Ambulance.api;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.project.Ambulance.model.Car;
import com.project.Ambulance.service.BrandCarService;
import com.project.Ambulance.service.CarService;
import com.project.Ambulance.service.ProvinceService;
import com.project.Ambulance.service.UploadFile;
import com.project.Ambulance.service.UserService;

@CrossOrigin("*")
@RestController
@RequestMapping("/api/car")
public class CarAPI {
    @Autowired
    private ProvinceService provinceService;
    @Autowired
    private BrandCarService brandCarService;

    @Autowired
    private UserService userService;

    @Autowired
    private UploadFile uploadFile;

    @Autowired
    private CarService carService;

    @GetMapping("")
    public List<Car> getCarByAdrress(){
       return null;
    }
}
