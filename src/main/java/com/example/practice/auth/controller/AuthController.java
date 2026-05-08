package com.example.practice.auth.controller;

import com.example.practice.auth.controller.dto.LoginRequest;
import com.example.practice.auth.controller.dto.LoginResponse;
import com.example.practice.auth.service.AuthService;
import com.example.practice.auth.util.AuthTokenUtils;
import com.example.practice.global.exception.CustomException;
import com.example.practice.global.exception.error.ErrorCode;
import com.example.practice.global.response.ApiResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/auth")
public class AuthController {

    private final AuthService authorService;

    // 로그인
    @PostMapping("/login")
    @ResponseStatus(HttpStatus.OK)
    public ApiResponse<LoginResponse> login(@RequestBody LoginRequest loginRequest) {
        return ApiResponse.success("로그인 성공", authorService.login(loginRequest));
    }

    @PostMapping("/logout")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public ApiResponse<Void> logout(@RequestHeader(value = "Authorization") String accessToken) {
        if (accessToken == null || !AuthTokenUtils.isValidToken(accessToken)) {
            throw new CustomException(ErrorCode.INVALID_TOKEN);
        }

        String sessionKey = AuthTokenUtils.getSessionKey(accessToken);
        authorService.logout(sessionKey);
        return ApiResponse.success("로그아웃 성공", null);
    }
}
