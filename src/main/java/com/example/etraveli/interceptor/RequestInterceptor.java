package com.example.etraveli.interceptor;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.http.HttpStatus;
import org.springframework.lang.Nullable;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import java.util.Queue;
import java.util.concurrent.ConcurrentLinkedQueue;


@Component
public class RequestInterceptor implements HandlerInterceptor {


    private static final int REQUEST_LIMIT = 5;
    private final Queue<Long> requestTimestamps = new ConcurrentLinkedQueue<>();
    private final ConcurrentLinkedQueue<String> exceededRequestsQueue = new ConcurrentLinkedQueue<>();
    private long TIME_LIMIT_MS = 3600000; // 1 hour in milliseconds

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
/*
        long currentTime = System.currentTimeMillis();
        // Remove timestamps older than 1 hour
        while (!requestTimestamps.isEmpty() && currentTime - requestTimestamps.peek() > TIME_LIMIT_MS) {
            requestTimestamps.poll();
        }

        // Check if request limit exceeded
        if (requestTimestamps.size() >= REQUEST_LIMIT) {
            response.setStatus(HttpStatus.TOO_MANY_REQUESTS.value());
            response.getWriter().write("Rate limit exceeded. Please try again later.");
            return false;
        }

        // Add current request timestamp
        requestTimestamps.offer(currentTime);*/
        return true;
       // return true;
    }

    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler,
                           @Nullable ModelAndView modelAndView) throws Exception {


    }
}

