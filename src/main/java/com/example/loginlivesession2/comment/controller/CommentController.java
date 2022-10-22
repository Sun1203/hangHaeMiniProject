package com.example.loginlivesession2.comment.controller;

import com.example.loginlivesession2.comment.dto.CommentRequestDto;
import com.example.loginlivesession2.comment.service.CommentService;
import com.example.loginlivesession2.comment.dto.ResponseDto;
import com.example.loginlivesession2.security.user.UserDetailsImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/comment")
public class CommentController {

    private final CommentService commentService;

    @PostMapping("/{postId}")
    public ResponseDto createComment(@RequestBody CommentRequestDto commentRequestDto,
                                     @AuthenticationPrincipal UserDetailsImpl userDetails,
                                     @PathVariable Long postId){
        return ResponseDto.success(commentService.save(commentRequestDto, userDetails.getAccount(), postId));
    }

//    @PutMapping("/{commentId}")
//    public ResponseDto createCOmment
}
