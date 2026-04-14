package com.example.practice.controller;

import com.example.practice.component.SessionManager;
import com.example.practice.controller.dto.MemberRequest;
import com.example.practice.controller.dto.MemberResponse;
import com.example.practice.service.MemberService;
import com.example.practice.util.BearerTokenUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

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
    public MemberResponse me(@RequestHeader(name = "Authorization") String accessToken) {

        if (accessToken == null || !BearerTokenUtil.isValidToken(accessToken)) {
            throw new ResponseStatusException(HttpStatus.UNAUTHORIZED);
//            throw new UnauthorizedException("Invalid token format");
        }
        String sessionKey = BearerTokenUtil.getSessionKey(accessToken);
        Long memberId = sessionManager.getMemberId(sessionKey);
        if (memberId == null) {
            throw new ResponseStatusException(HttpStatus.UNAUTHORIZED);
//            throw new UnauthorizedException("Invalid token");
        } // 401 -> Unauthorized
        return memberService.findById(memberId);
    }
}
