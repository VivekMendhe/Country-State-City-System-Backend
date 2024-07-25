package com.pack.spring.service;

import java.util.List;

import com.pack.spring.dto.CountryDTO;

public interface CountryService {
	CountryDTO saveCountry(CountryDTO countryDTO);

	CountryDTO getCountryById(Long id);

	List<CountryDTO> getAllCountries();

	CountryDTO updateCountry(Long id, CountryDTO countryDTO);

	void deleteCountry(Long id);
	
	Long countStatesByCountryName(String countryName);
	
}
