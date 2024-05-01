package com.example.etraveli.dto;

import org.springframework.http.ResponseEntity;

import java.util.concurrent.CompletableFuture;

public class RequestDTO {
    private final String cardNumber;
    private final CompletableFuture<ResponseEntity<String>> future;

    public RequestDTO(String cardNumber, CompletableFuture<ResponseEntity<String>> future) {
        this.cardNumber = cardNumber;
        this.future = future;
    }

    public String getCardNumber() {
        return cardNumber;
    }

    public CompletableFuture<ResponseEntity<String>> getFuture() {
        return future;
    }
}
