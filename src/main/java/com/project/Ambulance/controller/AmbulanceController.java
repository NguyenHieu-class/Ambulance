package com.project.Ambulance.controller;

import com.project.Ambulance.model.Ambulance;
import com.project.Ambulance.service.AmbulanceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
public class AmbulanceController {

    @Autowired
    private AmbulanceService ambulanceService;

    // === ADMIN APIs ===

    @GetMapping("/admin/ambulances")
    public ResponseEntity<List<Ambulance>> getAllAmbulances() {
        List<Ambulance> ambulances = ambulanceService.getAllAmbulances();
        return ResponseEntity.ok(ambulances);
    }

    @GetMapping("/admin/ambulance/{id}")
    public ResponseEntity<Ambulance> getAmbulanceById(@PathVariable int id) {
        Ambulance ambulance = ambulanceService.getAmbulanceById(id);
        if (ambulance == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(ambulance);
    }

    @PostMapping("/admin/ambulances")
    public ResponseEntity<Ambulance> createAmbulance(@RequestBody Ambulance ambulance) {
        Ambulance saved = ambulanceService.saveAmbulance(ambulance);
        return ResponseEntity.ok(saved);
    }

    @PutMapping("/admin/ambulance/{id}")
    public ResponseEntity<Ambulance> updateAmbulance(@PathVariable int id,
                                                     @RequestBody Ambulance ambulance) {
        ambulance.setIdAmbulance(id);
        Ambulance updated = ambulanceService.saveAmbulance(ambulance);
        return ResponseEntity.ok(updated);
    }

    @DeleteMapping("/admin/ambulance/{id}")
    public ResponseEntity<Void> deleteAmbulance(@PathVariable int id) {
        ambulanceService.deleteAmbulance(id);
        return ResponseEntity.noContent().build();
    }

    @PutMapping("/admin/ambulance/{id}/status")
    public ResponseEntity<Void> updateAmbulanceStatus(@PathVariable int id,
                                                      @RequestParam int status) {
        try {
            ambulanceService.updateStatus(id, status);
            return ResponseEntity.ok().build();
        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest().build();
        }
    }

    @GetMapping("/admin/ambulances/search")
    public ResponseEntity<List<Ambulance>> searchAmbulances(
            @RequestParam(required = false) Integer status,
            @RequestParam(required = false) Integer hospitalId,
            @RequestParam(required = false) Integer driverId,
            @RequestParam(required = false) String location) {
        List<Ambulance> ambulances;
        if (status != null) {
            ambulances = ambulanceService.getAmbulancesByStatus(status);
        } else if (hospitalId != null) {
            ambulances = ambulanceService.getAmbulancesByHospital(hospitalId);
        } else if (driverId != null) {
            ambulances = ambulanceService.getAmbulancesByDriver(driverId);
        } else if (location != null) {
            ambulances = ambulanceService.searchAmbulancesByLocation(location);
        } else {
            ambulances = ambulanceService.getAllAmbulances();
        }
        return ResponseEntity.ok(ambulances);
    }
}
