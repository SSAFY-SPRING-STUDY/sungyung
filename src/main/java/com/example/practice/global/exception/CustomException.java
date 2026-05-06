package com.example.practice.global.exception;

import com.example.practice.global.exception.error.ErrorCode;
import lombok.Getter;

@Getter
public class CustomException extends RuntimeException {

    /**
     * CustomExceptiondp에는 메시지 정보가 필요
     * 메시지를 어디에 저장할까? 내 필드? 부모에게 위임?
     * 부모(RuntimeException)가 이미 메세지 저장소를 갖고 있음 -> 이건 내가 알고 있으야하는 정보인가? -> 한 번 알고나면 계속 재활용
     * 부모가 가진 저장소에 getMessage()를 넘기면 ?
     * 넘기려면 super(message) 생성자 호출
     * 예외는 ErrorCode를 받으니까 거기서 메세지 꺼내서 넘긴다
     * => super(errorCode.getMessage())
     */
    private final ErrorCode errorCode;

    public CustomException(ErrorCode errorCode) {
        super(errorCode.getMessage());
        this.errorCode = errorCode;
    }
}
