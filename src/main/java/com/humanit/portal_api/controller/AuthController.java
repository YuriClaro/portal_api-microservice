package com.humanit.portal_api.controller;

import com.humanit.portal_api.model.SignInRequest;
import com.humanit.portal_api.service.AuthService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("portal-api")
public class AuthController {

    private final AuthService authService;

    public AuthController(AuthService authService) {
        this.authService = authService;
    }

    @Operation(summary = "Sign in a user and generate a JWT token")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Token generated successfully"),
            @ApiResponse(responseCode = "401", description = "Unauthorized, invalid credentials")
    })
    @PostMapping("/sign-in")
    public String signIn(@RequestBody SignInRequest request) {
        return authService.signIn(request.getUsername(), request.getPassword());
    }
}
