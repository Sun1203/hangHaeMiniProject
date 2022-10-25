package com.example.loginlivesession2.post.repository;

import com.example.loginlivesession2.post.entity.Post;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface PostRepository extends JpaRepository <Post, Long> {
    List<Post> findAll();
    List<Post> findAllByCategory(String category);
}
