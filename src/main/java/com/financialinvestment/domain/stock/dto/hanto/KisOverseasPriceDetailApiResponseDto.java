package com.financialinvestment.domain.stock.dto.hanto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class KisOverseasPriceDetailApiResponseDto {
    @JsonProperty("rt_cd")
    private String rtCd;
    @JsonProperty("msg_cd")
    private String msgCd;
    @JsonProperty("msg1")
    private String msg1;

    @JsonProperty("output")
    private KisOverseasPriceDetailOutputDto output;
}
