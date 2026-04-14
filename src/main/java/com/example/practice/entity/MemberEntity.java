package com.example.practice.entity;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class MemberEntity {
    private static long AUTO_INCREMENT_ID = 0L;

    private Long id;
    private String loginId;
    private String password;
    private String name;

    public MemberEntity(String loginId, String password, String name) {
        this.id = ++AUTO_INCREMENT_ID;
        this.loginId = loginId;
        this.password = password;
        this.name = name;
    }
}
