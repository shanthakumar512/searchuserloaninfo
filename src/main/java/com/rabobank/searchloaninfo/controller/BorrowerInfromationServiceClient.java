package com.rabobank.searchloaninfo.controller;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import com.rabobank.searchloaninfo.model.Borrower;

@FeignClient(name="user-information-service")
public interface BorrowerInfromationServiceClient {
	
	@GetMapping("loanUser/getLoanUserByFirstName/{userFirstName}")
	public Borrower retrieveBorrowerInfoByFirstName(@PathVariable("userFirstName") String userFirstName);
	
	@GetMapping("loanUser/getLoanUserByLastName/{userLastName}")
	public Borrower retrieveBorrowerInfoByLastName(@PathVariable("userLastName") String userLastName);

}
