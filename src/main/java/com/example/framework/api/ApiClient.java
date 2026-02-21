package com.example.framework.api;

import com.example.framework.config.ConfigReader;
import io.restassured.RestAssured;
import io.restassured.specification.RequestSpecification;

public class ApiClient {
    private static final ThreadLocal<RequestSpecification> requestSpec = new ThreadLocal<>();

    public static void init() {
        String baseUrl = ConfigReader.getApiUrl();
        requestSpec.set(RestAssured.given().baseUri(baseUrl));
    }

    public static RequestSpecification getRequestSpec() {
        if (requestSpec.get() == null) {
            init();
        }
        return requestSpec.get();
    }

    public static void reset() {
        requestSpec.remove();
    }
}