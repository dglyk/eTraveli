package com.example.etraveli.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CountryDTO {

    private String numeric;
    private String alpha2;
    private String name;
    private String emoji;
    private String currency;
    private float latitude;
    private float longitude;
}
