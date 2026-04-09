package com.example.practice.service;

import com.example.practice.component.SessionManager;
import com.example.practice.controller.dto.LoginRequest;
import com.example.practice.controller.dto.LoginResponse;
import com.example.practice.entity.MemberEntity;
import com.example.practice.global.exception.UnauthorizedException;
import com.example.practice.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthService {

    private final SessionManager sessionManager;
    private final MemberRepository memberRepository;

    public LoginResponse login(LoginRequest request) {
        MemberEntity entity = memberRepository.findByLoginId(request.loginId())
                .orElseThrow(() -> new UnauthorizedException("아이디 또는 비밀번호가 올바르지 않습니다."));
        if (!entity.getPassword().equals(request.password())) {
            // 로그인 실패
            throw new UnauthorizedException("아이디 또는 비밀번호가 올바르지 않습니다.");
        }
        // 로그인 성공
        String uuid = sessionManager.createSession(entity.getId());
        return LoginResponse.from(uuid);
    }

    public void logout(String accessToken) {
        sessionManager.removeSession(accessToken);
    }

}
