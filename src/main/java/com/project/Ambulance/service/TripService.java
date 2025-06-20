package com.project.Ambulance.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.project.Ambulance.model.Trip;
import com.project.Ambulance.repository.TripRepository;

@Service
public class TripService {
    private final TripRepository tripRepository;

    public TripService(TripRepository tripRepository) {
        this.tripRepository = tripRepository;
    }

    public Trip create(Trip trip) {
        return tripRepository.save(trip);
    }

    public List<Trip> readAll() {
        return tripRepository.findAll();
    }

    public Optional<Trip> read(int id) {
        return tripRepository.findById(id);
    }

    public Trip update(int id, Trip trip) {
        trip.setIdTrip(id);
        return tripRepository.save(trip);
    }

    public void delete(int id) {
        tripRepository.deleteById(id);
    }

    public List<Trip> getTripsForUser(int userId) {
        List<Trip> result = new ArrayList<>();
        result.addAll(tripRepository.findByDriver_IdUser(userId));
        result.addAll(tripRepository.findByMedicalStaffId(userId));
        return result;
    }
}
