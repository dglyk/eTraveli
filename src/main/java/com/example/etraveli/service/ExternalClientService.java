package com.example.etraveli.service;

import com.example.etraveli.dto.ResponseDTO;
import org.springframework.http.ResponseEntity;

import java.util.concurrent.CompletableFuture;

public interface ExternalClientService {
    ResponseEntity<ResponseDTO> calculateClearingCost(String panNumber);

    CompletableFuture<ResponseEntity<ResponseDTO>> asyncCalculateClearingCost(String panNumber);

}
