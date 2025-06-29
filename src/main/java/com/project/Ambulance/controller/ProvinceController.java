package com.project.Ambulance.controller;

import com.project.Ambulance.model.Province;
import com.project.Ambulance.service.ProvinceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
public class ProvinceController {

    @Autowired
    private ProvinceService provinceService;

    // === ADMIN APIs ===
    @GetMapping("/admin/provinces")
    public ResponseEntity<?> getAllProvinces() {
        List<Province> provinces = provinceService.getAllProvinceOrderByName();
        return ResponseEntity.ok(provinces);
    }

    @GetMapping("/admin/province/{id}")
    public ResponseEntity<?> getProvinceById(@PathVariable int id) {
        try {
            Province province = provinceService.getProvince(id);
            return ResponseEntity.ok(province);
        } catch (RuntimeException ex) {
            return ResponseEntity.notFound().build();
        }
    }

    @PostMapping("/admin/provinces")
    public ResponseEntity<?> createProvince(@RequestBody Province province) {
        provinceService.saveProvince(province);
        return ResponseEntity.ok(province);
    }

    @PutMapping("/admin/province/{id}")
    public ResponseEntity<?> updateProvince(@PathVariable int id,
                                            @RequestBody Province province) {
        province.setIdProvince(id);
        provinceService.saveProvince(province);
        return ResponseEntity.ok(province);
    }

    @DeleteMapping("/admin/province/{id}")
    public ResponseEntity<?> deleteProvince(@PathVariable int id) {
        provinceService.deleteProvince(id);
        return ResponseEntity.noContent().build();
    }
}
