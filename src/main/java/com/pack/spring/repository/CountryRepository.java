package com.pack.spring.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.pack.spring.model.Country;

public interface CountryRepository extends JpaRepository<Country, Long> {
	boolean existsById(Long id);
	
	boolean existsByCountry(String country);

	@Query("SELECT COUNT(s) FROM State s JOIN s.country c WHERE c.country = :countryName")
    Long countStatesByCountryName(@Param("countryName") String countryName);

}
