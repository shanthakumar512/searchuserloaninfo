package com.rabobank.searchuserloaninfo.controller;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import com.rabobank.searchuserloaninfo.request.LoanUser;

@FeignClient(name="user-information-service")
public interface UserInfromationServiceClient {
	
	@GetMapping("loanUser/getLoanUserByFirstName/{userFirstName}")
	public LoanUser retrieveUserInfoByFirstName(@PathVariable("userFirstName") String userFirstName);
	
	@GetMapping("loanUser/getLoanUserByLastName/{userLastName}")
	public LoanUser retrieveUserInfoByLastName(@PathVariable("userLastName") String userLastName);

}
