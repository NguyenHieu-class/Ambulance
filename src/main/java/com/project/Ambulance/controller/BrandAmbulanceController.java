package com.project.Ambulance.controller;

import com.project.Ambulance.model.BrandAmbulance;
import com.project.Ambulance.service.BrandAmbulanceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
public class BrandAmbulanceController {

    @Autowired
    private BrandAmbulanceService brandAmbulanceService;

    // === ADMIN APIs ===

    @GetMapping("/admin/brand-ambulances")
    public ResponseEntity<?> getAllBrands() {
        List<BrandAmbulance> brands = brandAmbulanceService.getAllBrands();
        return ResponseEntity.ok(brands);
    }

    @GetMapping("/admin/brand-ambulance/{id}")
    public ResponseEntity<?> getBrandById(@PathVariable int id) {
        BrandAmbulance brand = brandAmbulanceService.getBrandById(id);
        if (brand == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(brand);
    }

    @PostMapping("/admin/brand-ambulances")
    public ResponseEntity<?> createBrand(@RequestBody BrandAmbulance brand) {
        BrandAmbulance saved = brandAmbulanceService.saveBrand(brand);
        return ResponseEntity.ok(saved);
    }

    @PutMapping("/admin/brand-ambulance/{id}")
    public ResponseEntity<?> updateBrand(@PathVariable int id,
                                         @RequestBody BrandAmbulance brand) {
        brand.setIdBrand(id);
        BrandAmbulance updated = brandAmbulanceService.saveBrand(brand);
        return ResponseEntity.ok(updated);
    }

    @DeleteMapping("/admin/brand-ambulance/{id}")
    public ResponseEntity<?> deleteBrand(@PathVariable int id) {
        brandAmbulanceService.deleteBrand(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/admin/brand-ambulances/search")
    public ResponseEntity<?> searchBrandsByName(@RequestParam String name) {
        List<BrandAmbulance> brands = brandAmbulanceService.searchBrandByName(name);
        return ResponseEntity.ok(brands);
    }
}
