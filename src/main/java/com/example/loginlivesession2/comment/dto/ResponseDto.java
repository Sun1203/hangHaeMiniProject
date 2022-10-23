package com.example.loginlivesession2.comment.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class ResponseDto<T> {
    private boolean ok;
    private T data;
    private String massage;

    public static <T> ResponseDto<T> success(T data, String massage) {
        return new ResponseDto<>(true, data, massage);
    }


//    public static <T> ResponseDto<T> fail(String code, String message) {
//        return new ResponseDto<>(false, null, new Error(code, message));
//    }
}
