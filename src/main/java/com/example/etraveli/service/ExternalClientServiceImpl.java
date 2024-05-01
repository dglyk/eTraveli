package com.example.etraveli.service;

import com.example.etraveli.domain.ClearingCost;
import com.example.etraveli.dto.BinListResponseDTO;
import com.example.etraveli.dto.CardInfoDTO;
import com.example.etraveli.dto.ClearingCostDTO;
import com.example.etraveli.dto.ResponseDTO;
import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.apache.coyote.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.CacheManager;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@AllArgsConstructor
public class ExternalClientServiceImpl implements ExternalClientService{

    private BinListApiClient binListApiClient;
    private ClearingCostService clearingCostService;
    private BinClearingCostService binClearingCostService;

    @Autowired
    CacheManager cacheManager;

    public ResponseEntity<ResponseDTO> calculateClearingCost(String panNumber) {
        CardInfoDTO cardInfoDTO = binClearingCostService.findClearingCostByCountryCode(getBinNumber(panNumber));
        if(cardInfoDTO.getCountryCode() != null){
            return ResponseEntity.status(HttpStatus.OK)
                    .body(clearingCostToResponseDTO(clearingCostService.findClearingCostByCountryCode(cardInfoDTO.getCountryCode())));
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new ResponseDTO());
        }
    }

    private ResponseDTO clearingCostToResponseDTO(ClearingCost clearingCost){
        return ResponseDTO.builder().cost(clearingCost.getCost()).country(clearingCost.getIssuingCountry()).build();
    }

    private String getBinNumber(String panNumber){
        return panNumber.substring(0,6);
    }

}
