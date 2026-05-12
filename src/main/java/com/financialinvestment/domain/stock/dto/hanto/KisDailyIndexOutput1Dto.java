package com.financialinvestment.domain.stock.dto.hanto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.NoArgsConstructor;

/**
 * 현재 해당 dto에서 실제로 사용할 값
 * 현재가, 전일대비, 등락률, 방향, 거래량, 거래대금
 */
@Getter
@NoArgsConstructor
public class KisDailyIndexOutput1Dto {

    @JsonProperty("bstp_nmix_prpr")
    private String bstpNmixPrpr; //지수 현재가

    @JsonProperty("bstp_nmix_prdy_vrss")
    private String bstpNmixPrdyVrss; //지수 전일 대비

    @JsonProperty("prdy_vrss_sign")
    private String prdyVrssSign; //전일 대비 부호

    @JsonProperty("bstp_nmix_prdy_ctrt")
    private String bstpNmixPrdyCtrt; //지수 전일 대비율

    @JsonProperty("acml_vol")
    private String acmlVol; //누적 거래량

    @JsonProperty("acml_tr_pbmn")
    private String acmlTrPbmn; //누적 거래 대금

    @JsonProperty("bstp_nmix_oprc")
    private String bstpNmixOprc;

    @JsonProperty("bstp_nmix_hgpr")
    private String bstpNmixHgpr;

    @JsonProperty("bstp_nmix_lwpr")
    private String bstpNmixLwpr;

    @JsonProperty("prdy_vol")
    private String prdyVol;

    @JsonProperty("ascn_issu_cnt")
    private String ascnIssuCnt;

    @JsonProperty("down_issu_cnt")
    private String downIssuCnt;

    @JsonProperty("stnr_issu_cnt")
    private String stnrIssuCnt;

    @JsonProperty("uplm_issu_cnt")
    private String uplmIssuCnt;

    @JsonProperty("lslm_issu_cnt")
    private String lslmIssuCnt;

    @JsonProperty("prdy_tr_pbmn")
    private String prdyTrPbmn;

    @JsonProperty("dryy_bstp_nmix_hgpr_date")
    private String dryyBstpNmixHgprDate;

    @JsonProperty("dryy_bstp_nmix_hgpr")
    private String dryyBstpNmixHgpr;

    @JsonProperty("dryy_bstp_nmix_lwpr")
    private String dryyBstpNmixLwpr;

    @JsonProperty("dryy_bstp_nmix_lwpr_date")
    private String dryyBstpNmixLwprDate;
}