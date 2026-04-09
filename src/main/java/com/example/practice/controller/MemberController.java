package com.example.practice.controller;

import com.example.practice.component.SessionManager;
import com.example.practice.controller.dto.LoginRequest;
import com.example.practice.controller.dto.LoginResponse;
import com.example.practice.controller.dto.MemberRequest;
import com.example.practice.controller.dto.MemberResponse;
import com.example.practice.service.AuthService;
import com.example.practice.service.MemberService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api")
public class MemberController {

    private final MemberService memberService;
    private final AuthService authorService;
    private final SessionManager sessionManager;

    // 회원가입
    @PostMapping("/members")
    @ResponseStatus(HttpStatus.CREATED)
    public MemberResponse join(@RequestBody MemberRequest memberRequest) {
        return memberService.save(memberRequest);
    }

    // 로그인
    @PostMapping("/auth/login")
    @ResponseStatus(HttpStatus.OK)
    public LoginResponse login(LoginRequest loginRequest) {
        return authorService.login(loginRequest);
        // ResponseStatus -> 200 = HttpStatus.OK / 401 -> Unauthorized

    }

    @PostMapping("/auth/logout")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void logout(@RequestHeader("Authorization") String accessToken) {
        authorService.logout(accessToken);
    }

    @GetMapping("/members/me")
    @ResponseStatus(HttpStatus.OK)
    public MemberResponse me(@RequestHeader("Authorization") String accessToken) {
        Long memberId = sessionManager.getMemberId(accessToken);

        // memberId == null 이면????->회원 정보가 없다는건뎅?
        // memberService.findBy에서 해당 아이디가 없으면 NotFoundException으로 넘김..!?
        // 401 -> Unauthorized
        return memberService.findById(memberId);
    }
}
