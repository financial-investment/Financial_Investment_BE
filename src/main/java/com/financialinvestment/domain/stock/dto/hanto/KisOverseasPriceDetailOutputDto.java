package com.financialinvestment.domain.stock.dto.hanto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class KisOverseasPriceDetailOutputDto {

    @JsonProperty("output")
    private String curr;   // 통화
    @JsonProperty("t_rate")
    private String tRate;  // 당일환율
    @JsonProperty("p_rate")
    private String pRate;  // 전일환율
}
