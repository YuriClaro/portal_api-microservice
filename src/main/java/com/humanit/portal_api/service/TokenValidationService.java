package com.humanit.portal_api.service;

import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.server.ResponseStatusException;

@Service
public class TokenValidationService {

    private String endpoint = "http://localhost:8081/auth/validate-token";

    public void validateToken(String authorizationHeader) {
        RestTemplate restTemplate = new RestTemplate();
        HttpHeaders headers = new HttpHeaders();
        HttpEntity<String> entity = new HttpEntity<>(headers);

        String token = extractToken(authorizationHeader);
        headers.set("Authorization", "Bearer " + token);

        ResponseEntity<Boolean> response = restTemplate.exchange(endpoint, HttpMethod.POST, entity, Boolean.class);

        if (!Boolean.TRUE.equals(response.getBody())) {
            throw new ResponseStatusException(HttpStatus.UNAUTHORIZED, "Token is not valid");
        }
    }

    public String extractToken(String authorizationHeader) {
        if (authorizationHeader != null && authorizationHeader.startsWith("Bearer ")) {
            return authorizationHeader.substring(7);
        }
        return authorizationHeader;
    }

}


