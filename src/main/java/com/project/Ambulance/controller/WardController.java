package com.project.Ambulance.controller;

import com.project.Ambulance.model.Ward;
import com.project.Ambulance.service.WardService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
public class WardController {

    @Autowired
    private WardService wardService;

    // === ADMIN APIs ===
    @GetMapping("/admin/wards")
    public ResponseEntity<?> getAllWards() {
        List<Ward> wards = wardService.getAllWard();
        return ResponseEntity.ok(wards);
    }

    @GetMapping("/admin/ward/{id}")
    public ResponseEntity<?> getWardById(@PathVariable int id) {
        try {
            Ward ward = wardService.getAWard(id);
            return ResponseEntity.ok(ward);
        } catch (RuntimeException ex) {
            return ResponseEntity.notFound().build();
        }
    }

    @PostMapping("/admin/wards")
    public ResponseEntity<?> createWard(@RequestBody Ward ward) {
        wardService.saveWard(ward);
        return ResponseEntity.ok(ward);
    }

    @PutMapping("/admin/ward/{id}")
    public ResponseEntity<?> updateWard(@PathVariable int id,
                                        @RequestBody Ward ward) {
        ward.setIdWard(id);
        wardService.saveWard(ward);
        return ResponseEntity.ok(ward);
    }

    @DeleteMapping("/admin/ward/{id}")
    public ResponseEntity<?> deleteWard(@PathVariable int id) {
        wardService.deleteWard(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/admin/wards/by-district/{id}")
    public ResponseEntity<?> getWardsByDistrict(@PathVariable int id) {
        List<Ward> wards = wardService.getAllWardByIdDistrict(id);
        return ResponseEntity.ok(wards);
    }
}
