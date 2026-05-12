package com.financialinvestment.domain.stock.dto.front;

import com.financialinvestment.domain.util.Direction;
import com.financialinvestment.domain.util.IndexType;
import lombok.AllArgsConstructor;
import lombok.Getter;

import java.math.BigDecimal;
import java.time.OffsetDateTime;
import java.util.List;

@Getter
@AllArgsConstructor
public class IndexSummaryResponseDto {

    private String id;                    // kospi, kosdaq
    private String label;                 // 코스피, 코스닥
    private BigDecimal currentPrice;
    private BigDecimal change;
    private BigDecimal changeRate;
    private Direction direction;
    private OffsetDateTime baseDateTime;
    private String chartColor;            // #ba1a1a 등
    private List<IndexDailyChartPointDto> chartData;
}
