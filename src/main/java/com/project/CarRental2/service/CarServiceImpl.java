package com.project.Ambulance.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.project.Ambulance.model.Ambulance;
import com.project.Ambulance.repository.CarRepository;

@Service
public class CarServiceImpl implements CarService {

    @Autowired
    private CarRepository repository;

    @Override
    public void save(Ambulance ambulance) {
        repository.save(ambulance);
    }

    @Override
    public List<Ambulance> findAll() {
        return repository.findAll();
    }

    @Override
    public Ambulance findById(int id) {
        Optional<Ambulance> opt = repository.findById(id);
        return opt.orElse(null);
    }

    @Override
    public void deleteById(int id) {
        repository.deleteById(id);
    }
}
