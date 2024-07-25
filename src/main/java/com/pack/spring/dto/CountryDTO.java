package com.pack.spring.dto;

import lombok.Data;

@Data
public class CountryDTO {
    private Long id;
    private String country;
    private Long stateCount;
    private String population;
}
