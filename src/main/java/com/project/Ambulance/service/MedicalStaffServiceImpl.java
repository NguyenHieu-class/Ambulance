package com.project.Ambulance.service;

import com.project.Ambulance.model.MedicalStaff;
import com.project.Ambulance.repository.MedicalStaffRepository;
import com.project.Ambulance.constants.AppConstants;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MedicalStaffServiceImpl implements MedicalStaffService {

    @Autowired
    private MedicalStaffRepository medicalStaffRepository;

    @Override
    public List<MedicalStaff> getAllMedicalStaff() {
        return medicalStaffRepository.findAllByOrderByNameAsc();
    }

    @Override
    public List<MedicalStaff> getByHospital(int hospitalId) {
        return medicalStaffRepository.findByHospitalIdHospitalOrderByNameAsc(hospitalId);
    }

    @Override
    public List<MedicalStaff> getByStatus(int status) {
        return medicalStaffRepository.findByStatusOrderByNameAsc(status);
    }

    @Override
    public List<MedicalStaff> getAvailableMedicalStaff() {
        return medicalStaffRepository.findByStatusOrderByNameAsc(AppConstants.MEDICAL_STATUS_AVAILABLE);
    }

    @Override
    public List<MedicalStaff> getByHospitalAndStatus(int hospitalId, int status) {
        return medicalStaffRepository.findByHospitalIdHospitalAndStatusOrderByNameAsc(hospitalId, status);
    }

    @Override
    public List<MedicalStaff> searchByName(String name) {
        return medicalStaffRepository.findByNameContainingIgnoreCaseOrderByNameAsc(name);
    }

    @Override
    public List<MedicalStaff> searchByPhone(String phone) {
        return medicalStaffRepository.findByPhoneContainingIgnoreCaseOrderByNameAsc(phone);
    }

    @Override
    public List<MedicalStaff> searchByLicenseNumber(String licenseNumber) {
        return medicalStaffRepository.findByLicenseNumberContainingIgnoreCaseOrderByNameAsc(licenseNumber);
    }

    @Override
    public MedicalStaff getById(int id) {
        return medicalStaffRepository.findById(id).orElse(null);
    }

    @Override
    public MedicalStaff save(MedicalStaff staff) {
        return medicalStaffRepository.save(staff);
    }

    @Override
    public void delete(int id) {
        medicalStaffRepository.deleteById(id);
    }

    @Override
    public void updateStatus(int id, int status) {
        if (status != AppConstants.MEDICAL_STATUS_AVAILABLE &&
            status != AppConstants.MEDICAL_STATUS_ON_DUTY &&
            status != AppConstants.MEDICAL_STATUS_SUSPENDED) {
            throw new IllegalArgumentException("Invalid medical staff status: " + status);
        }
        medicalStaffRepository.updateStatus(id, status);
    }

    @Override
    public int countByHospital(int hospitalId) {
        return medicalStaffRepository.countByHospitalIdHospital(hospitalId);
    }

    @Override
    public long countAll() {
        return medicalStaffRepository.count();
    }

    @Override
    public List<MedicalStaff> getByIds(List<Integer> ids) {
        return medicalStaffRepository.findByIdMedicalStaffIn(ids);
    }
}
