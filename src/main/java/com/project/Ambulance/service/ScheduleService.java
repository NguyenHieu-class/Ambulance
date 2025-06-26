package com.project.Ambulance.service;

import com.project.Ambulance.model.Schedule;
import com.project.Ambulance.model.Schedule.Shift;

import java.time.LocalDate;
import java.util.List;

public interface ScheduleService {

    List<Schedule> getAllSchedule();

    Schedule getScheduleById(Long id);

    void saveSchedule(Schedule schedule);

    void deleteSchedule(Long id);

    long countSchedule();

    List<Schedule> getScheduleByDate(LocalDate date);

    List<Schedule> getScheduleByDateAndShift(LocalDate date, Shift shift);

    List<Schedule> getScheduleByAmbulanceId(Long ambulanceId);

    List<Schedule> getScheduleByDriverId(Long driverId);

    boolean existsByAmbulanceAndDateAndShift(Long ambulanceId, LocalDate date, Shift shift);

    boolean existsByDriverAndDateAndShift(Long driverId, LocalDate date, Shift shift);
}
