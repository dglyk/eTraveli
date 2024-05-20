package com.example.etraveli.controller;

import com.example.etraveli.dto.PanDTO;
import com.example.etraveli.dto.ResponseDTO;
import com.example.etraveli.service.AsyncService;
import com.example.etraveli.service.ExternalClientService;
import jakarta.servlet.http.HttpServletRequest;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;

@RestController
@AllArgsConstructor
public class ExternalSystemController {

    private ExternalClientService externalClientService;
    private AsyncService asyncService;

    @PostMapping(value = "/payment-cards-cost")
    public ResponseEntity<ResponseDTO> paymentCardsCost(HttpServletRequest request, @RequestBody PanDTO pan) {
        return externalClientService.calculateClearingCost(pan.getCard_number());
    }

    @PostMapping(value="/async-controller")
    public ResponseEntity<ResponseDTO> paymentCards(HttpServletRequest request, @RequestBody PanDTO pan) throws ExecutionException, InterruptedException {
        CompletableFuture<ResponseEntity<ResponseDTO>> dtos;
        try {
            dtos = asyncService.getCardInfoAsync(pan.getCard_number());
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        return dtos.get();
    }
}
