package com.humanit.portal_api.client;

import com.humanit.portal_api.model.SignInRequest;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@FeignClient(name = "authService", url = "http://localhost:8081/auth")
public interface AuthClient {

    @PostMapping(value = "/sign-in", consumes = "application/json")
    String signIn(@RequestBody SignInRequest request);
}
