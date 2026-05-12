package com.financialinvestment.domain.stock.dto.front;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.math.BigDecimal;
import java.time.OffsetDateTime;

@Getter
@AllArgsConstructor
public class ExchangeRateResponseDto {
    private String id;              // usdKrw
    private String label;           // 원/달러
    private BigDecimal currentRate; // t_rate
    private BigDecimal previousRate;// p_rate
    private OffsetDateTime baseDateTime;
}
