package com.example.practice.repository;

import com.example.practice.entity.MemberEntity;
import org.springframework.stereotype.Repository;

import java.util.Map;
import java.util.Optional;
import java.util.concurrent.ConcurrentHashMap;

@Repository
public class MemberRepository {
    private static Map<Long, MemberEntity> memberStore = new ConcurrentHashMap<>();
    private static long sequence = 0L;

    public MemberEntity save(MemberEntity member) {
        member.setId(++sequence);
        memberStore.put(member.getId(), member);
        return member;
    }

    public Optional<MemberEntity> findByLoginId(String loginId) {
        for (Long id : memberStore.keySet()) {
            MemberEntity member = memberStore.get(id);
            if (member.getLoginId().equals(loginId)) {
                return Optional.of(member);
            }
        }
        return Optional.empty();
    }

    public Optional<MemberEntity> findById(Long id) {
        for (Long keyId : memberStore.keySet()) {
            MemberEntity member = memberStore.get(keyId);
            if (member.getId().equals(id)) {
                return Optional.of(member);
            }
        }
        return Optional.empty();
    }


}
