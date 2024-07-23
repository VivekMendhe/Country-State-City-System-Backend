package com.pack.spring.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.pack.spring.model.City;

public interface CityRepository extends JpaRepository<City, Long>{

	boolean existsByStateId(Long id);

}
