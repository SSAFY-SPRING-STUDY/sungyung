package com.example.practice.post.service;

import com.example.practice.global.exception.CustomException;
import com.example.practice.global.exception.error.ErrorCode;
import com.example.practice.member.entity.MemberEntity;
import com.example.practice.member.repository.MemberRepository;
import com.example.practice.post.controller.dto.PostRequest;
import com.example.practice.post.controller.dto.PostResponse;
import com.example.practice.post.entity.PostEntity;
import com.example.practice.post.repository.PostRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class PostService {

    private final PostRepository postRepository;
    private final MemberRepository memberRepository;

    // 저장
    public PostResponse create(PostRequest request, Long authorId) {

        MemberEntity author = memberRepository.findById(authorId)
                .orElseThrow(() -> new CustomException(ErrorCode.MEMBER_NOT_FOUND));

        PostEntity entity = PostEntity.create(request.title(), request.content(), author);
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
    public PostResponse getPostById(Long id) {
        PostEntity entity = postRepository.findById(id)
                .orElseThrow(() -> new CustomException(ErrorCode.POST_NOT_FOUND));
        return PostResponse.from(entity);
    }

    // update
    public PostResponse update(PostRequest request, Long id, Long authorId) {
//        System.out.println("Service : request " + request);
        PostEntity entity = postRepository.findById(id)
                .orElseThrow(() -> new CustomException(ErrorCode.POST_NOT_FOUND));

        ///////////////////////////////////////////////////////////////
        // author 권한 검증
        if (!entity.getAuthor().getId().equals(authorId)) {
            throw new CustomException(ErrorCode.INVALID_PERMISSION);
        }
//        // author 권한 검증 - 방법2
//        MemberEntity author = memberRepository.findById(authorId)
//                .orElseThrow(() -> new CustomException(ErrorCode.INVALID_PERMISSION));
        ///////////////////////////////////////////////////////////////

        entity.update(request.title(), request.content());
        return PostResponse.from(entity);
    }

    public void delete(Long id, Long authorId) {
        PostEntity entity = postRepository.findById(id)
                .orElseThrow(() -> new CustomException(ErrorCode.POST_NOT_FOUND));
        // author 권한검증
        if (!entity.getAuthor().getId().equals(authorId)) {
            throw new CustomException(ErrorCode.INVALID_PERMISSION);
        }
        // 삭제
        postRepository.deleteById(id);
    }
}
