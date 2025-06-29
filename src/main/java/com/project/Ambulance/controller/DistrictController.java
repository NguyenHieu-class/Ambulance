package com.project.Ambulance.controller;

import com.project.Ambulance.model.District;
import com.project.Ambulance.service.DistrictService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
public class DistrictController {

    @Autowired
    private DistrictService districtService;

    // === ADMIN APIs ===
    @GetMapping("/admin/districts")
    public ResponseEntity<?> getAllDistricts() {
        List<District> districts = districtService.getAllDistrict();
        return ResponseEntity.ok(districts);
    }

    @GetMapping("/admin/district/{id}")
    public ResponseEntity<?> getDistrictById(@PathVariable int id) {
        try {
            District district = districtService.getDistrict(id);
            return ResponseEntity.ok(district);
        } catch (RuntimeException ex) {
            return ResponseEntity.notFound().build();
        }
    }

    @PostMapping("/admin/districts")
    public ResponseEntity<?> createDistrict(@RequestBody District district) {
        districtService.saveDistrict(district);
        return ResponseEntity.ok(district);
    }

    @PutMapping("/admin/district/{id}")
    public ResponseEntity<?> updateDistrict(@PathVariable int id,
                                            @RequestBody District district) {
        district.setIdDistrict(id);
        districtService.saveDistrict(district);
        return ResponseEntity.ok(district);
    }

    @DeleteMapping("/admin/district/{id}")
    public ResponseEntity<?> deleteDistrict(@PathVariable int id) {
        districtService.deleteDistrict(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/admin/districts/by-province/{id}")
    public ResponseEntity<?> getDistrictsByProvince(@PathVariable int id) {
        List<District> districts = districtService.getAllDistrictByIdProvince(id);
        return ResponseEntity.ok(districts);
    }
}
