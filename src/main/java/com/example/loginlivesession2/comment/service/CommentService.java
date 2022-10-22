package com.example.loginlivesession2.comment.service;

import com.example.loginlivesession2.account.entity.Account;
import com.example.loginlivesession2.comment.dto.CommentRequestDto;
import com.example.loginlivesession2.comment.dto.CommentResponseDto;
import com.example.loginlivesession2.comment.entity.Comment;
import com.example.loginlivesession2.comment.repository.CommentRepository;
import com.example.loginlivesession2.post.dto.ResponseDto;
import com.example.loginlivesession2.post.entity.Post;
import com.example.loginlivesession2.post.repository.PostRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CommentService {
    private final CommentRepository commentRepository;
    private final PostRepository postRepository;
    public CommentResponseDto save(CommentRequestDto commentRequestDto, Account account, Long postId) {

        Post post = postRepository.findById(postId).orElseThrow(
                () -> new IllegalArgumentException("게시글이 없습니다")
        );

        Comment comment = new Comment(commentRequestDto, account, post);
        commentRepository.save(comment);
        CommentResponseDto commentResponseDto = new CommentResponseDto(comment, account);
        return commentResponseDto;
    }


}
