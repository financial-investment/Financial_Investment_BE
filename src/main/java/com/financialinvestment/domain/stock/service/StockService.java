package com.financialinvestment.domain.stock.service;

import com.financialinvestment.domain.stock.dto.front.ExchangeRateResponseDto;
import com.financialinvestment.domain.stock.dto.front.IndexDailyChartPointDto;
import com.financialinvestment.domain.stock.dto.front.IndexSummaryResponseDto;
import com.financialinvestment.domain.stock.dto.hanto.*;
import com.financialinvestment.domain.util.*;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.time.LocalDate;
import java.time.OffsetDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Comparator;
import java.util.List;

@Service
@RequiredArgsConstructor
@Slf4j
public class StockService {

    private final KisMarketClient kisMarketClient;
    private final KisAuthClient kisAuthClient;

    /**
     * 코스피와 코스닥의 일별 지수 값을 가져옴
     *
     */
    public List<IndexSummaryResponseDto> getIndexSummaries() throws InterruptedException {
        String accessToken = kisAuthClient.getAccessToken();

        KisDailyIndexApiResponseDto kospiResponse = kisMarketClient.getDailyIndex(
                accessToken,  "0001", "D", DateUtil.getTodayDateyyyyMMdd()
        );

        KisDailyIndexApiResponseDto kosdaqResponse = kisMarketClient.getDailyIndex(
                accessToken,  "1001", "D", DateUtil.getTodayDateyyyyMMdd()
        );

        return List.of(
                toIndexSummary(IndexType.KOSPI, "코스피", kospiResponse),
                toIndexSummary(IndexType.KOSDAQ, "코스닥", kosdaqResponse)
        );
    }

    private IndexSummaryResponseDto toIndexSummary(
            IndexType indexType,
            String name,
            KisDailyIndexApiResponseDto response
    ) {
        KisDailyIndexOutput1Dto output1 = response.getOutput1();

        //전일 대비 등락
        BigDecimal change = toBigDecimal(output1.getBstpNmixPrdyVrss());

        List<IndexDailyChartPointDto> chart = response.getOutput2().stream()
                .sorted(Comparator.comparing(KisDailyIndexOutput2Dto::getStckBsopDate))
                .map(output -> new IndexDailyChartPointDto(
                        formatDate(output.getStckBsopDate()),
                        toBigDecimal(output.getBstpNmixPrpr())
                ))
                .toList();
        Direction direction = toDirection(output1.getPrdyVrssSign(), change);
        return new IndexSummaryResponseDto(
                toId(indexType),
                name,
                toBigDecimal(output1.getBstpNmixPrpr()),
                change,
                toBigDecimal(output1.getBstpNmixPrdyCtrt()),
                direction,
                OffsetDateTime.now(),
                toChartColor(direction),
                chart
        );
    }
    public List<IndexSummaryResponseDto> getExchangeRateSummary() throws InterruptedException {
        String accessToken = kisAuthClient.getAccessToken();

        KisOverseasPriceDetailApiResponseDto response = kisMarketClient.getOverseasPriceDetail(accessToken, "NAS", "QQQ");
        KisOverseasPriceDetailOutputDto output = response.getOutput();

        BigDecimal current = toBigDecimal(output.getTRate());
        BigDecimal previous = toBigDecimal(output.getPRate());
        BigDecimal change = current.subtract(previous);

        BigDecimal changeRate = previous.compareTo(BigDecimal.ZERO) == 0
                ? BigDecimal.ZERO
                : change.divide(previous, 6, RoundingMode.HALF_UP)
                  .multiply(BigDecimal.valueOf(100));

        Direction direction = compareDirection(change);

        return List.of(new IndexSummaryResponseDto(
                "usdKrw",
                "원/달러",
                current,
                change,
                changeRate,
                direction,
                OffsetDateTime.now(),
                toChartColor(direction),
                List.of()
        ));
    }

    private String toId(IndexType indexType) {
        return indexType.name().toLowerCase();
    }

    private String formatDate(String value) {
        return LocalDate.parse(value, DateTimeFormatter.BASIC_ISO_DATE)
                .format(DateTimeFormatter.ISO_LOCAL_DATE);
    }


    private BigDecimal toBigDecimal(String value) {
        if (value == null || value.isBlank()) {
            return BigDecimal.ZERO;
        }
        return new BigDecimal(value.trim());
    }

    private Direction toDirection(String sign, BigDecimal change) {
        if (sign == null || sign.isBlank()) {
            return compareDirection(change);
        }

        return switch (sign.trim()) {
            case "1", "2" -> Direction.UP;
            case "4", "5" -> Direction.DOWN;
            case "3" -> Direction.SAME;
            default -> compareDirection(change);
        };
    }

    private Direction compareDirection(BigDecimal change) {
        int compare = change.compareTo(BigDecimal.ZERO);
        if (compare > 0) {
            return Direction.UP;
        }
        if (compare < 0) {
            return Direction.DOWN;
        }
        return Direction.SAME;
    }

    private String toChartColor(Direction direction) {
        return switch (direction) {
            case UP -> "#006e1c";
            case DOWN -> "#ba1a1a";
            case SAME -> "#6f6f6f";
        };
    }

}
