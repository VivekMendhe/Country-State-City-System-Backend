package com.pack.spring.controller;

import com.pack.spring.dto.CityDTO;
import com.pack.spring.service.CityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/cities")
@CrossOrigin("*")
public class CityController {

	@Autowired
	private CityService cityService;

	@PostMapping
	public ResponseEntity<CityDTO> createCity(@RequestBody CityDTO cityDTO) {
		CityDTO saveCity = this.cityService.saveCity(cityDTO);
		return ResponseEntity.ok(saveCity);
	}

	@GetMapping
	public ResponseEntity<List<CityDTO>> getAllCities() {
		List<CityDTO> allCities = this.cityService.getAllCities();
		return ResponseEntity.ok(allCities);
	}

	@GetMapping("/{id}")
	public ResponseEntity<CityDTO> getCityById(@PathVariable Long id) {
		CityDTO city = this.cityService.getCityById(id);
		return ResponseEntity.ok(city);
	}

	@PutMapping("/{id}")
	public ResponseEntity<CityDTO> updateCity(@PathVariable Long id, @RequestBody CityDTO cityDTO) {
		CityDTO updateCity = this.cityService.updateCity(id, cityDTO);
		return ResponseEntity.ok(updateCity);
	}

	@DeleteMapping("/{id}")
	public ResponseEntity<String> deleteCity(@PathVariable Long id) {
		this.cityService.deleteCity(id);
		return ResponseEntity.ok("City deleted successfully!");
	}
}
