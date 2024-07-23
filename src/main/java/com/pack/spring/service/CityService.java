package com.pack.spring.service;

import java.util.List;

import com.pack.spring.dto.CityDTO;

public interface CityService {

	CityDTO saveCity(CityDTO cityDTO);

	CityDTO getCityById(Long id);

	List<CityDTO> getAllCities();

	CityDTO updateCity(Long id, CityDTO cityDTO);

	void deleteCity(Long id);

	boolean existsByStateId(Long id);

}
