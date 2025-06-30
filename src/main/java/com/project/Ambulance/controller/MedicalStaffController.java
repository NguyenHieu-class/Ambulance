package com.project.Ambulance.controller;

import com.project.Ambulance.model.MedicalStaff;
import com.project.Ambulance.service.MedicalStaffService;
import com.project.Ambulance.constants.AppConstants;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
public class MedicalStaffController {

    @Autowired
    private MedicalStaffService medicalStaffService;

    // === ADMIN APIs ===
    @GetMapping("/admin/medicalStaffs")
    public ResponseEntity<?> getAllMedicalStaff() {
        List<MedicalStaff> staff = medicalStaffService.getAllMedicalStaff();
        return ResponseEntity.ok(staff);
    }

    @GetMapping("/admin/medicalStaff/{id}")
    public ResponseEntity<?> getMedicalStaffById(@PathVariable int id) {
        MedicalStaff staff = medicalStaffService.getById(id);
        if (staff == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(staff);
    }

    @PostMapping("/admin/medicalStaffs")
    public ResponseEntity<?> createMedicalStaff(@RequestBody MedicalStaff staff) {
        // Newly created staff are available by default
        staff.setStatus(AppConstants.MEDICAL_STATUS_AVAILABLE);
        MedicalStaff saved = medicalStaffService.save(staff);
        return ResponseEntity.ok(saved);
    }

    @PutMapping("/admin/medicalStaff/{id}")
    public ResponseEntity<?> updateMedicalStaff(@PathVariable int id,
                                                @RequestBody MedicalStaff staff) {
        staff.setIdMedicalStaff(id);
        MedicalStaff updated = medicalStaffService.save(staff);
        return ResponseEntity.ok(updated);
    }

    @DeleteMapping("/admin/medicalStaff/{id}")
    public ResponseEntity<?> deleteMedicalStaff(@PathVariable int id) {
        medicalStaffService.delete(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/admin/medicalStaffs/search")
    public ResponseEntity<?> searchMedicalStaff(@RequestParam(required = false) String name,
                                                @RequestParam(required = false) String phone,
                                                @RequestParam(required = false) String licenseNumber) {
        List<MedicalStaff> result;
        if (name != null) {
            result = medicalStaffService.searchByName(name);
        } else if (phone != null) {
            result = medicalStaffService.searchByPhone(phone);
        } else if (licenseNumber != null) {
            result = medicalStaffService.searchByLicenseNumber(licenseNumber);
        } else {
            result = medicalStaffService.getAllMedicalStaff();
        }
        return ResponseEntity.ok(result);
    }

    @PutMapping("/admin/medicalStaff/{id}/status")
    public ResponseEntity<?> updateMedicalStaffStatus(@PathVariable int id,
                                                      @RequestParam int status) {
        try {
            medicalStaffService.updateStatus(id, status);
            return ResponseEntity.ok().build();
        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest().build();
        }
    }

    // === MEDICAL STAFF APIs ===
    @GetMapping("/medical/profile/{id}")
    public ResponseEntity<?> getProfile(@PathVariable int id) {
        MedicalStaff staff = medicalStaffService.getById(id);
        if (staff == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(staff);
    }

    @PutMapping("/medical/profile/{id}")
    public ResponseEntity<?> updateProfile(@PathVariable int id,
                                           @RequestBody MedicalStaff staff) {
        staff.setIdMedicalStaff(id);
        MedicalStaff updated = medicalStaffService.save(staff);
        return ResponseEntity.ok(updated);
    }

    @PutMapping("/medical/status/{id}")
    public ResponseEntity<?> updateStatus(@PathVariable int id,
                                          @RequestParam int status) {
        try {
            medicalStaffService.updateStatus(id, status);
            return ResponseEntity.ok().build();
        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest().build();
        }
    }
}

