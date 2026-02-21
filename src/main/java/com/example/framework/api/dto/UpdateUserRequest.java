package com.example.framework.api.dto;

public class UpdateUserRequest {

    private String name;
    private String job;

    public UpdateUserRequest(String name, String job) {
        this.name = name;
        this.job = job;
    }

    public String getName() { return name; }
    public String getJob() { return job; }
}
