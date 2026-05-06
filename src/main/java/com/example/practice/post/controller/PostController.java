package com.example.practice.post.controller;

import com.example.practice.auth.component.SessionManager;
import com.example.practice.auth.util.AuthTokenUtils;
import com.example.practice.global.exception.CustomException;
import com.example.practice.global.exception.error.ErrorCode;
import com.example.practice.global.response.ApiResponse;
import com.example.practice.post.controller.dto.PostRequest;
import com.example.practice.post.controller.dto.PostResponse;
import com.example.practice.post.service.PostService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/posts")
public class PostController {

    private final PostService postService;
    private final SessionManager sessionManager;

    // 게시글 생성
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public ApiResponse<PostResponse> createPost(@RequestBody PostRequest request, @RequestHeader(name = "Authorization") String bearerToken) {
        // 인증 로직
        if (bearerToken == null || !AuthTokenUtils.isValidToken(bearerToken)) {
            throw new CustomException(ErrorCode.INVALID_TOKEN);
        }
        String sessionKey = AuthTokenUtils.getSessionKey(bearerToken);
        Long memberId = sessionManager.getMemberId(sessionKey);
        if (memberId == null) {
            throw new CustomException(ErrorCode.UNAUTHORIZED);
        }

        return ApiResponse.success("게시글 생성 성공", postService.create(request, memberId));
    }

    // 모든 게시글 목록 반환
    @GetMapping
    public ApiResponse<List<PostResponse>> findAllPosts() {
        return ApiResponse.success("게시글 목록 조회 성공", postService.findAll());
    }

    // ID 입력, 특정 게시글 반환
    @GetMapping("/{id}")
    public ApiResponse<PostResponse> findPostById(@PathVariable Long id) {
        return ApiResponse.success("게시글 조회 성공", postService.getPostById(id));
    }

    // 게시글 수정
    @PutMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public ApiResponse<Void> updatePost(@PathVariable Long id, @RequestBody PostRequest request, @RequestHeader(name = "Authorization") String bearerToken) {
        // 토큰 검증
        if (bearerToken == null || !AuthTokenUtils.isValidToken(bearerToken)) {
            throw new CustomException(ErrorCode.INVALID_TOKEN);
        }
        String sessionKey = AuthTokenUtils.getSessionKey(bearerToken);
        Long memberId = sessionManager.getMemberId(sessionKey);
        if (memberId == null) {
            throw new CustomException(ErrorCode.UNAUTHORIZED);
        }
        postService.update(request, id, memberId);
        return ApiResponse.success("게시글 수정 성공");
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public ApiResponse<Void> deletePost(@PathVariable Long id, @RequestHeader(name = "Authorization") String bearerToken) {
        // 토큰 검증
        if (bearerToken == null || !AuthTokenUtils.isValidToken(bearerToken)) {
            throw new CustomException(ErrorCode.INVALID_TOKEN);
        }
        String sessionKey = AuthTokenUtils.getSessionKey(bearerToken);
        Long memberId = sessionManager.getMemberId(sessionKey);
        if (memberId == null) {
            throw new CustomException(ErrorCode.UNAUTHORIZED);
        }
        postService.delete(id, memberId);
        return ApiResponse.success("게시글 삭제 성공");
    }
}
