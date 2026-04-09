package com.example.practice.controller.dto;

public record LoginResponse(String accessToken, String tokenType) {


    public static LoginResponse from(String uuid) {
        return new LoginResponse(uuid, "Bearer");
    }
}
