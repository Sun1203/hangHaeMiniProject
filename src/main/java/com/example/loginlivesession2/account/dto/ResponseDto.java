package com.example.loginlivesession2.account.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class ResponseDto {
    private boolean ok;
    private String  message;

    public static ResponseDto success(String message) {
        return new ResponseDto(true, message);
    }

}
