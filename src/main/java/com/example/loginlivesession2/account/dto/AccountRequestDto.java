package com.example.loginlivesession2.account.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;

@Getter
@NoArgsConstructor
public class AccountRequestDto {

    @NotBlank
    private String loginId;

    @NotBlank
    private String password;

    @NotBlank
    private String username;

    public AccountRequestDto(String loginId, String password, String username) {
        this.loginId = loginId;
        this.password = password;
        this.username = username;
    }

    public void setEncodePwd(String encodePwd) {
        this.password = encodePwd;
    }

}
