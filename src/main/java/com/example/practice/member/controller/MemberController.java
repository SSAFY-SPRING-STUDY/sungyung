package com.example.practice.member.controller;

import com.example.practice.auth.component.SessionManager;
import com.example.practice.auth.util.AuthTokenUtils;
import com.example.practice.global.exception.CustomException;
import com.example.practice.global.exception.error.ErrorCode;
import com.example.practice.global.response.ApiResponse;
import com.example.practice.member.controller.dto.MemberRequest;
import com.example.practice.member.controller.dto.MemberResponse;
import com.example.practice.member.service.MemberService;
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
    public ApiResponse<MemberResponse> join(@RequestBody MemberRequest memberRequest) {
        return ApiResponse.success("회원가입 성공", memberService.save(memberRequest));
    }

    @GetMapping("/me")
    @ResponseStatus(HttpStatus.OK)
    public ApiResponse<MemberResponse> me(@RequestHeader(name = "Authorization") String accessToken) {

        if (accessToken == null || !AuthTokenUtils.isValidToken(accessToken)) {
            throw new CustomException(ErrorCode.INVALID_TOKEN);
//            throw new UnauthorizedException("Invalid token format");
        }
        String sessionKey = AuthTokenUtils.getSessionKey(accessToken);
        Long memberId = sessionManager.getMemberId(sessionKey);
        if (memberId == null) {
            throw new CustomException(ErrorCode.UNAUTHORIZED);
//            throw new UnauthorizedException("Invalid token");
        } // 401 -> Unauthorized
        return ApiResponse.success("회원 조회 성공", memberService.findById(memberId));
    }

}
