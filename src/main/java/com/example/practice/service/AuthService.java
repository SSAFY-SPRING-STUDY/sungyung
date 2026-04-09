package com.example.practice.service;

import com.example.practice.component.SessionManager;
import com.example.practice.controller.dto.LoginRequest;
import com.example.practice.controller.dto.LoginResponse;
import com.example.practice.entity.MemberEntity;
import com.example.practice.global.exception.NotFoundException;
import com.example.practice.repository.MemberRepository;

public class AuthService {

    private SessionManager sessionManager;
    private MemberRepository memberRepository;

    public LoginResponse login(LoginRequest request) {
        MemberEntity entity = memberRepository.findByLoginId(request.loginId())
                .orElseThrow(() -> new NotFoundException("해당 정보 없음"));
        if(entity.getPassword().equals(request.password())) {
            // 로그인 성공
            String uuid = sessionManager.createSession(entity.getId());
            return LoginResponse.from(uuid);
        }
        // 로그인 실패시 어떻게 보내야하지????
//        return new NotFoundException("패스워드 잘못됨");..?
        return null;
    }


    public void logout(String accessToken) {
        sessionManager.removeSession(accessToken);
    }

}
