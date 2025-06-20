package com.project.Ambulance.service;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.project.Ambulance.model.Vehicle;
import com.project.Ambulance.repository.VehicleRepository;

@Service
public class VehicleService {
    private final VehicleRepository vehicleRepository;

    public VehicleService(VehicleRepository vehicleRepository) {
        this.vehicleRepository = vehicleRepository;
    }

    public Vehicle create(Vehicle vehicle) {
        return vehicleRepository.save(vehicle);
    }

    public List<Vehicle> readAll() {
        return vehicleRepository.findAll();
    }

    public Optional<Vehicle> read(int id) {
        return vehicleRepository.findById(id);
    }

    public Vehicle update(int id, Vehicle vehicle) {
        vehicle.setIdVehicle(id);
        return vehicleRepository.save(vehicle);
    }

    public void delete(int id) {
        vehicleRepository.deleteById(id);
    }
}
