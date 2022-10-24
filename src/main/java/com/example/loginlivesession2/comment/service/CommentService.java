package com.example.loginlivesession2.comment.service;

import com.example.loginlivesession2.account.entity.Account;
import com.example.loginlivesession2.comment.dto.CommentRequestDto;
import com.example.loginlivesession2.comment.dto.CommentResponseDto;
import com.example.loginlivesession2.comment.entity.Comment;
import com.example.loginlivesession2.comment.repository.CommentRepository;
import com.example.loginlivesession2.exception.CustomException;
import com.example.loginlivesession2.exception.ErrorCode;
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


    // 댓글 생성
    @Transactional
    public CommentResponseDto create(CommentRequestDto commentRequestDto, Long postId,Account account) {

        Post post = postRepository.findById(postId).orElseThrow(
                () -> new IllegalArgumentException("게시글을 찾을수가 없습니다.")
        );

        Comment comment = new Comment(commentRequestDto, account, post);

        commentRepository.save(comment);
        CommentResponseDto commentResponseDto = new CommentResponseDto(comment);

        return commentResponseDto;
    }


    // 댓글 전체 조회
//    @Transactional
//    public List<Comment> getAllComment() {
//
//        return commentRepository.findAllByOrderByCreatedAtDesc();
//    }


    // 댓글 수정
    @Transactional
    public CommentResponseDto update(CommentRequestDto commentRequestDto, Long commentId, Account account) {

        Comment comment = commentCheck(commentId);

        if(comment.getAccount().getUsername().equals(account.getUsername())){
            comment.update(commentRequestDto);
            CommentResponseDto commentResponseDto = new CommentResponseDto(comment);
            return commentResponseDto;
        } else {
            throw new CustomException(ErrorCode.NOT_FOUND_COMMWNTUSER);
        }

    }

    //댓글 삭제
    @Transactional
    public void delete(Long commentId, Account currentAccount) {

        Comment comment = commentCheck(commentId);

        if (comment.getAccount().getUsername().equals(currentAccount.getUsername())) {
            commentRepository.deleteById(commentId);

        } else {
            throw new CustomException(ErrorCode.NOT_FOUND_COMMWNTUSER);
        }

    }

    // 댓글 있는지 확인하는 메소드
    public Comment commentCheck(Long commentId){
        return commentRepository.findById(commentId).orElseThrow(
                () -> new CustomException(ErrorCode.NOT_FOUND_COMMENT)
        );
    }

}
