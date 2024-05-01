package com.example.etraveli.service;

import com.example.etraveli.dto.BinListResponseDTO;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;


@Service
public class BinListApiClientImpl implements BinListApiClient {
    private static final String BASE_URL = "https://lookup.binlist.net/";
    String acceptVersion = "3";
    RestTemplate restTemplate = new RestTemplate();
    @Override
    public String makeCall(String bin) {
        HttpHeaders headers = new HttpHeaders();
        headers.set("Accept-Version", acceptVersion);
        headers.setContentType(MediaType.APPLICATION_JSON);
        // Make API call
        ResponseEntity<BinListResponseDTO> response = restTemplate.exchange(BASE_URL + bin, HttpMethod.GET, null, BinListResponseDTO.class);
        String resultCountryCode = null;
        if (!response.getStatusCode().equals(HttpStatus.NOT_FOUND)) {
            resultCountryCode = response.getBody().getCountry().getAlpha2();
        }
        return resultCountryCode;
    }
}
