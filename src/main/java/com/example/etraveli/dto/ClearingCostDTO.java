package com.example.etraveli.dto;

import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Data
public class ClearingCostDTO {
    private Long id;
    private String issuingCountry;
    private double cost;
    private String currency;
}
