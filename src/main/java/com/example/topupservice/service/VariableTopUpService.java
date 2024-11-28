package com.example.topupservice.service;

import com.example.topupservice.dto.BalanceResponse;
import com.example.topupservice.dto.TopUpRequest;
import com.example.topupservice.dto.TopUpResponse;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class VariableTopUpService {

    private final RestTemplate restTemplate;

    @Value("${whish.api.production-base-url}")
    private String productionBaseUrl;

    @Value("${whish.api.sandbox-base-url}")
    private String sandboxBaseUrl;

    public VariableTopUpService(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    private HttpHeaders createHeaders() {
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        headers.set("sessionId", "your-session-id"); // Replace with actual sessionId
        headers.set("token", "your-token");         // Replace with actual token
        headers.set("language", "en");
        return headers;
    }

    public BalanceResponse getAccountBalance() {
        String url = productionBaseUrl + "/account/balance";
        HttpEntity<Void> entity = new HttpEntity<>(createHeaders());

        ResponseEntity<BalanceResponse> response = restTemplate.exchange(
                url, HttpMethod.GET, entity, BalanceResponse.class);

        return response.getBody();
    }

    public TopUpResponse performTopUp(TopUpRequest request) {
        String url = sandboxBaseUrl + "/transfer/airtime";
        HttpEntity<TopUpRequest> entity = new HttpEntity<>(request, createHeaders());

        ResponseEntity<TopUpResponse> response = restTemplate.exchange(
                url, HttpMethod.POST, entity, TopUpResponse.class);

        return response.getBody();
    }
}
