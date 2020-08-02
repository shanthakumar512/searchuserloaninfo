package com.rabobank.searchloaninfo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.openfeign.EnableFeignClients;

import springfox.documentation.swagger2.annotations.EnableSwagger2;

@SpringBootApplication
@EnableDiscoveryClient
@EnableFeignClients("com.rabobank.searchloaninfo.*")
@EnableSwagger2
public class SearchLoanInformationApplication {

	public static void main(String[] args) {
		SpringApplication.run(SearchLoanInformationApplication.class, args);
	}

}
