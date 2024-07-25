package com.pack.spring.serviceimpl;

import java.lang.module.ResolutionException;
import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.pack.spring.dto.CityDTO;
import com.pack.spring.dto.CountryDTO;
import com.pack.spring.model.City;
import com.pack.spring.model.Country;
import com.pack.spring.model.State;
import com.pack.spring.repository.CountryRepository;
import com.pack.spring.repository.StateRepository;
import com.pack.spring.service.CountryService;

@Service
public class CountryServiceimpl implements CountryService {

	@Autowired
	private CountryRepository countryRepository;

	@Autowired
	private StateRepository stateRepository;

	@Autowired
	private ModelMapper modelMapper;

	@Override
	public CountryDTO saveCountry(CountryDTO countryDTO) {
		Country country = dtoToEntity(countryDTO);
		country = countryRepository.save(country);
		return entityToDTO(country);
	}

	@Override
	public CountryDTO getCountryById(Long id) {
		Country country = countryRepository.findById(id).orElseThrow(() -> new RuntimeException("Country not found"));
		return entityToDTO(country);
	}

	@Override
	public List<CountryDTO> getAllCountries() {
		return countryRepository.findAll().stream().map(this::entityToDTO).collect(Collectors.toList());
	}

	@Override
	public CountryDTO updateCountry(Long id, CountryDTO countryDTO) {
		Country country = countryRepository.findById(id).orElseThrow(() -> new RuntimeException("Country not found"));
		country.setCountry(countryDTO.getCountry());
		country.setPopulation(countryDTO.getPopulation());
		country = countryRepository.save(country);
		return entityToDTO(country);
	}

	/*
	 * @Override public void deleteCountry(Long id) {
	 * countryRepository.deleteById(id); }
	 */

	/*@Override
	public void deleteCountry(Long id) {
		if (countryRepository.existsById(id)) {
			if (stateRepository.existsByCountryId(id)) {
				throw new IllegalStateException("Cannot delete country as it has dependent states");
			}
			countryRepository.deleteById(id);
		} else {
			throw new ResolutionException("Country not found");
		}
	}*/
	
	@Override
	public void deleteCountry(Long id) {
	    if (countryRepository.existsById(id)) {
	        List<String> stateNames = stateRepository.findByCountryId(id).stream()
	                                    .map(State::getState)
	                                    .collect(Collectors.toList());

	        if (!stateNames.isEmpty()) {
	            throw new IllegalStateException("Cannot delete country as it has dependent states: " + String.join(", ", stateNames));
	        }

	        countryRepository.deleteById(id);
	    } else {
	        throw new ResolutionException("Country not found");
	    }
	}

	

	@Override
	public Long countStatesByCountryName(String countryName) {
		return countryRepository.countStatesByCountryName(countryName);
	}

	// Convert dto to entity
	private Country dtoToEntity(CountryDTO countryDTO) {
		return modelMapper.map(countryDTO, Country.class);
	}

	// Convert entity to dto
	private CountryDTO entityToDTO(Country country) {
		return modelMapper.map(country, CountryDTO.class);
	}

}
