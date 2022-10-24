package com.example.loginlivesession2.comment.repository;

import com.example.loginlivesession2.comment.entity.Comment;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CommentRepository extends JpaRepository<Comment, Long> {
//    List<Comment> findAllByOrderByCreatedAtDesc();
//    Optional<Comment> findById(Long id);

}