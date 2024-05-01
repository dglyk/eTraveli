package com.example.etraveli.domain;


import jakarta.persistence.*;
import lombok.*;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Getter
@Setter
public class ClearingCost {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String issuingCountry;
    private double cost;
    private String currency;

    @Override
    public String toString(){
        return "\nId: " +this.id + "\nIssuing Country: " + this.issuingCountry + "\nCost: " + this.cost + "\nCurrency: " +this.currency
                + "\n--- --- ---";
    }

}
