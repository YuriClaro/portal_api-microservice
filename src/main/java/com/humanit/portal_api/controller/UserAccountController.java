package com.humanit.portal_api.controller;

import com.humanit.portal_api.dto.UserAccountDTO;
import com.humanit.portal_api.service.UserAccountService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("portal-api/users")
public class UserAccountController {
    private final UserAccountService userAccountService;

    public UserAccountController(UserAccountService userAccountService) {
        this.userAccountService = userAccountService;
    }

    @Operation(summary = "Create a new user", description = "Endpoint to create a new user")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "UserAccount created successfully"),
            @ApiResponse(responseCode = "400", description = "Invalid request")
    })
    @PostMapping
    public ResponseEntity<UserAccountDTO> createUserAccount(
            @RequestBody UserAccountDTO userAccountDTO,
            @RequestHeader("Authorization") String authorizationHeader) {
        return new ResponseEntity<>(userAccountService
                .createUserAccount(userAccountDTO, authorizationHeader), HttpStatus.OK);
    }

    @Operation(summary = "A paginated list of users", description = "Endpoint to retrieve all users with pagination")
    @ApiResponse(responseCode = "200", description = "UserAccount list retrieved successfully")
    @GetMapping
    public ResponseEntity<Page<UserAccountDTO>> getAllUsers(Pageable pageable,
                                                            @RequestHeader("Authorization") String authorizationHeader) {
        return new ResponseEntity<>(userAccountService.getAllUsers(pageable, authorizationHeader), HttpStatus.OK);
    }

    @Operation(summary = "Search users by name", description = "Endpoint to search for users by the provided name")
    @ApiResponse(responseCode = "200", description = "Users found successfully")
    @GetMapping("/search/{name}")
    public ResponseEntity<Page<UserAccountDTO>> searchByName(@PathVariable String name, Pageable pageable,
                                                             @RequestHeader("Authorization") String authorizationHeader) {
        return new ResponseEntity<>(userAccountService.searchByName(name, pageable, authorizationHeader), HttpStatus.OK);
    }

    @Operation(summary = "Retrieves a user by ID", description = "Endpoint to fetch a specific user by ID")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "UserAccount retrieved successfully"),
            @ApiResponse(responseCode = "404", description = "UserAccount not found")
    })
    @GetMapping("/{id}")
    public ResponseEntity<UserAccountDTO> getUserById(@PathVariable Long id,
                                                      @RequestHeader("Authorization") String authorizationHeader) {
        return new ResponseEntity<>(userAccountService.getUserById(id, authorizationHeader), HttpStatus.OK);
    }

    @Operation(summary = "Updates an existing user", description = "Endpoint to update a specific user by ID")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "UserAccount updated successfully"),
            @ApiResponse(responseCode = "404", description = "UserAccount not found"),
            @ApiResponse(responseCode = "400", description = "Invalid request")
    })
    @PutMapping("/{id}")
    public ResponseEntity<UserAccountDTO> updateUserAccount(@PathVariable Long id,
                                                            @RequestBody UserAccountDTO userAccountDTO,
                                                            @RequestHeader("Authorization") String authorizationHeader) {
        return new ResponseEntity<>(userAccountService
                .updateUserAccount(id, userAccountDTO, authorizationHeader), HttpStatus.OK);
    }

    @Operation(summary = "Deletes an existing user", description = "Endpoint to delete a specific user by ID")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "204", description = "UserAccount deleted successfully"),
            @ApiResponse(responseCode = "404", description = "UserAccount not found")
    })
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteUser(@PathVariable Long id,
                                           @RequestHeader("Authorization") String authorizationHeader) {
        userAccountService.deleteUser(id, authorizationHeader);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

}

