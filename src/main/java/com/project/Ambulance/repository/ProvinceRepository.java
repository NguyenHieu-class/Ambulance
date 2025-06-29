package com.project.Ambulance.repository;

import com.project.Ambulance.model.Province;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProvinceRepository extends JpaRepository<Province, Integer> {

    @Query(value = "SELECT * FROM province ORDER BY name_province ASC", nativeQuery = true)
    List<Province> findAllProvinceOrderByName();

    long count();
}