package com.example.practice.controller.dto;

public class CreatePostRequest {
    public String title;
    public String content;
    public String author;

    @Override
    public String toString() {
        return "CreatePostRequest{" +
                "title='" + title + '\'' +
                ", content='" + content + '\'' +
                ", Author='" + author + '\'' +
                '}';
    }
}
