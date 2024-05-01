package com.example.etraveli.controller;

import com.example.etraveli.dto.PanDTO;
import com.example.etraveli.dto.ResponseDTO;
import com.example.etraveli.service.ExternalClientService;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@AllArgsConstructor
public class ExternalSystemController {

    private ExternalClientService externalClientService;

    @PostMapping(value = "/payment-cards-cost")
    public ResponseEntity<ResponseDTO> paymentCardsCost(@RequestBody PanDTO pan) {
        return externalClientService.calculateClearingCost(pan.getCard_number());
    }
}
