package com.project.Ambulance.service;

import com.project.Ambulance.model.MedicalStaff;

import java.util.List;

public interface MedicalStaffService {

    List<MedicalStaff> getAllMedicalStaff();

    MedicalStaff getMedicalStaffById(Long id);

    void saveMedicalStaff(MedicalStaff medicalStaff);

    void deleteMedicalStaff(Long id);

    long countMedicalStaff();

    MedicalStaff getMedicalStaffByUsername(String username);

    boolean existsByUsername(String username);

    List<MedicalStaff> getMedicalStaffByDepartment(String department);
}
