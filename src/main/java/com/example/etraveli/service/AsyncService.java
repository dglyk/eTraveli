package com.example.etraveli.service;

import com.example.etraveli.dto.ResponseDTO;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import java.util.concurrent.CompletableFuture;

@Service
@AllArgsConstructor
public class AsyncService {


    private ExternalClientService externalClientService;

    @Async
    public CompletableFuture<ResponseEntity<ResponseDTO>> getCardInfoAsync(String panNumber) throws InterruptedException {
        CompletableFuture<ResponseEntity<ResponseDTO>> responseDTO = externalClientService.asyncCalculateClearingCost(panNumber);
        return responseDTO;
    }
}
