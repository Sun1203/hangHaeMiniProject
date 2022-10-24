package com.example.loginlivesession2.exception;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
@AllArgsConstructor
public enum ErrorCode {
    NOT_FOUND_MEMBER(HttpStatus.NOT_FOUND.value(), "M001", "해당 유저 id를 찾을 수 없습니다"),
    NOT_FOUND_COMMENT(HttpStatus.NOT_FOUND.value(), "M002", "해당 댓글을 찾을 수 없습니다"),
    NOT_FOUND_COMMWNTUSER(HttpStatus.BAD_REQUEST.value(), "M003","해당 댓글 작성자가 아닙니다"),

    ;
    private final int httpStatus;
    private final String code;
    private final String message;
}
