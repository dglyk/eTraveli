package com.example.etraveli.dto;

import com.example.etraveli.domain.ClearingCost;

public class ClearingCostTransformer {

    public static ClearingCost dtoToObject(ClearingCostDTO dto) {
       return ClearingCost.builder()
               .id(dto.getId())
               .issuingCountry(dto.getIssuingCountry())
               .cost(dto.getCost())
               .currency(dto.getCurrency())
               .build();
    }

    public static ClearingCostDTO objectToDTO(ClearingCost clearingCost){
        return  ClearingCostDTO.builder()
                .id(clearingCost.getId())
                .issuingCountry(clearingCost.getIssuingCountry())
                .cost(clearingCost.getCost())
                .currency(clearingCost.getCurrency())
                .build();
    }
}
