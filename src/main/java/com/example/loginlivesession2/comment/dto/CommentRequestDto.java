package com.example.loginlivesession2.comment.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Getter
@NoArgsConstructor
public class CommentRequestDto {
    @NotNull
    private String content;
}