package com.example.loginlivesession2.comment.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class ResponseDto<T> {
    private boolean ok;
    private T data;
    private String message;

    public static <T> ResponseDto<T> success(T data, String message) {
        return new ResponseDto<>(true, data, message);
    }

}
