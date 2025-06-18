package com.project.Ambulance.service;

import java.util.List;

import com.project.Ambulance.model.Ambulance;

public interface CarService {
    void save(Ambulance ambulance);
    List<Ambulance> findAll();
    Ambulance findById(int id);
    void deleteById(int id);
}
