package com.devteria.identity_service.controller;


import com.devteria.identity_service.dto.request.ApiResponse;
import com.devteria.identity_service.dto.request.AuthenticationRequest;
import com.devteria.identity_service.dto.response.AuthenticationResponse;
import com.devteria.identity_service.service.AuthenticationService;
import com.devteria.identity_service.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class AuthenticationController {
    @Autowired
    AuthenticationService authenticationService;

    @PostMapping("/v1/auth")
    boolean authentication(@RequestBody AuthenticationRequest request) {
        return authenticationService.authenticate(request);
    }

    @PostMapping("/v1/login")
    ApiResponse login(@RequestBody AuthenticationRequest request) {
        boolean is_authenticated = authenticationService.authenticate(request);
        AuthenticationResponse authenticationResponse =
                AuthenticationResponse.builder().authenticated(is_authenticated).build();

        int _code = is_authenticated ? 2001:2002;
        ApiResponse<AuthenticationResponse> apiResponse =
                ApiResponse.<AuthenticationResponse>builder()
                        .code(_code)
                        .result(authenticationResponse).build();

        return apiResponse;
    }

    @PostMapping("/v2/login")
    ApiResponse login2(@RequestBody AuthenticationRequest request) {
        AuthenticationResponse response = authenticationService.authenticateJWT(request);
        return ApiResponse.<AuthenticationResponse>builder().result(response).build();
    }


}
