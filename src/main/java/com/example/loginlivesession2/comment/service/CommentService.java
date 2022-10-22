package com.example.loginlivesession2.comment.service;

import com.example.loginlivesession2.account.entity.Account;
import com.example.loginlivesession2.comment.dto.CommentRequestDto;
import com.example.loginlivesession2.comment.dto.CommentResponseDto;
import com.example.loginlivesession2.comment.dto.ResponseDto;
import com.example.loginlivesession2.comment.entity.Comment;
import com.example.loginlivesession2.comment.repository.CommentRepository;
import com.example.loginlivesession2.post.entity.Post;
import com.example.loginlivesession2.post.repository.PostRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@Service
@RequiredArgsConstructor
public class CommentService {
    private final CommentRepository commentRepository;
    private final PostRepository postRepository;

    // 댓글 등록
    @Transactional
    public CommentResponseDto save(CommentRequestDto commentRequestDto, Account account, Long postId) {
        Post post = postRepository.findById(postId).orElseThrow(
                () -> new IllegalArgumentException("게시글이 없습니다.")
        );

        Comment comment = new Comment(commentRequestDto, account, post);
        commentRepository.save(comment);
        CommentResponseDto commentResponseDto = new CommentResponseDto(comment, account);
        return commentResponseDto;
    }

    // 댓글 수정 (작성자만)
    @Transactional
    public CommentResponseDto update(Long commentId, CommentRequestDto commentRequestDto, Account account) {
        Comment comment = commentCheck(commentId);

        if (comment.getAccount().getUsername().equals(account.getUsername())) {
            comment.update(commentRequestDto);
            CommentResponseDto commentResponseDto = new CommentResponseDto(comment, account);
            return commentResponseDto;
        } else {
            throw new IllegalArgumentException("Don't have access");
        }
    }


    // 댓글 삭제 (작성자만)
    @Transactional
    public String delete(Long commentId, Account account) {
        Comment comment = commentCheck(commentId);

        if (comment.getAccount().getUsername().equals(account.getUsername())) {
            commentRepository.deleteById(commentId);
            return "삭제완료";
        } else {
            throw new IllegalArgumentException("Don't have access");
        }
    }


    // 댓글 있는지 확인하는 메소드
    private Comment commentCheck(Long commentId) {
        return commentRepository.findById(commentId).orElseThrow(
                () -> new IllegalArgumentException("댓글이 없습니다.")
        );
    }
}
