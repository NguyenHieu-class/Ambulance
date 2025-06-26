package com.project.Ambulance.service;

import com.project.Ambulance.model.TransportRequest;
import com.project.Ambulance.model.TransportRequest.Status;
import com.project.Ambulance.repository.TransportRequestRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class TransportRequestServiceImpl implements TransportRequestService {

    @Autowired
    private TransportRequestRepository transportRequestRepository;

    @Override
    public List<TransportRequest> getAllTransportRequest() {
        return transportRequestRepository.findAll();
    }

    @Override
    public TransportRequest getTransportRequestById(Long id) {
        Optional<TransportRequest> optional = transportRequestRepository.findById(id);
        if (optional.isPresent()) {
            return optional.get();
        } else {
            throw new RuntimeException("Không tìm thấy yêu cầu vận chuyển với ID: " + id);
        }
    }

    @Override
    public void saveTransportRequest(TransportRequest transportRequest) {
        transportRequestRepository.save(transportRequest);
    }

    @Override
    public void deleteTransportRequest(Long id) {
        transportRequestRepository.deleteById(id);
    }

    @Override
    public long countTransportRequest() {
        return transportRequestRepository.count();
    }

    @Override
    public List<TransportRequest> getTransportRequestByStatus(Status status) {
        return transportRequestRepository.findByStatus(status);
    }

    @Override
    public List<TransportRequest> getTransportRequestByMedicalStaffId(Long medicalStaffId) {
        return transportRequestRepository.findByRequester_Id(medicalStaffId);
    }

    @Override
    public List<TransportRequest> getTransportRequestByDriverId(Long driverId) {
        return transportRequestRepository.findByAssignedDriver_Id(driverId);
    }

    @Override
    public List<TransportRequest> getTransportRequestByHospitalId(Long hospitalId) {
        return transportRequestRepository.findByHospital_Id(hospitalId);
    }
}
