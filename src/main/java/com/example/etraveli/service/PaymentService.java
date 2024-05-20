package com.example.etraveli.service;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.util.Queue;
import java.util.concurrent.ConcurrentLinkedQueue;

@Component
public class PaymentService {
    private Queue<HttpServletRequest> requestQueue = new ConcurrentLinkedQueue<>();
    private int maxRequestsPerHour = 5;
    private int requestsSent = 0;
    private long lastHourTimestamp = System.currentTimeMillis();
    // Endpoint to receive payment card requests
    public void processPaymentCardCostRequest(HttpServletRequest request) {
        requestQueue.add(request);
    }

    @Scheduled(fixedDelay = 1000) // Check every second
    public void processPaymentRequests() {
        long currentTimestamp = System.currentTimeMillis();
        if (currentTimestamp - lastHourTimestamp >= 3600000) { // If one hour has passed
            lastHourTimestamp = currentTimestamp;
            requestsSent = 0; // Reset requests sent count
        }

        if (requestsSent < maxRequestsPerHour) {
            HttpServletRequest request = requestQueue.poll();
            if (request != null) {
                //sendPaymentRequestToExternalAPI(request);
                requestsSent++;
            }
        }
    }

}
