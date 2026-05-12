package com.financialinvestment.domain.stock.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestClient;

@Configuration
public class KisClientConfig {

    @Bean
    public RestClient kisRestClient() {
        return RestClient.builder()
                .baseUrl("https://openapi.koreainvestment.com:9443")
                .build();
    }
    @Bean
    public RestClient kisMockRestClient() {
        return RestClient.builder()
                .baseUrl("https://openapi.koreainvestment.com:29443")
                .build();
    }

}
