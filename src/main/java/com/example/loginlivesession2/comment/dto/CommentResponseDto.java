package com.example.loginlivesession2.comment.dto;


import com.example.loginlivesession2.account.entity.Account;
import com.example.loginlivesession2.comment.entity.Comment;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class CommentResponseDto {

    private String username;
    private String comment;

    public CommentResponseDto(Comment comment, Account account) {
        this.username = account.getUsername();
        this.comment = comment.getComment();
    }
}
