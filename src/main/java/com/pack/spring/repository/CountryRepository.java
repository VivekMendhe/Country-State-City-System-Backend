package com.pack.spring.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.pack.spring.model.Country;

public interface CountryRepository extends JpaRepository<Country, Long>{
	boolean existsById(Long id);
}
