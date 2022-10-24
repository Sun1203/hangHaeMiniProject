package com.example.loginlivesession2.post.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class PostRequestDto {
    private  String title;
    private String category;
    private String image;
    private String content;
}
