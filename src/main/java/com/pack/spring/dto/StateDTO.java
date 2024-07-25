package com.pack.spring.dto;

import lombok.Data;

@Data
public class StateDTO {
    private Long id;
    private String state;
    private String population;
    private Long cityCount;
    private Long countryId;
}
