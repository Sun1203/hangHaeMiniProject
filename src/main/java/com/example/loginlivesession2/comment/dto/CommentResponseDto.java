package com.example.loginlivesession2.comment.dto;


import com.example.loginlivesession2.account.entity.Account;
import com.example.loginlivesession2.comment.entity.Comment;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class CommentResponseDto {

    private String username;
    private String content;

    public CommentResponseDto(Comment comment){
        this.username = comment.getUsername();
        this.content = comment.getContent();
    }

}
