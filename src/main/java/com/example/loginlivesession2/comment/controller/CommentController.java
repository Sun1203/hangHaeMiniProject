package com.example.loginlivesession2.comment.controller;

import com.example.loginlivesession2.account.entity.Account;
import com.example.loginlivesession2.comment.dto.CommentRequestDto;
import com.example.loginlivesession2.comment.dto.CommentResponseDto;
import com.example.loginlivesession2.comment.service.CommentService;
import com.example.loginlivesession2.comment.dto.ResponseDto;
import com.example.loginlivesession2.security.user.UserDetailsImpl;
import lombok.RequiredArgsConstructor;
import org.apache.catalina.UserDatabase;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/comment")
public class CommentController {

    private final CommentService commentService;

    // 댓글 등록 api
    @PostMapping("/{postId}")
    public ResponseDto createComment(@RequestBody CommentRequestDto commentRequestDto,
                                     @AuthenticationPrincipal UserDetailsImpl userDetails,
                                     @PathVariable Long postId){
        return ResponseDto.success(commentService.save(commentRequestDto, userDetails.getAccount(), postId));
    }

    // 댓글 수정 api
    @PutMapping("/{commentId}")
    public ResponseDto updateComment(@PathVariable Long commentId,
                                     @RequestBody CommentRequestDto commentRequestDto,
                                     @AuthenticationPrincipal Account account){
        return ResponseDto.success(commentService.update(commentId, commentRequestDto, account)) ;
    }

    // 댓글 삭제 api
    @PutMapping("/{commentId}")
    public ResponseDto deleteComment(@PathVariable Long commentId,
                                     @AuthenticationPrincipal Account account){
        return ResponseDto.success(commentService.delete(commentId, account));
    }
}
