package com.example.practice.service;

import com.example.practice.controller.dto.PostRequest;
import com.example.practice.entity.PostEntity;
import com.example.practice.global.exception.NotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import com.example.practice.repository.PostRepository;
import com.example.practice.controller.dto.PostResponse;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class PostService {

    private final PostRepository postRepository;

    // 저장
    public PostResponse save(PostRequest request) {
        PostEntity entity = new PostEntity(
                request.title(), request.content(), request.author()
        );
        PostEntity savePost = postRepository.save(entity);

        return PostResponse.from(savePost);
    }

    // 전체 목록 조회
    public List<PostResponse> findAll() {
        List<PostEntity> posts = postRepository.findAll();
        List<PostResponse> result = new ArrayList<>();
        for (PostEntity entity : posts) {
            PostResponse response = PostResponse.from(entity);
            result.add(response);
        }

        return result;
    }

    // ID 입력, PostResponse
    public PostResponse findById(Long id) {
        PostEntity entity = postRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("해당 게시글 없음"));
        return PostResponse.from(entity);
    }

    // update
    public void update(Long id, PostRequest request) {
        System.out.println("Service : request " + request);
        PostEntity entity = postRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("해당 id 없음"));
        entity.modify(request.title(), request.content());
    }

    public void delete(Long id) {
        PostEntity entity = postRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("삭제할 게시글 없음"));

        postRepository.deleteById(id);
    }
}
