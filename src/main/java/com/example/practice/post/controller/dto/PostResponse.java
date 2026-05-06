package com.example.practice.post.controller.dto;

import com.example.practice.member.controller.dto.MemberResponse;
import com.example.practice.post.entity.PostEntity;

public record PostResponse (// @ResponseBody -> @RestController : class or record를 반환하면 json으로 변환해줌.
        Long id,
        String title,
        String content,
        MemberResponse memberResponse
) {
    public static PostResponse from(PostEntity savePost) {
        MemberResponse memberResponse = MemberResponse.from(savePost.getAuthor());

        return new PostResponse(savePost.getId(), savePost.getTitle(), savePost.getContent(), memberResponse);
    }
}
