package com.financialinvestment.domain.stock.dto.hanto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class KisAccessTokenResponseDto {

    @JsonProperty("access_token")
    private String accessToken;

    @JsonProperty("token_type")
    private String tokenType;

    @JsonProperty("access_token_token_expired")
    private String tokenExpired;

    @JsonProperty("expires_in")
    private Long expiresIn;
}
