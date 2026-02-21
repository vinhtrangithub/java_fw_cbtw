package com.example.framework.api.services;

import com.example.framework.api.ApiRequestBuilder;
import com.example.framework.api.ApiResponseHandler;
import com.example.framework.api.auth.AuthManager;
import com.example.framework.api.dto.LoginRequest;
import com.example.framework.api.dto.LoginResponse;
import com.example.framework.config.ConfigReader;
import io.restassured.http.Method;

public class AuthService {

    public ApiResponseHandler login(LoginRequest request) {

        ApiResponseHandler response = new ApiResponseHandler(
                new ApiRequestBuilder()
                        .addHeader("Content-Type", "application/json")
                        .addHeader("x-api-key", ConfigReader.getApiKey())
                        .addBody(request)
                        .sendRequest(Method.POST, "/api/login")
        );

        if (response.getStatusCode() == 200) {
            LoginResponse loginResponse = response.getBodyAs(LoginResponse.class);
            AuthManager.setToken(loginResponse.getToken());
        }

        return response;
    }
}
