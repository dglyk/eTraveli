package com.example.etraveli.service;

import com.example.etraveli.dto.ResponseDTO;
import org.springframework.http.ResponseEntity;

public interface ExternalClientService {
    ResponseEntity<ResponseDTO> calculateClearingCost(String panNumber);
}
