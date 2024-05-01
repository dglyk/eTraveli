package com.example.etraveli.Exception;

import com.example.etraveli.dto.ErrorResponseDTO;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.client.HttpClientErrorException;


@ControllerAdvice
public class RestResponseEntityExceptionHandler {
    @ExceptionHandler(value
            = HttpClientErrorException.TooManyRequests.class)
    @ResponseStatus(HttpStatus.TOO_MANY_REQUESTS)
    public @ResponseBody ErrorResponseDTO
    handleException(HttpClientErrorException.TooManyRequests ex) {
        String message = "External API can handle only 5 requests per hour. Your request has been saved and will be executed when there is availability.";
        return new ErrorResponseDTO(
                HttpStatus.TOO_MANY_REQUESTS.value(), message);
    }
}
