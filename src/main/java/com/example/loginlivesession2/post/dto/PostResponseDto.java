package com.example.loginlivesession2.post.dto;

import com.example.loginlivesession2.comment.entity.Comment;
import com.example.loginlivesession2.post.entity.Post;
import lombok.Getter;

import java.util.List;

@Getter
public class PostResponseDto {
    private Long postId;
    private String username;
    private String title;
    private String category;
    private String image;
    private String content;
    private List<Comment> comments;

    public PostResponseDto(Post post){
        this.postId = post.getPostId();
        this.username = post.getAccount().getUsername();
        this.title = post.getTitle();
        this.category = post.getCategory();
        this.image = post.getImage();
        this.content = post.getContent();
        this.comments = post.getComment();
    }
}
