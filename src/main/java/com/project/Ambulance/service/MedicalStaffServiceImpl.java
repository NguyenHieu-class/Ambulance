package com.project.Ambulance.service;

import com.project.Ambulance.model.MedicalStaff;
import com.project.Ambulance.repository.MedicalStaffRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class MedicalStaffServiceImpl implements MedicalStaffService {

    @Autowired
    private MedicalStaffRepository medicalStaffRepository;

    @Override
    public List<MedicalStaff> getAllMedicalStaff() {
        return medicalStaffRepository.findAll();
    }

    @Override
    public MedicalStaff getMedicalStaffById(Long id) {
        Optional<MedicalStaff> optional = medicalStaffRepository.findById(id);
        if (optional.isPresent()) {
            return optional.get();
        } else {
            throw new RuntimeException("Không tìm thấy nhân viên y tế với ID: " + id);
        }
    }

    @Override
    public void saveMedicalStaff(MedicalStaff medicalStaff) {
        medicalStaffRepository.save(medicalStaff);
    }

    @Override
    public void deleteMedicalStaff(Long id) {
        medicalStaffRepository.deleteById(id);
    }

    @Override
    public long countMedicalStaff() {
        return medicalStaffRepository.count();
    }

    @Override
    public MedicalStaff getMedicalStaffByUsername(String username) {
        return medicalStaffRepository.findByUsername(username);
    }

    @Override
    public boolean existsByUsername(String username) {
        return medicalStaffRepository.existsByUsername(username);
    }

    @Override
    public List<MedicalStaff> getMedicalStaffByDepartment(String department) {
        return medicalStaffRepository.findByDepartment(department);
    }
}
