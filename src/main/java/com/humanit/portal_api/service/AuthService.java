package com.humanit.portal_api.service;

import com.humanit.portal_api.client.AuthClient;
import com.humanit.portal_api.model.SignInRequest;
import org.springframework.stereotype.Service;

@Service
public class AuthService {

    private final AuthClient authClient;

    public AuthService(AuthClient authClient) {
        this.authClient = authClient;
    }

    public String signIn(String username, String password) {
        return authClient.signIn(new SignInRequest(username, password));
    }
}
