package com.humanit.portal_api.service;

import com.humanit.portal_api.dto.UserAccountDTO;
import com.humanit.portal_api.util.CustomPageImpl;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpDelete;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;


@Service
public class UserAccountService {
    private final TokenValidationService tokenValidationService;
    private final RestTemplate restTemplate;
    private static final Logger logger = Logger.getLogger(UserAccountService.class.getName());
    private String endpoint = "http://localhost:8082/api/users";

    public UserAccountService(TokenValidationService tokenValidationService, RestTemplate restTemplate) {
        this.tokenValidationService = tokenValidationService;
        this.restTemplate = restTemplate;
    }

    public UserAccountDTO createUserAccount(UserAccountDTO userAccountDTO, String authorizationHeader) {
        tokenValidationService.validateToken(authorizationHeader);
        HttpEntity<UserAccountDTO> requestEntity = new HttpEntity<>(userAccountDTO);
        ResponseEntity<UserAccountDTO> response = restTemplate.exchange(
                endpoint, HttpMethod.POST, requestEntity, UserAccountDTO.class
        );
        return response.getBody();
    }

    public Page<UserAccountDTO> getAllUsers(Pageable pageable, String authorizationHeader) {
        tokenValidationService.validateToken(authorizationHeader);
        UriComponentsBuilder uriBuilder = UriComponentsBuilder.fromUriString(endpoint)
                .queryParam("page", pageable.getPageNumber())
                .queryParam("size", pageable.getPageSize());
        ResponseEntity<CustomPageImpl<UserAccountDTO>> responseEntity = restTemplate.exchange(
                uriBuilder.toUriString(),
                HttpMethod.GET,
                null,
                new ParameterizedTypeReference<CustomPageImpl<UserAccountDTO>>() {
                });
        return responseEntity.getBody();
    }

    public UserAccountDTO getUserById(Long id, String authorizationHeader) {
        tokenValidationService.validateToken(authorizationHeader);
        return restTemplate.getForObject(endpoint + "/" + id, UserAccountDTO.class);
    }

    public Page<UserAccountDTO> searchByName(String name, Pageable pageable, String authorizationHeader) {
        tokenValidationService.validateToken(authorizationHeader);
        UriComponentsBuilder uriBuilder = UriComponentsBuilder.fromUriString(endpoint + "/search/" + name)
                .queryParam("page", pageable.getPageNumber())
                .queryParam("size", pageable.getPageSize());
        ResponseEntity<CustomPageImpl<UserAccountDTO>> responseEntity = restTemplate.exchange(
                uriBuilder.toUriString(),
                HttpMethod.GET,
                null,
                new ParameterizedTypeReference<CustomPageImpl<UserAccountDTO>>() {
                });
        return responseEntity.getBody();
    }

    public UserAccountDTO updateUserAccount(Long id, UserAccountDTO userAccountDTO, String authorizationHeader) {
        tokenValidationService.validateToken(authorizationHeader);
        HttpEntity<UserAccountDTO> requestEntity = new HttpEntity<>(userAccountDTO);
        ResponseEntity<UserAccountDTO> response = restTemplate.exchange(
                endpoint + "/" + id, HttpMethod.PUT, requestEntity, UserAccountDTO.class
        );
        return response.getBody();
    }

    public void deleteUser(Long id, String authorizationHeader) {
        tokenValidationService.validateToken(authorizationHeader);
        try (CloseableHttpClient httpClient = HttpClients.createDefault()) {
            HttpDelete request = new HttpDelete(endpoint + "/" + id);
            try (CloseableHttpResponse response = httpClient.execute(request)) {
                logger.log(Level.INFO, "User deleted successfully");
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

}


