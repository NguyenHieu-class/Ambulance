package com.project.Ambulance.service;

import com.project.Ambulance.model.TransportRequest;
import com.project.Ambulance.model.TransportRequest.Status;

import java.util.List;

public interface TransportRequestService {

    List<TransportRequest> getAllTransportRequest();

    TransportRequest getTransportRequestById(Long id);

    void saveTransportRequest(TransportRequest transportRequest);

    void deleteTransportRequest(Long id);

    long countTransportRequest();

    List<TransportRequest> getTransportRequestByStatus(Status status);

    List<TransportRequest> getTransportRequestByMedicalStaffId(Long medicalStaffId);

    List<TransportRequest> getTransportRequestByDriverId(Long driverId);

    List<TransportRequest> getTransportRequestByHospitalId(Long hospitalId);
}
