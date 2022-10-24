package com.example.loginlivesession2.post.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Getter
public class ResponseDto<T> {
    private boolean ok;
    private T data;
    private String massage;

    public static <T> ResponseDto<T> success(T data, String massage) {
        return new ResponseDto<>(true, data, massage);
    }

    public static <T> ResponseDto<T> success(String massage) {
        return new ResponseDto<>();
    }
//
//    @Getter
//    @AllArgsConstructor
//    static class Error {
//        private String code;
//        private String message;
//    }
}
