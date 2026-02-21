package com.example.framework.api.services;

import com.example.framework.api.ApiRequestBuilder;
import com.example.framework.api.ApiResponseHandler;
import com.example.framework.api.auth.AuthManager;
import com.example.framework.api.dto.UpdateUserRequest;
import com.example.framework.config.ConfigReader;
import io.restassured.http.Method;

public class UserService {

    private ApiRequestBuilder authorizedRequest() {
        return new ApiRequestBuilder()
                .addHeader("x-api-key", ConfigReader.getApiKey())
                .addHeader("Authorization", "Bearer " + AuthManager.getToken())
                .addHeader("Content-Type", "application/json");
    }

    public ApiResponseHandler getUser(int id) {
        return new ApiResponseHandler(
                authorizedRequest()
                        .sendRequest(Method.GET, "/api/users/" + id)
        );
    }

    public ApiResponseHandler updateUser(int id, UpdateUserRequest request) {
        return new ApiResponseHandler(
                authorizedRequest()
                        .addBody(request)
                        .sendRequest(Method.PUT, "/api/users/" + id)
        );
    }
}
