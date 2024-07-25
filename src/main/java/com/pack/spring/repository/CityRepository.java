package com.pack.spring.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.pack.spring.model.City;

public interface CityRepository extends JpaRepository<City, Long>{

	boolean existsByStateId(Long id);
	
	@Query("SELECT c FROM City c WHERE c.state.id = :stateId")
	List<City> findByStateId(@Param("stateId") Long stateId);
}
