package com.pack.spring.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.pack.spring.model.State;

public interface StateRepository extends JpaRepository<State, Long>{

	boolean existsByCountryId(Long countryId);

}
