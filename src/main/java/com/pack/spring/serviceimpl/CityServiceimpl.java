package com.pack.spring.serviceimpl;

import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.pack.spring.dto.CityDTO;
import com.pack.spring.model.City;
import com.pack.spring.model.State;
import com.pack.spring.repository.CityRepository;
import com.pack.spring.repository.StateRepository;
import com.pack.spring.service.CityService;

@Service
public class CityServiceimpl implements CityService {

	@Autowired
	private ModelMapper modelMapper;

	@Autowired
	private CityRepository cityRepository;

	@Autowired
	private StateRepository stateRepository;

	@Override
	public CityDTO saveCity(CityDTO cityDTO) {
		City city = dtoToEntity(cityDTO);
		city = cityRepository.save(city);
		return entityToDTO(city);
	}

	@Override
	public CityDTO getCityById(Long id) {
		City city = cityRepository.findById(id).orElseThrow(() -> new RuntimeException("City not found"));
		return entityToDTO(city);
	}

	@Override
	public List<CityDTO> getAllCities() {
		return cityRepository.findAll().stream().map(this::entityToDTO).collect(Collectors.toList());
	}

	@Override
	public CityDTO updateCity(Long id, CityDTO cityDTO) {
		City city = cityRepository.findById(id).orElseThrow(() -> new RuntimeException("City not found"));
		city.setCity(cityDTO.getCity());

		State state = stateRepository.findById(cityDTO.getStateId())
				.orElseThrow(() -> new RuntimeException("State not found"));
		city.setState(state);

		city = cityRepository.save(city);
		return entityToDTO(city);
	}

	/*@Override
	public void deleteCity(Long id) {
		 // Check if the city exists
        if (cityRepository.existsById(id)) {
            // Check if the city has any dependent records (like states)
            City city = cityRepository.findById(id).orElseThrow(() -> new ResolutionException("City not found"));

            if (stateRepository.existsByCountryId(city.getState().getCountry().getId())) {
                throw new IllegalStateException("Cannot delete city as it has dependent states");
            }

            cityRepository.deleteById(id);
        } else {
            throw new ResolutionException("City not found");
        }
	}*/
	
	@Override
	public void deleteCity(Long id) {
		cityRepository.deleteById(id);
	}
	
	@Override
	public boolean existsByStateId(Long id) {
		return cityRepository.existsByStateId(id);
	}

	// Convert dto to entity
	private City dtoToEntity(CityDTO cityDTO) {
		return modelMapper.map(cityDTO, City.class);
	}

	// Convert entity to dto
	private CityDTO entityToDTO(City city) {
		return modelMapper.map(city, CityDTO.class);
	}
	
	
}
