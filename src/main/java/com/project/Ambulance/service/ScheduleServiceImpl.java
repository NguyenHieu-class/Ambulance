package com.project.Ambulance.service;

import com.project.Ambulance.model.Schedule;
import com.project.Ambulance.model.Schedule.Shift;
import com.project.Ambulance.repository.ScheduleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Service
public class ScheduleServiceImpl implements ScheduleService {

    @Autowired
    private ScheduleRepository scheduleRepository;

    @Override
    public List<Schedule> getAllSchedule() {
        return scheduleRepository.findAll();
    }

    @Override
    public Schedule getScheduleById(Long id) {
        Optional<Schedule> optional = scheduleRepository.findById(id);
        if (optional.isPresent()) {
            return optional.get();
        } else {
            throw new RuntimeException("Không tìm thấy lịch trực với ID: " + id);
        }
    }

    @Override
    public void saveSchedule(Schedule schedule) {
        scheduleRepository.save(schedule);
    }

    @Override
    public void deleteSchedule(Long id) {
        scheduleRepository.deleteById(id);
    }

    @Override
    public long countSchedule() {
        return scheduleRepository.count();
    }

    @Override
    public List<Schedule> getScheduleByDate(LocalDate date) {
        return scheduleRepository.findByDate(date);
    }

    @Override
    public List<Schedule> getScheduleByDateAndShift(LocalDate date, Shift shift) {
        return scheduleRepository.findByDateAndShift(date, shift);
    }

    @Override
    public List<Schedule> getScheduleByAmbulanceId(Long ambulanceId) {
        return scheduleRepository.findByAmbulance_Id(ambulanceId);
    }

    @Override
    public List<Schedule> getScheduleByDriverId(Long driverId) {
        return scheduleRepository.findByDriver_Id(driverId);
    }

    @Override
    public boolean existsByAmbulanceAndDateAndShift(Long ambulanceId, LocalDate date, Shift shift) {
        return scheduleRepository.existsByDateAndShiftAndAmbulance_Id(date, shift, ambulanceId);
    }

    @Override
    public boolean existsByDriverAndDateAndShift(Long driverId, LocalDate date, Shift shift) {
        return scheduleRepository.existsByDateAndShiftAndDriver_Id(date, shift, driverId);
    }
}
