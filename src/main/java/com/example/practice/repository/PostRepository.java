package com.example.practice.repository;

import com.example.practice.controller.PostController;
import com.example.practice.controller.dto.CreatePostRequest;
import com.example.practice.entity.PostEntity;
import org.springframework.stereotype.Repository;

import javax.swing.text.html.Option;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Repository
public class PostRepository {

    private final List<PostEntity> database = new ArrayList<>();

    public PostEntity save(PostEntity entity) {
        database.add(entity);
        return entity;
    }

    public List<PostEntity> findAll() {return database;}

    public Optional<PostEntity> findById(Long id) {
        for (PostEntity entity : database) {
            if(entity.getId().equals(id))
                return Optional.of(entity);
        }
        return Optional.empty();
    }

    public void deleteById(Long id) {
        database.removeIf(e -> e.getId().equals(id));
    }
}
