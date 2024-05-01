package com.example.etraveli.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class BankDTO {

    private String name;
    private String url;
    private String phone;
    private String city;
}
