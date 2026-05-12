package com.financialinvestment;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;

@SpringBootApplication
public class FinancialInvestmentApplication {
	public static void main(String[] args) {
		SpringApplication.run(FinancialInvestmentApplication.class, args);
	}
}
