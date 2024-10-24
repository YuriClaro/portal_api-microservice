package com.humanit.portal_api.model;

public class SignInRequest {
    private String username;
    private String password;

    public SignInRequest(String username, String password) {
        this.username = username;
        this.password = password;
    }

    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
    }

    public SignInRequest setUsername(String username) {
        this.username = username;
        return this;
    }

    public SignInRequest setPassword(String password) {
        this.password = password;
        return this;
    }
}
