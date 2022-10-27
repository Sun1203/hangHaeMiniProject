package com.example.loginlivesession2.comment.controller;

import com.example.loginlivesession2.comment.dto.CommentRequestDto;
import com.example.loginlivesession2.comment.dto.ResponseDto;
import com.example.loginlivesession2.comment.service.CommentService;
import com.example.loginlivesession2.security.user.UserDetailsImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RequiredArgsConstructor
@RestController
@RequestMapping("/comment")
public class CommentController {
    private final CommentService commentService;


    // 댓글 등록
    @PostMapping("/{postId}")
    public ResponseDto<?> createComment(@RequestBody @Valid CommentRequestDto commentRequestDto,
                                         @PathVariable Long postId,
                                         @AuthenticationPrincipal UserDetailsImpl userDetails) {
        return ResponseDto.success(commentService.create(commentRequestDto, postId, userDetails.getAccount()), "등록 성공");
    }


    // 댓글 수정
    @PutMapping("/{commentId}")
    public ResponseDto<?> updateComment(@RequestBody @Valid CommentRequestDto commentRequestDto,
                                        @PathVariable Long commentId,
                                        @AuthenticationPrincipal UserDetailsImpl userDetails) {
        return ResponseDto.success(commentService.update(commentRequestDto, commentId, userDetails.getAccount()), "수정 성공");
    }


    // 댓글 삭제
    @DeleteMapping("/{commentId}")
    public ResponseDto<?> deleteComment(@PathVariable Long commentId,
                                @AuthenticationPrincipal UserDetailsImpl userDetails) {
        commentService.delete(commentId, userDetails.getAccount());
        return ResponseDto.success("", "삭제 성공");
    }
}
