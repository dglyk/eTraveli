package com.example.etraveli.controller;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.http.*;
import org.springframework.test.context.junit4.SpringRunner;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;


@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class ExternalSystemControllerTests {

    @LocalServerPort
    private int port;

    TestRestTemplate restTemplate = new TestRestTemplate();
    HttpHeaders headers = new HttpHeaders();


    @Test
    public void testPaymentCardsCost() throws Exception {
        headers.setContentType(MediaType.APPLICATION_JSON);
        HttpEntity<String> entity = new HttpEntity<String>("{\n" +
                "\"card_number\": \"45717360\"\n" +
                "}", headers);

        ResponseEntity<String> response = restTemplate.exchange(
                createURLWithPort("/api/payment-cards-cost"), HttpMethod.POST, entity, String.class);

        assertFalse(response.getBody().isEmpty());
        assertTrue(response.getStatusCode().is2xxSuccessful());
    }

    private String createURLWithPort(String uri) {
        return "http://localhost:" + port + uri;
    }

}
