package com.example.framework.api;

import io.restassured.response.Response;

public class ApiResponseHandler {
    private final Response response;

    public ApiResponseHandler(Response response) {
        this.response = response;
    }

    public int getStatusCode() {
        return response.getStatusCode();
    }

    public <T> T getBodyAs(Class<T> clazz) {
        return response.as(clazz);
    }
}