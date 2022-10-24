package com.example.loginlivesession2.heart.entity;


import com.example.loginlivesession2.account.entity.Account;
import com.example.loginlivesession2.post.entity.Post;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity @Getter
@NoArgsConstructor
public class Heart {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long heartId;

    @ManyToOne(fetch = FetchType.LAZY)
    private Account account;

    @ManyToOne(fetch = FetchType.LAZY)
    private Post post;


    public Heart(Account account, Post post) {
        this.account = account;
        this.post = post;
    }
}
