package com.example.practice.auth.service;

import com.example.practice.auth.component.SessionManager;
import com.example.practice.auth.controller.dto.LoginRequest;
import com.example.practice.auth.controller.dto.LoginResponse;
import com.example.practice.global.exception.CustomException;
import com.example.practice.global.exception.error.ErrorCode;
import com.example.practice.member.entity.MemberEntity;
import com.example.practice.member.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthService {

    private final SessionManager sessionManager;
    private final MemberRepository memberRepository;

    public LoginResponse login(LoginRequest request) {
        MemberEntity entity = memberRepository.findByLoginId(request.loginId())
                .orElseThrow(() -> new CustomException(ErrorCode.INVALID_USERNAME));
        if (!entity.getPassword().equals(request.password())) {
            // 로그인 실패 - 비번 틀림
            throw new CustomException(ErrorCode.INVALID_PASSWORD);
        }
        // 로그인 성공
        String uuid = sessionManager.createSession(entity.getId());
        return LoginResponse.withUUID(uuid);
    }

    public void logout(String accessToken) {
        sessionManager.removeSession(accessToken);
    }

}
