package com.example.etraveli.service;

import com.example.etraveli.domain.ClearingCost;
import com.example.etraveli.dto.ClearingCostDTO;

import java.util.List;

public interface ClearingCostService {
    List<ClearingCost> findAllClearingCosts();

    ClearingCost findClearCostById(Long id);

    ClearingCost addClearingCost(ClearingCost clearingCost);

    void deleteClearingCostById(ClearingCost clearingCost);

    ClearingCost updateClearingCost(ClearingCost clearingCost);

    ClearingCost findClearingCostByCountryCode(String countryCode);

    ClearingCost saveClearingCost(ClearingCostDTO clearingCostDTO);

    ClearingCost updateClearingCost(ClearingCostDTO clearingCostDTO, Long id);

}
