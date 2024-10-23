package com.humanit.portal_api.service;

import com.humanit.portal_api.dto.AddressDTO;
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

import java.util.logging.Level;
import java.util.logging.Logger;

@Service
public class AddressService {
    private final TokenValidationService tokenValidationService;
    private final RestTemplate restTemplate;
    private static final Logger logger = Logger.getLogger(UserAccountService.class.getName());
    private String endpoint = "http://localhost:8082/api/addresses";

    public AddressService(TokenValidationService tokenValidationService, RestTemplate restTemplate) {
        this.tokenValidationService = tokenValidationService;
        this.restTemplate = restTemplate;
    }

    public AddressDTO createAddress(AddressDTO addressDTO, String authorizationHeader) {
        tokenValidationService.validateToken(authorizationHeader);
        HttpEntity<AddressDTO> requestEntity = new HttpEntity<>(addressDTO);
        ResponseEntity<AddressDTO> response = restTemplate.exchange(
                endpoint, HttpMethod.POST, requestEntity, AddressDTO.class
        );
        return response.getBody();
    }

    public Page<AddressDTO> getAllAddresses(Pageable pageable, String authorizationHeader) {
        tokenValidationService.validateToken(authorizationHeader);
        UriComponentsBuilder uriBuilder = UriComponentsBuilder.fromUriString(endpoint)
                .queryParam("page", pageable.getPageNumber())
                .queryParam("size", pageable.getPageSize());
        ResponseEntity<CustomPageImpl<AddressDTO>> responseEntity = restTemplate.exchange(
                uriBuilder.toUriString(),
                HttpMethod.GET,
                null,
                new ParameterizedTypeReference<CustomPageImpl<AddressDTO>>() {
                });
        return responseEntity.getBody();
    }

    public Page<AddressDTO> searchByUser(Long id, Pageable pageable, String authorizationHeader) {
        tokenValidationService.validateToken(authorizationHeader);
        UriComponentsBuilder uriBuilder = UriComponentsBuilder.fromUriString(endpoint + "/search/" + id)
                .queryParam("page", pageable.getPageNumber())
                .queryParam("size", pageable.getPageSize());
        ResponseEntity<CustomPageImpl<AddressDTO>> responseEntity = restTemplate.exchange(
                uriBuilder.toUriString(),
                HttpMethod.GET,
                null,
                new ParameterizedTypeReference<CustomPageImpl<AddressDTO>>() {
                });
        return responseEntity.getBody();
    }

    public AddressDTO getAddressById(Long id, String authorizationHeader) {
        tokenValidationService.validateToken(authorizationHeader);
        return restTemplate.getForObject(endpoint + "/" + id, AddressDTO.class);
    }

    public AddressDTO updateAddress(Long id, AddressDTO addressDTO, String authorizationHeader) {
        tokenValidationService.validateToken(authorizationHeader);
        HttpEntity<AddressDTO> requestEntity = new HttpEntity<>(addressDTO);
        ResponseEntity<AddressDTO> response = restTemplate.exchange(
                endpoint + "/" + id, HttpMethod.PUT, requestEntity, AddressDTO.class
        );
        return response.getBody();
    }

    public void deleteAddress(Long id, String authorizationHeader) {
        tokenValidationService.validateToken(authorizationHeader);
        try (CloseableHttpClient httpClient = HttpClients.createDefault()) {
            HttpDelete request = new HttpDelete(endpoint + "/" + id);
            try (CloseableHttpResponse response = httpClient.execute(request)) {
                logger.log(Level.INFO, "Address deleted successfully");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
