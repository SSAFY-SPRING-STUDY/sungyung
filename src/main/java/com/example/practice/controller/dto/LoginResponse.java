package com.example.practice.controller.dto;

public record LoginResponse(String accessToken, String tokenType) {


    public static LoginResponse withUUID(String uuid) {
        return new LoginResponse(uuid, "Bearer");
    }
}
