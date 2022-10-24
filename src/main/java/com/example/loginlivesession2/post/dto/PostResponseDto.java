package com.example.loginlivesession2.post.dto;

import com.example.loginlivesession2.comment.dto.CommentResponseDto;
import com.example.loginlivesession2.post.entity.Post;
import lombok.Getter;

@Getter
public class PostResponseDto {
    private String username;
    private String title;
    private String category;
    private String image;
    private String content;

    public PostResponseDto(Post post){
        this.username = post.getAccount().getUsername();
        this.title = post.getTitle();
        this.category = post.getCategory();
        this.image = post.getImage();
        this.content = post.getContent();
    }

}
