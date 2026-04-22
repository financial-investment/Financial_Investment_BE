package com.financialinvestment.domain.auth.entity;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum OauthProvider {

    KAKAO("kakao", "카카오"),
    NAVER("naver","네이버"),
    GOOGLE("google", "구글");

    private final String code;
    private final String displayName;

    // 문자열로부터 OAuthProvider 찾기
    public static OauthProvider fromCode(String code) {
        for (OauthProvider provider : values()) {
            if (provider.getCode().equalsIgnoreCase(code)) {
                return provider;
            }
        }
        throw new IllegalArgumentException("Invalid OAuth provider: " + code);
    }

    // 문자열이 유효한 OAuth provider인지 확인
    public static boolean isValid(String code) {
        try {
            fromCode(code);
            return true;
        } catch (IllegalArgumentException e) {
            return false;
        }
    }
}
