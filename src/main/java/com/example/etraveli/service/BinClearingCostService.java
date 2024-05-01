package com.example.etraveli.service;

import com.example.etraveli.dto.CardInfoDTO;

public interface BinClearingCostService {

    CardInfoDTO findClearingCostByCountryCode(String binNumber);
}
