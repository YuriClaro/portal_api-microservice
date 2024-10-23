package com.humanit.portal_api.controller;

import com.humanit.portal_api.dto.AddressDTO;
import com.humanit.portal_api.service.AddressService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("portal-api/addresses")
public class AddressController {
    private final AddressService addressService;

    public AddressController(AddressService addressService) {
        this.addressService = addressService;
    }

    @Operation(summary = "Create a new address", description = "Endpoint to create a new address")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Address created successfully"),
            @ApiResponse(responseCode = "400", description = "Invalid request")
    })
    @PostMapping
    public ResponseEntity<AddressDTO> createAddress(
            @RequestBody AddressDTO addressDTO,
            @RequestHeader("Authorization") String authorizationHeader) {
        return new ResponseEntity<>(addressService
                .createAddress(addressDTO, authorizationHeader), HttpStatus.OK);
    }

    @Operation(summary = "Retrieves a paginated list of addresses", description = "Endpoint to retrieve all addresses with pagination")
    @ApiResponse(responseCode = "200", description = "Addresses list retrieved successfully")
    @GetMapping
    public ResponseEntity<Page<AddressDTO>> getAllAddresses(Pageable pageable,
                                                            @RequestHeader("Authorization") String authorizationHeader) {
        return new ResponseEntity<>(addressService.getAllAddresses(pageable, authorizationHeader), HttpStatus.OK);
    }

    @Operation(summary = "Retrieves an address by ID", description = "Endpoint to fetch a specific address by ID")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Address retrieved successfully"),
            @ApiResponse(responseCode = "404", description = "Address not found")
    })
    @GetMapping("/{id}")
    public ResponseEntity<AddressDTO> getUserById(@PathVariable Long id,
                                                  @RequestHeader("Authorization") String authorizationHeader) {
        return new ResponseEntity<>(addressService.getAddressById(id, authorizationHeader), HttpStatus.OK);
    }

    @Operation(summary = "Search addresses by user id", description = "Endpoint to search addresses provided by user id")
    @ApiResponse(responseCode = "200", description = "Addresses found successfully")
    @GetMapping("/search/{id}")
    public ResponseEntity<Page<AddressDTO>> searchByUser(@PathVariable Long id, Pageable pageable,
                                                         @RequestHeader("Authorization") String authorizationHeader) {
        return new ResponseEntity<>(addressService.searchByUser(id, pageable, authorizationHeader), HttpStatus.OK);
    }

    @Operation(summary = "Updates an existing address", description = "Endpoint to update a specific address by ID")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Address updated successfully"),
            @ApiResponse(responseCode = "404", description = "Address not found"),
            @ApiResponse(responseCode = "400", description = "Invalid request")
    })
    @PutMapping("/{id}")
    public ResponseEntity<AddressDTO> updateAddress(@PathVariable Long id,
                                                    @RequestBody AddressDTO addressDTO,
                                                    @RequestHeader("Authorization") String authorizationHeader) {
        return new ResponseEntity<>(addressService
                .updateAddress(id, addressDTO, authorizationHeader), HttpStatus.OK);
    }

    @Operation(summary = "Deletes an existing address", description = "Endpoint to delete a specific address by ID")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "204", description = "Address deleted successfully"),
            @ApiResponse(responseCode = "404", description = "Address not found")
    })
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteAddress(@PathVariable Long id,
                                              @RequestHeader("Authorization") String authorizationHeader) {
        addressService.deleteAddress(id, authorizationHeader);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
