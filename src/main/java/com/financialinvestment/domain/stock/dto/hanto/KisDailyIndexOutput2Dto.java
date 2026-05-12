package com.financialinvestment.domain.stock.dto.hanto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.NoArgsConstructor;

/**
 * 캔들 차트 기준 사용 변수
 * 날짜 + 시가 + 고가 + 저가 + 현재가
 */
@Getter
@NoArgsConstructor
public class KisDailyIndexOutput2Dto {

    @JsonProperty("stck_bsop_date")
    private String stckBsopDate; //주식 영업 일자

    @JsonProperty("bstp_nmix_prpr")
    private String bstpNmixPrpr; //업종 지수 현재가

    @JsonProperty("prdy_vrss_sign")
    private String prdyVrssSign;

    @JsonProperty("bstp_nmix_prdy_vrss")
    private String bstpNmixPrdyVrss;

    @JsonProperty("bstp_nmix_prdy_ctrt")
    private String bstpNmixPrdyCtrt;

    @JsonProperty("bstp_nmix_oprc")
    private String bstpNmixOprc; //지수 시가

    @JsonProperty("bstp_nmix_hgpr")
    private String bstpNmixHgpr; //최고가

    @JsonProperty("bstp_nmix_lwpr")
    private String bstpNmixLwpr; //최저가

    @JsonProperty("acml_vol_rlim")
    private String acmlVolRlim;

    @JsonProperty("acml_vol")
    private String acmlVol;

    @JsonProperty("acml_tr_pbmn")
    private String acmlTrPbmn;

    @JsonProperty("invt_new_psdg")
    private String invtNewPsdg;

    @JsonProperty("d20_dsrt")
    private String d20Dsrt;
}
