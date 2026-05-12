package com.financialinvestment.domain.util;

import com.financialinvestment.domain.stock.dto.hanto.KisDailyIndexApiResponseDto;
import com.financialinvestment.domain.stock.dto.hanto.KisIndexApiResponseDto;
import com.financialinvestment.domain.stock.dto.hanto.KisOverseasPriceDetailApiResponseDto;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestClient;

@Component
@RequiredArgsConstructor
public class KisMarketClient {

    private final RestClient kisRestClient;

    @Value("${kis.app-key}")
    private String appKey;

    @Value("${kis.app-secret}")
    private String appSecret;

    /**
     * 국내 업종(코스피, 코스닥) 현재가
     * -> 차트를 그리기엔 맞지 않음.
     *
     * 주의점 : 장중이 아닐때는 등락이 0으로 표시된다.
     */
    public KisIndexApiResponseDto getCurrentIndex(String accessToken, String marketType) throws InterruptedException {
        Thread.sleep(1000);
        return kisRestClient.get()
                .uri(uriBuilder -> uriBuilder
                        .path("/uapi/domestic-stock/v1/quotations/inquire-index-price")
                        .queryParam("FID_COND_MRKT_DIV_CODE", "U")
                        .queryParam("FID_INPUT_ISCD", marketType)
                        .build())
                .header("content-type", MediaType.APPLICATION_JSON_VALUE)
                .header(HttpHeaders.AUTHORIZATION, "Bearer " + accessToken)
                .header("appkey", appKey)
                .header("appsecret", appSecret)
                .header("tr_id", "FHPUP02100000")
                .header("custtype","P")
                .retrieve()
                .body(KisIndexApiResponseDto.class);
    }

    /**
     * 국내 업종 일자별 가격
     *
     */
    public KisDailyIndexApiResponseDto getDailyIndex(String accessToken, String marketType, String period, String date) throws InterruptedException {
        Thread.sleep(1000);
        return kisRestClient.get()
                .uri(uriBuilder -> uriBuilder
                        .path("/uapi/domestic-stock/v1/quotations/inquire-index-daily-price")
                        .queryParam("FID_PERIOD_DIV_CODE", period)
                        .queryParam("FID_COND_MRKT_DIV_CODE", "U")
                        .queryParam("FID_INPUT_ISCD", marketType)
                        .queryParam("FID_INPUT_DATE_1", date)
                        .build())
                .header("content-type", MediaType.APPLICATION_JSON_VALUE)
                .header(HttpHeaders.AUTHORIZATION, "Bearer " + accessToken)
                .header("appkey", appKey)
                .header("appsecret", appSecret)
                .header("tr_id", "FHPUP02120000")
                .header("custtype","P")
                .retrieve()
                .body(KisDailyIndexApiResponseDto.class);
    }

    public KisOverseasPriceDetailApiResponseDto getOverseasPriceDetail(
            String accessToken,
            String exchangeCode,
            String symbol
    ) throws InterruptedException {
        Thread.sleep(1000);
        return kisRestClient.get()
                .uri(uriBuilder -> uriBuilder
                        .path("/uapi/overseas-price/v1/quotations/price-detail")
                        .queryParam("AUTH", "")
                        .queryParam("EXCD", exchangeCode)
                        .queryParam("SYMB", symbol)
                        .build())
                .header("content-type", MediaType.APPLICATION_JSON_VALUE)
                .header(HttpHeaders.AUTHORIZATION, "Bearer " + accessToken)
                .header("appkey", appKey)
                .header("appsecret", appSecret)
                .header("tr_id", "HHDFS76200200")
                .header("custtype", "P")
                .retrieve()
                .body(KisOverseasPriceDetailApiResponseDto.class);
    }
}
