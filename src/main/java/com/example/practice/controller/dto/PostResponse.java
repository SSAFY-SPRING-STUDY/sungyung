package com.example.practice.controller.dto;

import com.example.practice.entity.PostEntity;

public record PostResponse (// @ResponseBody -> @RestController : class or record를 반환하면 json으로 변환해줌.
        Long id,
        String title,
        String content,
        String author
) {
    public static PostResponse from(PostEntity savePost) {
        return new PostResponse(savePost.getId(), savePost.getTitle(), savePost.getContent(), savePost.getAuthor());
    }
}
