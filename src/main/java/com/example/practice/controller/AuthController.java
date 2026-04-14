package com.example.practice.controller;

import com.example.practice.controller.dto.LoginRequest;
import com.example.practice.controller.dto.LoginResponse;
import com.example.practice.global.exception.UnauthorizedException;
import com.example.practice.service.AuthService;
import com.example.practice.util.BearerTokenUtil;
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
    public LoginResponse login(@RequestBody LoginRequest loginRequest) {
        return authorService.login(loginRequest);
        // ResponseStatus -> 200 = HttpStatus.OK / 401 -> Unauthorized
    }

    @PostMapping("/logout")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void logout(@RequestHeader(value = "Authorization") String accessToken) {
        if (accessToken == null || !BearerTokenUtil.isValidToken(accessToken)) {
            throw new UnauthorizedException("Invalid token format");
        }

        String sessionKey = BearerTokenUtil.getSessionKey(accessToken);
        authorService.logout(sessionKey);
    }
}
