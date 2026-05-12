package com.financialinvestment.domain.util;

import com.financialinvestment.domain.stock.dto.hanto.KisAccessTokenRequestDto;
import com.financialinvestment.domain.stock.dto.hanto.KisAccessTokenResponseDto;
import lombok.RequiredArgsConstructor;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestClient;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

@Component
@RequiredArgsConstructor
public class KisAuthClient {

    private final RestClient kisRestClient;

    @Value("${kis.app-key}")
    private String appKey;

    @Value("${kis.app-secret}")
    private String appSecret;

    private String accessToken;
    private LocalDateTime accessTokenExpiredAt;

    public synchronized String getAccessToken() {
        if (isValidToken()) {
            return accessToken;
        }

        KisAccessTokenRequestDto request = new KisAccessTokenRequestDto(
                "client_credentials",
                appKey,
                appSecret
        );

        KisAccessTokenResponseDto response = kisRestClient.post()
                .uri("/oauth2/tokenP")
                .contentType(MediaType.APPLICATION_JSON)
                .body(request)
                .retrieve()
                .body(KisAccessTokenResponseDto.class);

        if (response == null || response.getAccessToken() == null) {
            throw new IllegalStateException("한투 access token 발급 실패");
        }

        this.accessToken = response.getAccessToken();
        this.accessTokenExpiredAt = parseExpiredAt(response.getTokenExpired());

        return this.accessToken;
    }

    private boolean isValidToken() {
        return accessToken != null
                && accessTokenExpiredAt != null
                && LocalDateTime.now().isBefore(accessTokenExpiredAt.minusMinutes(5));
    }

    private LocalDateTime parseExpiredAt(String expiredAt) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        return LocalDateTime.parse(expiredAt, formatter);
    }
}
