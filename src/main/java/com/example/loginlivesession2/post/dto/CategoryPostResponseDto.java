package com.example.loginlivesession2.post.dto;

import com.example.loginlivesession2.post.entity.Post;
import lombok.Getter;

@Getter
public class CategoryPostResponseDto {

        private String username;
        private String title;
        private String category;
        private String image;
        private String content;
        private Long heart;

        public CategoryPostResponseDto(Post post, Long heart) {
                this.username = post.getAccount().getUsername();
                this.title = post.getTitle();
                this.category = post.getCategory();
                this.image = post.getImage();
                this.content = post.getContent();
                this.heart = heart;
        }
}
