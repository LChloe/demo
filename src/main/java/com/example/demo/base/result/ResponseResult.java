package com.example.demo.base.result;

import lombok.Builder;
import lombok.Data;

import java.io.Serializable;

/**
 * 返回结构体 REST风格API
 * @param <T>
 */
@Data
@Builder
public class ResponseResult<T> {

    private long timestamp;
    private String code;
    private String message;
    private T data;

    public static <T> ResponseResult<T> success() {
        return success(null);
    }

    public static <T> ResponseResult<T> success(T data) {
        return ResponseResult.<T>builder().data(data)
                .message(ResponseStatus.SUCCESS.getMessage())
                .code(ResponseStatus.SUCCESS.getCode())
                .timestamp(System.currentTimeMillis())
                .build();
    }

    public static <T extends Serializable> ResponseResult<T> fail(String message) {
        return fail(null, message);
    }

    public static <T extends Serializable> ResponseResult<T> fail(T data, String message) {
        return ResponseResult.<T>builder().data(data)
                .message(message)
                .code(ResponseStatus.FAIL.getCode())
                .timestamp(System.currentTimeMillis())
                .build();
    }
}
