package com.example.etraveli.dto;

import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.time.Duration;
import java.util.ArrayDeque;
import java.util.Deque;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

@AllArgsConstructor
public class RequestQueue {
    private final Deque<RequestDTO> queue = new ArrayDeque<>();
    private final ExecutorService executorService = Executors.newSingleThreadExecutor();

    public RequestQueue(int i, Duration duration) {
    }

    public void addRequest(RequestDTO request) {
        synchronized (queue) {
            queue.addLast(request);
            //queue.notify(); // Notify any waiting thread that there's a new request
            executorService.execute(this::processRequests);
        }
    }

    private synchronized void processRequests() {
        int count = 0;
        while (count < 5 && !queue.isEmpty()) {
            RequestDTO request = queue.pollFirst();
            if (request != null) {
                request.getFuture().complete(ResponseEntity.status(HttpStatus.TOO_MANY_REQUESTS).body("Processing..."));
                count++;
            }
        }
    }

   /* public RequestDTO getNextRequest() throws InterruptedException {
        synchronized (queue) {
            while (queue.isEmpty()) {
                queue.wait(); // Wait until there's a request in the queue
            }
            return queue.pollFirst();
        }
    }

    public void completeRequest(RequestDTO request) {
        // No need for synchronization here since completeRequest is always called by a single thread
        // which is the one that removes the request from the queue
        request.getFuture().join(); // Ensure that the CompletableFuture is completed before removing the request
    }*/
}
