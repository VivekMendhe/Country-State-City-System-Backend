package com.pack.spring.controller;

import com.pack.spring.dto.StateDTO;
import com.pack.spring.service.CityService;
import com.pack.spring.service.StateService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/states")
@CrossOrigin("*")
public class StateController {

	@Autowired
	private StateService stateService;
	
	@Autowired
	private CityService cityService;

	@PostMapping
	public ResponseEntity<StateDTO> createState(@RequestBody StateDTO stateDTO) {
		StateDTO saveState = stateService.saveState(stateDTO);
		 return ResponseEntity.ok(saveState);
	}

	@GetMapping("/{id}")
	public ResponseEntity<StateDTO> getStateById(@PathVariable Long id) {
		StateDTO state = stateService.getStateById(id);
		return ResponseEntity.ok(state);
	}

	@GetMapping
	public ResponseEntity<List<StateDTO>> getAllStates() {
		List<StateDTO> states = stateService.getAllStates();
		return ResponseEntity.ok(states);
	}

	@PutMapping("/{id}")
	public ResponseEntity<StateDTO> updateState(@PathVariable Long id, @RequestBody StateDTO stateDTO) {
		StateDTO updateState = stateService.updateState(id, stateDTO);
		return ResponseEntity.ok(updateState);
	}

	/*@DeleteMapping("/{id}")
	public ResponseEntity<String> deleteState(@PathVariable Long id) {
		// Check if there are cities associated with this state
        if (cityService.existsByStateId(id)) {
            return ResponseEntity.status(HttpStatus.FORBIDDEN).body("Cannot delete state with associated cities.");
        }
		
		stateService.deleteState(id);
		return ResponseEntity.ok("State deleted successfully!");
	}*/
	
	@DeleteMapping("/{id}")
    public ResponseEntity<String> deleteState(@PathVariable Long id) {
        try {
            stateService.deleteState(id);
            return ResponseEntity.ok("State deleted successfully!");
        } catch (IllegalStateException e) {
            return ResponseEntity.status(HttpStatus.FORBIDDEN).body(e.getMessage());
        }
    }
}
