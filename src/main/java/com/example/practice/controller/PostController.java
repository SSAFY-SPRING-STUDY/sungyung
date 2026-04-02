package com.example.practice.controller;

import com.example.practice.controller.dto.PostRequest;
import com.example.practice.controller.dto.PostResponse;
import com.example.practice.service.PostService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/posts")
public class PostController {

    private final PostService postService;

    // 게시글 생성
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    PostResponse createPost(@RequestBody PostRequest request) {
        return postService.save(request);
    }

    // 모든 게시글 목록 반환
    @GetMapping
    List<PostResponse> findAllPosts() {
        return postService.findAll();
    }



    // ID 입력, 특정 게시글 반환
    @GetMapping("/{id}")
    PostResponse findPostById(@PathVariable Long id) {
        return postService.findById(id);
    }

    // 게시글 수정
    @PutMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    void updatePost(@PathVariable Long id, @RequestBody PostRequest request) {
        postService.update(id, request);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    void deletePost(@PathVariable Long id) {
        postService.delete(id);
    }
}
