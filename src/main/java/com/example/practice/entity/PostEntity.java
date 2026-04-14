package com.example.practice.entity;

import lombok.Getter;

@Getter
public class PostEntity {
    private static long AUTO_INCREMENT_ID = 1L;

    private Long id;
    private String title;
    private String content;
    private String author;

    public PostEntity(String title, String content, String author) {
        this.id = AUTO_INCREMENT_ID++;
        this.title = title;
        this.content = content;
        this.author = author;
    }

//    public boolean isMatch(Long id) {
//        return this.id != null && this.id.equals(id);
//    }

    public PostEntity modify(String title, String content) {
        this.title = title;
        this.content = content;
        return this;
    }
}