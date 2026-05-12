package com.financialinvestment.domain.stock.controller;


import com.financialinvestment.domain.stock.dto.front.IndexSummaryResponseDto;
import com.financialinvestment.domain.stock.service.StockService;
import com.financialinvestment.domain.util.ApiResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/stock")
@RequiredArgsConstructor
@Slf4j
public class StockController {

    private final StockService stockService;

    @GetMapping("/market/indexes/summary")
    public ApiResponse<List<IndexSummaryResponseDto>> getIndexSummaries() throws InterruptedException {
        List<IndexSummaryResponseDto> result = stockService.getIndexSummaries();
        log.debug("대시보드용 요약 조회함.");
        return ApiResponse.success("대시보드용 요약 조회 성공", result);
    }

    @GetMapping("/market/exchange-rate/summary")
    public ApiResponse<List<IndexSummaryResponseDto>> getExchangeRateSummary() throws InterruptedException {
        List<IndexSummaryResponseDto> result = stockService.getExchangeRateSummary();
        return ApiResponse.success("환율 요약 조회 성공", result);
    }

}
