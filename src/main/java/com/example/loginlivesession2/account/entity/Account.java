package com.example.loginlivesession2.account.entity;

import com.example.loginlivesession2.account.dto.AccountRequestDto;
import com.example.loginlivesession2.comment.entity.Comment;
import com.example.loginlivesession2.post.entity.Post;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import java.util.ArrayList;
import java.util.List;

@Getter
@Entity
@NoArgsConstructor
public class Account {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long accountId;

    private String loginId;

    private String password;

    private String username;

    @OneToMany(mappedBy = "account")
    List<Post> post = new ArrayList<>();


    @OneToMany(mappedBy = "account")
    List<Comment> comment =new ArrayList<>();


    public Account(AccountRequestDto requestDto) {
        this.loginId = requestDto.getLoginId();
        this.password = requestDto.getPassword();
        this.username = requestDto.getUsername();
    }

}
