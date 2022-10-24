package com.example.loginlivesession2.account.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;

@Getter
@NoArgsConstructor
public class AccountRequestDto {

    @NotBlank
    @Pattern(regexp = "^[a-zA-Z][0-9a-zA-Z]{6,20}$", message = "아이디 형식을 맞춰주세요.")
    private String loginId;

    @NotBlank
    @Pattern(regexp = "^(?=.*[a-zA-z])(?=.*[0-9])(?=.*[$`~!@$!%*#^?&\\\\(\\\\)\\-_=+]).{8,16}$", message = "비밀번호 형식을 맞춰주세요.")
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
