package com.example.practice.service;

import com.example.practice.controller.dto.MemberRequest;
import com.example.practice.controller.dto.MemberResponse;
import com.example.practice.entity.MemberEntity;
import com.example.practice.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

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
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));
//                .orElseThrow(() -> new NotFoundException("해당 아이디 없음"));

        return MemberResponse.from(member);
    }
}
