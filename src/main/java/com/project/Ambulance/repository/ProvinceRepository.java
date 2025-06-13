package com.project.Ambulance.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.project.Ambulance.model.Province;

@Repository
public interface ProvinceRepository extends JpaRepository<Province, Integer> {
	@Query(value =  "select * from Province order by name_Province asc", nativeQuery = true)
	List<Province> findAllProvinceOrderbyName();

	long count();
}
