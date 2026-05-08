package com.example.practice.member.service;

import com.example.practice.global.exception.CustomException;
import com.example.practice.global.exception.error.ErrorCode;
import com.example.practice.member.controller.dto.MemberRequest;
import com.example.practice.member.controller.dto.MemberResponse;
import com.example.practice.member.entity.MemberEntity;
import com.example.practice.member.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class MemberService {

    private final MemberRepository memberRepository;

    public MemberResponse save(MemberRequest memberRequest) {
        MemberEntity member = new MemberEntity(
                memberRequest.loginId(), memberRequest.password(), memberRequest.name()
        );
        MemberEntity saveMember = memberRepository.save(member);
        return MemberResponse.from(saveMember);
    }

    public MemberResponse findById(Long id) {
        MemberEntity member = memberRepository.findById(id)
                .orElseThrow(() -> new CustomException(ErrorCode.MEMBER_NOT_FOUND));
        return MemberResponse.from(member);
    }
}
