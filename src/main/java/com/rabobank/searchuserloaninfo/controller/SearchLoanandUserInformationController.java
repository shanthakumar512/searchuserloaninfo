/**
 * 
 */
package com.rabobank.searchuserloaninfo.controller;

import java.util.List;
import javax.validation.Valid;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.rabobank.searchuserloaninfo.exceptions.LoanInformationNotFoundException;
import com.rabobank.searchuserloaninfo.request.LoanInformation;
import com.rabobank.searchuserloaninfo.request.SearchLoanRequest;
import com.rabobank.searchuserloaninfo.services.SearchLoanAndUserInfoService;

/**
 * @author Admin
 *
 */
@RestController
@RequestMapping("/searchinfo")
public class SearchLoanandUserInformationController {
	
private static final Logger logger = LoggerFactory.getLogger(SearchLoanandUserInformationController.class);

@Autowired
SearchLoanAndUserInfoService searchLoanAndUserInfoService;
	
@PostMapping("/loanInformation")
@PreAuthorize("hasRole('USER') or hasRole('ADMIN')")
public ResponseEntity<?> searchLoanInfoByLoanNum(@Valid @RequestBody SearchLoanRequest searchLoanRequest) throws LoanInformationNotFoundException {
	logger.info("Entered searchLoanInfoByLoanNum() method");
	
	if(null != searchLoanRequest.getLoanNumber() && !searchLoanRequest.getLoanNumber().isEmpty()) {
		logger.info("Entered search with loan Number block Loan NUmber ::{}",searchLoanRequest.getLoanNumber());
		List<LoanInformation> list= searchLoanAndUserInfoService.searchLoanInformationWithLoanNUmber(searchLoanRequest.getLoanNumber());
		return new ResponseEntity<> (list, HttpStatus.OK);
	}
	
	if(null != searchLoanRequest.getUserFirstname() && !searchLoanRequest.getUserFirstname().isEmpty()) {
		logger.info("Entered search with loan user First Name block  ::{}",searchLoanRequest.getUserFirstname());
		List<LoanInformation> loanInfoList =	searchLoanAndUserInfoService.searchUserEmailByFirstName(searchLoanRequest.getUserFirstname());
		return new ResponseEntity<> (loanInfoList, HttpStatus.OK);
		
	}
	
	if(null != searchLoanRequest.getUserLastname() && !searchLoanRequest.getUserLastname().isEmpty()) {
		logger.info("Entered search with loan user Last Name block  ::{}",searchLoanRequest.getUserLastname());
		List<LoanInformation> loanInfoList =	searchLoanAndUserInfoService.searchuserEmailByLastName(searchLoanRequest.getUserLastname());
		return new ResponseEntity<> (loanInfoList, HttpStatus.OK);
	}
	
	return  ResponseEntity.badRequest().body("Search failed. Fine tune Search");	
}
	
}
