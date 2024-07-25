package com.pack.spring.controller;

import com.pack.spring.dto.CountryDTO;
import com.pack.spring.service.CountryService;
import com.pack.spring.service.StateService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.lang.module.ResolutionException;
import java.util.List;

@RestController
@RequestMapping("/api/countries")
@CrossOrigin("*")
public class CountryController {

    @Autowired
    private CountryService countryService;
    
    @Autowired
    private StateService stateService;

    @PostMapping
    public ResponseEntity<CountryDTO> createCountry(@RequestBody CountryDTO countryDTO) {
        CountryDTO saveCountry = countryService.saveCountry(countryDTO);
        return ResponseEntity.ok(saveCountry);
    }
    
   


    @GetMapping("/{id}")
    public ResponseEntity<CountryDTO> getCountryById(@PathVariable Long id) {
        CountryDTO country = countryService.getCountryById(id);
        return ResponseEntity.ok(country);
    }

    @GetMapping
    public ResponseEntity<List<CountryDTO>> getAllCountries() {
        List<CountryDTO> allCountries = countryService.getAllCountries();
        return ResponseEntity.ok(allCountries);
    }

    @PutMapping("/{id}")
    public ResponseEntity<CountryDTO> updateCountry(@PathVariable Long id, @RequestBody CountryDTO countryDTO) {
        CountryDTO updateCountry = countryService.updateCountry(id, countryDTO);
        return ResponseEntity.ok(updateCountry);
    }

    /*@DeleteMapping("/{id}")
    public ResponseEntity<String> deleteCountry(@PathVariable Long id) {
    	 // Check if there are states associated with this country
        if (stateService.existsByCountryId(id)) {
            return ResponseEntity.status(HttpStatus.FORBIDDEN).body("Cannot delete country with associated states.");
        }
    	
        countryService.deleteCountry(id);
        return ResponseEntity.ok("Country deleted successfully!");
    }*/
    
    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteCountry(@PathVariable Long id) {
        try {
            countryService.deleteCountry(id);
            return ResponseEntity.ok("Country deleted successfully!");
        } catch (IllegalStateException e) {
            return ResponseEntity.status(HttpStatus.FORBIDDEN).body(e.getMessage());
        }
    }
    

    @GetMapping("/countStates")
    public ResponseEntity<Long> countStatesByCountryName(@RequestParam String countryName) {
        Long stateCount = countryService.countStatesByCountryName(countryName);
        return ResponseEntity.ok(stateCount);
    }
    
}
