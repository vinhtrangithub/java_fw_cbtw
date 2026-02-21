package com.example.framework.api;

import io.restassured.http.Method;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

public class ApiRequestBuilder {
    private final RequestSpecification requestSpec;

    public ApiRequestBuilder() {
        this.requestSpec = ApiClient.getRequestSpec();
    }

    public ApiRequestBuilder addHeader(String key, String value) {
        requestSpec.header(key, value);
        return this;
    }

    public ApiRequestBuilder addQueryParam(String key, String value) {
        requestSpec.queryParam(key, value);
        return this;
    }

    public ApiRequestBuilder addBody(Object body) {
        requestSpec.body(body);
        return this;
    }

    public Response sendRequest(Method method, String endpoint) {
        return requestSpec.request(method, endpoint);
    }
}