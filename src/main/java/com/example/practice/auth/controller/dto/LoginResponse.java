package com.example.practice.auth.controller.dto;

public record LoginResponse(String accessToken, String tokenType) {


    public static LoginResponse withUUID(String uuid) {
        return new LoginResponse(uuid, "Bearer");
    }
}
