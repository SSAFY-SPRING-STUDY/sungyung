package com.example.practice.global.response;

import com.example.practice.global.exception.error.ErrorCode;

public record ApiResponse<T> (String message, T data){
    // sucess(T data) : 성공 응답용(데이터 포함)
    public static <T> ApiResponse<T> success(String message, T data) {
        return new ApiResponse<>(message, data);
    }

    // success() : 성공 응답용(데이터 미포함)
    public static <T> ApiResponse<T> success(String message) {
        return new ApiResponse<>(message, null);
    }

    public static <T> ApiResponse<T> error(ErrorCode errorCode) {
        return new ApiResponse<>(errorCode.getMessage(), null);
    }

    public static <T> ApiResponse<T> error(ErrorCode errorCode, T data) {
        return new ApiResponse<>(errorCode.getMessage(), data);
    }

}
