package com.project.Ambulance.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.project.Ambulance.model.Trip;
import com.project.Ambulance.service.TripService;

@RestController
@RequestMapping("/api/trips")
public class TripController {
    private final TripService tripService;

    public TripController(TripService tripService) {
        this.tripService = tripService;
    }

    @PreAuthorize("hasRole('ADMIN')")
    @GetMapping
    public List<Trip> getAll() {
        return tripService.readAll();
    }

    @PreAuthorize("hasRole('ADMIN')")
    @GetMapping("/{id}")
    public Optional<Trip> getById(@PathVariable int id) {
        return tripService.read(id);
    }

    @PreAuthorize("hasRole('ADMIN')")
    @PostMapping
    public Trip create(@RequestBody Trip trip) {
        return tripService.create(trip);
    }

    @PreAuthorize("hasRole('ADMIN')")
    @PutMapping("/{id}")
    public Trip update(@PathVariable int id, @RequestBody Trip trip) {
        return tripService.update(id, trip);
    }

    @PreAuthorize("hasRole('ADMIN')")
    @DeleteMapping("/{id}")
    public void delete(@PathVariable int id) {
        tripService.delete(id);
    }

    @GetMapping("/assigned")
    public List<Trip> getAssignedTrips(@RequestParam int userId) {
        return tripService.getTripsForUser(userId);
    }
}
