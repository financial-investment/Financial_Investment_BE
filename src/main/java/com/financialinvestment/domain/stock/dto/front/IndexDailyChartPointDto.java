package com.financialinvestment.domain.stock.dto.front;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.math.BigDecimal;

@Getter
@AllArgsConstructor
public class IndexDailyChartPointDto {

    private String time;          // 영업일자 2026-05-12
    private BigDecimal value;     // 해당일 지수
}
