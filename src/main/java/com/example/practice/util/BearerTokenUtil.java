package com.example.practice.util;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class BearerTokenUtil { // AuthUtil AuthTokenUtil
    private static final String PREFIX = "Bearer ";

    // parsing(accessToken(Bearer + SessionKey) -> SessionKey)

    public static boolean isValidToken(String accessToken) {
        return accessToken.startsWith(PREFIX);
    }
    public static String getSessionKey(String accessToken) {
        return accessToken.substring(PREFIX.length()).trim();
    }
    //


}
