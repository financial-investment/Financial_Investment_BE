package com.financialinvestment.domain.stock.dto.hanto;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class KisAccessTokenRequestDto {
    private String grant_type;
    private String appkey;
    private String appsecret;
}
