package com.financialinvestment.domain.stock.dto.hanto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.List;

@Getter
@NoArgsConstructor
public class KisDailyIndexApiResponseDto {
    @JsonProperty("rt_cd")
    private String rtCd;   // 성공 실패 여부

    @JsonProperty("msg_cd")
    private String msgCd;  // 응답코드

    @JsonProperty("msg1")
    private String msg1;   // 응답메시지

    @JsonProperty("output1")
    private KisDailyIndexOutput1Dto output1;

    @JsonProperty("output2")
    private List<KisDailyIndexOutput2Dto> output2;
}
