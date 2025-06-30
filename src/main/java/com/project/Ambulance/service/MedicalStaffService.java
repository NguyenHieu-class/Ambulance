package com.project.Ambulance.service;

import com.project.Ambulance.model.MedicalStaff;

import java.util.List;

public interface MedicalStaffService {

    List<MedicalStaff> getAllMedicalStaff();

    List<MedicalStaff> getByHospital(int hospitalId);

    List<MedicalStaff> getByStatus(int status);

    List<MedicalStaff> getAvailableMedicalStaff();

    List<MedicalStaff> getByHospitalAndStatus(int hospitalId, int status);

    List<MedicalStaff> searchByName(String name);

    List<MedicalStaff> searchByPhone(String phone);

    List<MedicalStaff> searchByLicenseNumber(String licenseNumber);

    MedicalStaff getById(int id);

    MedicalStaff save(MedicalStaff staff);

    void delete(int id);

    void updateStatus(int id, int status);

    int countByHospital(int hospitalId);

    long countAll();

    List<MedicalStaff> getByIds(List<Integer> ids);
}
