package com.example.loginlivesession2.account.entity;

import com.example.loginlivesession2.account.dto.AccountRequestDto;
import com.example.loginlivesession2.comment.entity.Comment;
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
    @NotBlank
    private String loginId;
    @NotBlank
    private String password;
    @NotBlank
    private String username;

    @OneToMany(mappedBy = "account")
    private List<Comment> comments = new  ArrayList<>();

    public Account(AccountRequestDto requestDto) {
        this.loginId = requestDto.getLoginId();
        this.password = requestDto.getPassword();
        this.username = requestDto.getUsername();
    }

}
