package com.project.Ambulance.service;

import com.project.Ambulance.model.MedicalStaff;
import com.project.Ambulance.repository.MedicalStaffRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MedicalStaffServiceImpl implements MedicalStaffService {

    @Autowired
    private MedicalStaffRepository medicalStaffRepository;

    @Override
    public List<MedicalStaff> getAllMedicalStaff() {
        return medicalStaffRepository.findAllByOrderByFullNameAsc();
    }

    @Override
    public List<MedicalStaff> getByHospital(int hospitalId) {
        return medicalStaffRepository.findByHospitalIdHospitalOrderByFullNameAsc(hospitalId);
    }

    @Override
    public List<MedicalStaff> getByStatus(int status) {
        return medicalStaffRepository.findByStatusOrderByFullNameAsc(status);
    }

    @Override
    public List<MedicalStaff> getByHospitalAndStatus(int hospitalId, int status) {
        return medicalStaffRepository.findByHospitalIdHospitalAndStatusOrderByFullNameAsc(hospitalId, status);
    }

    @Override
    public List<MedicalStaff> searchByName(String name) {
        return medicalStaffRepository.findByFullNameContainingIgnoreCaseOrderByFullNameAsc(name);
    }

    @Override
    public List<MedicalStaff> searchByPhone(String phone) {
        return medicalStaffRepository.findByPhoneContainingIgnoreCaseOrderByFullNameAsc(phone);
    }

    @Override
    public List<MedicalStaff> searchByLicenseNumber(String licenseNumber) {
        return medicalStaffRepository.findByLicenseNumberContainingIgnoreCaseOrderByFullNameAsc(licenseNumber);
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
}
