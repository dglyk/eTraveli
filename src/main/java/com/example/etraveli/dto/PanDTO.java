package com.example.etraveli.dto;

import jakarta.validation.constraints.Pattern;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class PanDTO {

    @Pattern(regexp = "^\\d{8,19}$", message = "PAN number should only contain digits and have a length from 8 to 19.")
    private String card_number;

}
