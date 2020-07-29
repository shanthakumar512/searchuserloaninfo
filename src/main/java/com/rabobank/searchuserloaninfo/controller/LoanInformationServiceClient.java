package com.rabobank.searchuserloaninfo.controller;

import java.util.List;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import com.rabobank.searchuserloaninfo.request.LoanInformation;

@FeignClient(name="loan-information-service")
public interface LoanInformationServiceClient {
	
	@GetMapping("loanInfo/getLoanInfo/{loanNumber}")
	public LoanInformation retrieveLoanInfo(@PathVariable("loanNumber") String loanNumber);
	
	@GetMapping("loanInfo/getLoanInfoByEmail/{loanUserEmail}")
	public List<LoanInformation> retrieveLoanInfoByEmail(@PathVariable("loanUserEmail") String loanUserEmail);
}
 