package com.example.loginlivesession2.comment.entity;


import com.example.loginlivesession2.account.entity.Account;
import com.example.loginlivesession2.comment.dto.CommentRequestDto;
import com.example.loginlivesession2.comment.dto.CommentResponseDto;
import com.example.loginlivesession2.post.entity.Post;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@NoArgsConstructor
@Setter
@Getter
@Entity
public class Comment {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long commentId;

    @Column(nullable = true)
    private String content;

    @Column
    private String username;

    @ManyToOne
    @JoinColumn(name = "post_id")
    @JsonIgnore
    private Post post;

    @ManyToOne
    @JoinColumn(name = "account_id")
    @JsonIgnore
    private Account account;

    public Comment(CommentRequestDto commentRequestDto, Account account) {
        this.content = commentRequestDto.getContent();
        this.account = account;
    }

    public Comment(CommentRequestDto commentRequestDto, Account account, Post post) {
        this.content = commentRequestDto.getContent();
        this.account = account;
        this.post = post;
        this.username = account.getUsername();
    }

    public void update(CommentRequestDto requestDto) {
        this.content = requestDto.getContent();
    }
}
