package com.example.etraveli.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class BinListResponseDTO {
    private NumberDTO number;
    private String scheme;
    private String type;
    private String brand;
    private boolean prepaid;
    private CountryDTO country;
    private BankDTO bank;
}
