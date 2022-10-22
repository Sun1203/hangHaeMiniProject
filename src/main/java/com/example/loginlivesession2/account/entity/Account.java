package com.example.loginlivesession2.account.entity;

import com.example.loginlivesession2.account.dto.AccountRequestDto;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.NotBlank;

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

    public Account(AccountRequestDto requestDto) {
        this.loginId = requestDto.getLoginId();
        this.password = requestDto.getPassword();
        this.username = requestDto.getUsername();
    }

}
