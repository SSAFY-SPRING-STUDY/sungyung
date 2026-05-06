package com.example.practice.post.entity;

import com.example.practice.member.entity.MemberEntity;
import lombok.Getter;

@Getter
public class PostEntity {
    private static long AUTO_INCREMENT_ID = 1L;

    private Long id;
    private String title;
    private String content;
    private MemberEntity author;

    private PostEntity(Long id, String title, String content, MemberEntity author) {
        this.id = id;
        this.title = title;
        this.content = content;
        this.author = author;
    }

    public static PostEntity create(String title, String content, MemberEntity author) {
        Long id = AUTO_INCREMENT_ID++;
        return new PostEntity(id, title, content, author);
    }

    public PostEntity update(String title, String content) {
        this.title = title;
        this.content = content;
        return this;
    }

}