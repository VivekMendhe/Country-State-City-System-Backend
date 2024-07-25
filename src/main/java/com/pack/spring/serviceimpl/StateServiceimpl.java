package com.pack.spring.serviceimpl;

import java.lang.module.ResolutionException;
import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.pack.spring.dto.StateDTO;
import com.pack.spring.model.City;
import com.pack.spring.model.Country;
import com.pack.spring.model.State;
import com.pack.spring.repository.CityRepository;
import com.pack.spring.repository.CountryRepository;
import com.pack.spring.repository.StateRepository;
import com.pack.spring.service.StateService;

@Service
public class StateServiceimpl implements StateService {

	@Autowired
	private StateRepository stateRepository;

	@Autowired
	private CountryRepository countryRepository;

	@Autowired
	private CityRepository cityRepository;

	@Autowired
	private ModelMapper modelMapper;

	@Override
	public StateDTO saveState(StateDTO stateDTO) {
		State state = dtoToEntity(stateDTO);
		state = stateRepository.save(state);
		return entityToDTO(state);
	}

	@Override
	public StateDTO getStateById(Long id) {
		State state = stateRepository.findById(id).orElseThrow(() -> new RuntimeException("State not found"));
		return entityToDTO(state);
	}

	@Override
	public List<StateDTO> getAllStates() {
		return stateRepository.findAll().stream().map(this::entityToDTO).collect(Collectors.toList());
	}

	@Override
	public StateDTO updateState(Long id, StateDTO stateDTO) {
		State state = stateRepository.findById(id).orElseThrow(() -> new RuntimeException("State not found"));
		state.setState(stateDTO.getState());

		Country country = countryRepository.findById(stateDTO.getCountryId())
				.orElseThrow(() -> new RuntimeException("Country not found"));
		state.setCountry(country);

		state = stateRepository.save(state);
		return entityToDTO(state);
	}

	@Override
	public boolean existsByCountryId(Long countryId) {
		return stateRepository.existsByCountryId(countryId);
	}

	/*
	 * @Override public void deleteState(Long id) { stateRepository.deleteById(id);
	 * }
	 */

	@Override
	public void deleteState(Long id) {
		if (stateRepository.existsById(id)) {
			
			 List<String> cityNames = cityRepository.findByStateId(id).stream()
                     .map(City::getCity)
                     .collect(Collectors.toList());
			
			 if (!cityNames.isEmpty()) {
		            throw new IllegalStateException("Cannot delete country as it has dependent states: " + String.join(", ", cityNames));
		        }
			stateRepository.deleteById(id);
		} else {
			throw new ResolutionException("State not found");
		}		        
	}
	
	
	
	@Override
    public long getCityCountByStateName(String stateName) {
        return stateRepository.countCitiesByStateName(stateName);
    }

	// Convert dto to entity
	private State dtoToEntity(StateDTO stateDTO) {
		return modelMapper.map(stateDTO, State.class);
	}

	// Convert entity to dto
	private StateDTO entityToDTO(State state) {
		return modelMapper.map(state, StateDTO.class);
	}

}
