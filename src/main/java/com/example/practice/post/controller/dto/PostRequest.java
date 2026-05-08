package com.example.practice.post.controller.dto;

import com.example.practice.member.entity.MemberEntity;
import com.example.practice.post.entity.PostEntity;

public record PostRequest(String title, String content) {

    public PostEntity toEntity(MemberEntity author) {
        return  PostEntity.create(title, content, author);
    }
}
