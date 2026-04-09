package com.example.practice.controller;

import com.example.practice.component.SessionManager;
import com.example.practice.controller.dto.MemberRequest;
import com.example.practice.controller.dto.MemberResponse;
import com.example.practice.global.exception.UnauthorizedException;
import com.example.practice.service.MemberService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/members")
public class MemberController {

    private final MemberService memberService;
    private final SessionManager sessionManager;

    // 회원가입
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public MemberResponse join(@RequestBody MemberRequest memberRequest) {
        return memberService.save(memberRequest);
    }

    @GetMapping("/me")
    @ResponseStatus(HttpStatus.OK)
    public MemberResponse me(@RequestHeader(name = "Authorization", required = false) String accessToken) {

        if (accessToken == null || !accessToken.startsWith("Bearer ")) {
            throw new UnauthorizedException("Invalid token format");
        }
        accessToken = accessToken.substring(7).trim();
        Long memberId = sessionManager.getMemberId(accessToken);
        if (memberId == null) {
            throw new UnauthorizedException("Invalid token");
        } // 401 -> Unauthorized
        return memberService.findById(memberId);
    }
}
