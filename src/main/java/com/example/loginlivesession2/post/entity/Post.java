package com.example.loginlivesession2.post.entity;

import com.example.loginlivesession2.comment.entity.Comment;
import com.example.loginlivesession2.post.dto.PostRequestDto;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@NoArgsConstructor
public class Post {

    @Id
    @GeneratedValue
    private Long postId;

    @Column
    private String title;

    @OneToMany(mappedBy = "post")
    private List<Comment> comments = new ArrayList<>();

    public Post(PostRequestDto postRequestDto) {
        this.title = postRequestDto.getTitle();
    }
}
