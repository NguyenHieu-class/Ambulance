package com.project.Ambulance.repository;

import com.project.Ambulance.model.Ward;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface WardRepository extends JpaRepository<Ward, Integer> {

    @Query(value = "SELECT w.id_ward, w.id_district, w.name_ward, w.create_date, w.update_date, d.name_district " +
            "FROM ward w JOIN district d ON d.id_district = w.id_district", nativeQuery = true)
    List<Ward> getAllWardWithDistrict();

    @Query(value = "SELECT w.id_ward, w.id_district, w.name_ward, w.create_date, w.update_date, " +
            "d.name_district, p.id_province, p.name_province FROM ward w " +
            "JOIN district d ON d.id_district = w.id_district " +
            "JOIN province p ON p.id_province = d.id_province", nativeQuery = true)
    List<Ward> getAllWardWithDistrictAndProvinces();

    List<Ward> findWardByDistrictIdDistrict(int idDistrict);

    long count();
}