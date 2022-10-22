package com.example.loginlivesession2.comment.entity;


import com.example.loginlivesession2.account.entity.Account;
import com.example.loginlivesession2.comment.dto.CommentRequestDto;
import com.example.loginlivesession2.post.entity.Post;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Getter
@NoArgsConstructor
public class Comment {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long commenId;

    @Column
    private String comment;

    @ManyToOne
    @JoinColumn(name = "acccount_id")
    @JsonIgnore
    private Account account;

    @ManyToOne
    @JoinColumn(name = "post_id")
    @JsonIgnore
    private Post post;

    public Comment(CommentRequestDto commentRequestDto, Account account, Post post) {
        this.comment = commentRequestDto.getComment();
        this.account = account;
        this.post = post;
    }

    public void update(CommentRequestDto commentRequestDto) {
        this.comment = commentRequestDto.getComment();
    }
}
