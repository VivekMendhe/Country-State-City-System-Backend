package com.pack.spring.service;

import java.util.List;

import com.pack.spring.dto.StateDTO;

public interface StateService {
	StateDTO saveState(StateDTO stateDTO);

	StateDTO getStateById(Long id);

	List<StateDTO> getAllStates();

	StateDTO updateState(Long id, StateDTO stateDTO);

	void deleteState(Long id);

	boolean existsByCountryId(Long id);
	
	long getCityCountByStateName(String stateName);
}
