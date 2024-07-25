package com.pack.spring.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.pack.spring.model.Country;
import com.pack.spring.model.State;

public interface StateRepository extends JpaRepository<State, Long> {

	boolean existsByCountryId(Long countryId);

	@Query("SELECT COUNT(c) FROM City c WHERE c.state.state = :stateName")
	long countCitiesByStateName(@Param("stateName") String stateName);

	@Query("SELECT s FROM State s WHERE s.country.id = :countryId")
	List<State> findByCountryId(@Param("countryId") Long countryId);

}
