package com.project.Ambulance.controller;

import com.project.Ambulance.model.Driver;
import com.project.Ambulance.model.User;
import com.project.Ambulance.service.DriverService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import jakarta.servlet.http.HttpSession;

import java.util.List;

@RestController
@RequestMapping("/api")
public class DriverController {

    @Autowired
    private DriverService driverService;

    // === ADMIN APIs ===

    @GetMapping("/admin/drivers")
    public ResponseEntity<List<Driver>> getAllDrivers() {
        List<Driver> drivers = driverService.getAllDrivers();
        return ResponseEntity.ok(drivers);
    }

    @GetMapping("/admin/driver/{id}")
    public ResponseEntity<Driver> getDriverById(@PathVariable int id) {
        Driver driver = driverService.getDriverById(id);
        if (driver == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(driver);
    }

    @PostMapping("/admin/drivers")
    public ResponseEntity<Driver> createDriver(@RequestBody Driver driver) {
        Driver saved = driverService.saveDriver(driver);
        return ResponseEntity.ok(saved);
    }

    @PutMapping("/admin/driver/{id}")
    public ResponseEntity<Driver> updateDriver(@PathVariable int id,
                                               @RequestBody Driver driver) {
        driver.setIdDriver(id);
        Driver updated = driverService.saveDriver(driver);
        return ResponseEntity.ok(updated);
    }

    @DeleteMapping("/admin/driver/{id}")
    public ResponseEntity<Void> deleteDriver(@PathVariable int id) {
        driverService.deleteDriver(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/admin/drivers/search")
    public ResponseEntity<List<Driver>> searchDriverByName(@RequestParam String name) {
        List<Driver> drivers = driverService.searchDriversByName(name);
        return ResponseEntity.ok(drivers);
    }

    @PutMapping("/admin/driver/{id}/status")
    public ResponseEntity<Void> updateDriverStatus(@PathVariable int id,
                                                   @RequestParam int status) {
        try {
            driverService.updateStatus(id, status);
            return ResponseEntity.ok().build();
        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest().build();
        }
    }

    // === DRIVER APIs ===

    @GetMapping("/driver/profile")
    public ResponseEntity<Driver> getProfile(HttpSession session) {
        User loggedIn = (User) session.getAttribute("loggedInUser");
        if (loggedIn == null) {
            return ResponseEntity.badRequest().build();
        }
        Driver driver = driverService.getDriverById(loggedIn.getIdUser());
        if (driver == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(driver);
    }

    @PutMapping("/driver/profile")
    public ResponseEntity<Driver> updateProfile(@RequestBody Driver driver, HttpSession session) {
        User loggedIn = (User) session.getAttribute("loggedInUser");
        if (loggedIn == null) {
            return ResponseEntity.badRequest().build();
        }
        driver.setIdDriver(loggedIn.getIdUser());
        Driver updated = driverService.saveDriver(driver);
        return ResponseEntity.ok(updated);
    }

    @PutMapping("/driver/status/{id}")
    public ResponseEntity<Void> updateStatus(@PathVariable int id,
                                             @RequestParam int status) {
        try {
            driverService.updateStatus(id, status);
            return ResponseEntity.ok().build();
        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest().build();
        }
    }
}

