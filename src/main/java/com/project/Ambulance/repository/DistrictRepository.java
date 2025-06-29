package com.project.Ambulance.repository;

import com.project.Ambulance.model.District;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface DistrictRepository extends JpaRepository<District, Integer> {

    @Query(value = "SELECT d.id_district, d.name_district, d.create_date, d.update_date, p.id_province, p.name_province " +
            "FROM district d JOIN province p ON p.id_province = d.id_province", nativeQuery = true)
    List<District> getAllDistrictWithProvince();

    List<District> findByProvinceIdProvince(int idProvince);

    long count();
}