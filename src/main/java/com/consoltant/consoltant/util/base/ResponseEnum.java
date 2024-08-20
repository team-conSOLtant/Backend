package com.consoltant.consoltant.util.base;

import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
@JsonPropertyOrder({"success", "statusCode", "httpStatus", "message", "timestamp", "result"})
public enum ResponseEnum {

    SUCCESS(true, 1000, HttpStatus.OK.value(), "요청에 성공하였습니다.");  //성공 코드

    private final boolean success;  //성공여부
    private final int statusCode;
    private final int httpStatus; //HTTP 상태코드
    private final String message; //메시지

    ResponseEnum(boolean success, int statusCode, int httpStatus, String message) {
        this.success = success;
        this.statusCode = statusCode;
        this.httpStatus = httpStatus;
        this.message = message;
    }
}
