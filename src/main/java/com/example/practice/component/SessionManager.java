package com.example.practice.component;

import org.springframework.stereotype.Component;

import java.util.Map;
import java.util.UUID;
import java.util.concurrent.ConcurrentHashMap;

@Component
public class SessionManager {

    private Map<String, Long> sessionStore = new ConcurrentHashMap<>();


    public String createSession(Long id) {
        String uuid = String.valueOf(UUID.randomUUID());

        // sessionStore 저장
        sessionStore.put(uuid, id);
        return uuid;
    }

    public void removeSession(String token) {
        sessionStore.remove(token);
        // 만약 로그인 정보가 없으면? 이럴수가 있나?
        return;
    }

    public Long getMemberId(String accessToken) {
        return sessionStore.get(accessToken);   // 없으면 null
    }
}
