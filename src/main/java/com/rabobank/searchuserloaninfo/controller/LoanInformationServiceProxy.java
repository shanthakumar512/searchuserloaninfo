package com.rabobank.searchuserloaninfo.controller;

import java.util.List;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import com.rabobank.searchuserloaninfo.request.LoanInformationRequest;

@FeignClient(name="loan-information-service")
public interface LoanInformationServiceProxy {
	
	@GetMapping("loanInfo/getLoanInfo/{loanNumber}")
	public LoanInformationRequest retrieveLoanInfo(@PathVariable("loanNumber") String loanNumber);
	
	@GetMapping("loanInfo/getLoanInfoByEmail/{loanUserEmail}")
	public List<LoanInformationRequest> retrieveLoanInfoByEmail(@PathVariable("loanUserEmail") String loanUserEmail);
}
