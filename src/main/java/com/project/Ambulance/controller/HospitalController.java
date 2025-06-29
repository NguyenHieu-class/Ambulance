package com.project.Ambulance.controller;

import com.project.Ambulance.model.Hospital;
import com.project.Ambulance.service.HospitalService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
public class HospitalController {

    @Autowired
    private HospitalService hospitalService;

    // === ADMIN APIs ===

    @GetMapping("/admin/hospitals")
    public ResponseEntity<List<Hospital>> getAllHospitals() {
        List<Hospital> hospitals = hospitalService.getAllHospitals();
        return ResponseEntity.ok(hospitals);
    }

    @GetMapping("/admin/hospital/{id}")
    public ResponseEntity<Hospital> getHospitalById(@PathVariable int id) {
        Hospital hospital = hospitalService.getHospitalById(id);
        if (hospital == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(hospital);
    }

    @PostMapping("/admin/hospitals")
    public ResponseEntity<Hospital> createHospital(@RequestBody Hospital hospital) {
        Hospital saved = hospitalService.saveHospital(hospital);
        return ResponseEntity.ok(saved);
    }

    @PutMapping("/admin/hospital/{id}")
    public ResponseEntity<Hospital> updateHospital(@PathVariable int id,
                                                   @RequestBody Hospital hospital) {
        hospital.setIdHospital(id);
        Hospital updated = hospitalService.saveHospital(hospital);
        return ResponseEntity.ok(updated);
    }

    @DeleteMapping("/admin/hospital/{id}")
    public ResponseEntity<Void> deleteHospital(@PathVariable int id) {
        hospitalService.deleteHospital(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/admin/hospitals/search")
    public ResponseEntity<List<Hospital>> searchHospitals(@RequestParam(required = false) String name,
                                                          @RequestParam(required = false) String address) {
        List<Hospital> hospitals;
        if (name != null) {
            hospitals = hospitalService.searchHospitalsByName(name);
        } else if (address != null) {
            hospitals = hospitalService.searchHospitalsByAddress(address);
        } else {
            hospitals = hospitalService.getAllHospitals();
        }
        return ResponseEntity.ok(hospitals);
    }
}
