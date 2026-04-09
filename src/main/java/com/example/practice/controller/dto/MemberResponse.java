package com.example.practice.controller.dto;

import com.example.practice.entity.MemberEntity;

public record MemberResponse(Long id, String loginId, String name) {
    public static MemberResponse from(MemberEntity saveMember) {
        return new MemberResponse(saveMember.getId(), saveMember.getLoginId(), saveMember.getName());
    }
}
