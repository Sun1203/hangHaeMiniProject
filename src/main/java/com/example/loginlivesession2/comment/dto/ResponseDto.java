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
//public static <T> ResponseDto<T> success(T data) {
//        return new ResponseDto<>(true, data, null);
//    }
//    public static <T> ResponseDto<T> fail(String code, String message) {
//        return new ResponseDto<>(false, null, new Error(code, message));
//    }
}
