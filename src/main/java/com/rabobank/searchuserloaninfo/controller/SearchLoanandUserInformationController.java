/**
 * 
 */
package com.rabobank.searchuserloaninfo.controller;

import java.util.ArrayList;
import java.util.List;
import javax.validation.Valid;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.rabobank.searchuserloaninfo.request.LoanInformationRequest;
import com.rabobank.searchuserloaninfo.request.LoanUserRequest;
import com.rabobank.searchuserloaninfo.request.SearchLoanRequest;

/**
 * @author Admin
 *
 */
@RestController
@RequestMapping("/searchinfo")
public class SearchLoanandUserInformationController {
	
private static final Logger logger = LoggerFactory.getLogger(SearchLoanandUserInformationController.class);
	
@Autowired
LoanInformationServiceProxy loanInformationServiceProxy;

@Autowired
UserInfromationServiceProxy userInfromationServiceProxy;

	
@PostMapping("/loanInformation")
public ResponseEntity<?> searchLoanInfoByLoanNum(@Valid @RequestBody SearchLoanRequest searchLoanRequest) {
	logger.info("Entered searchLoanInfoByLoanNum() method");
	if(null != searchLoanRequest.getLoanNumber() && !searchLoanRequest.getLoanNumber().isEmpty()) {
		logger.info("Entered search with loan Number block Loan NUmber ::{}",searchLoanRequest.getLoanNumber());
		LoanInformationRequest response= loanInformationServiceProxy.retrieveLoanInfo(searchLoanRequest.getLoanNumber());
		List<LoanInformationRequest> list= new ArrayList<>();
		list.add(response);
		logger.info("Loan details returned with Loan user Email ::{}",response.getLoanUserEmail());
		return new ResponseEntity<> (list, HttpStatus.OK);
	}
	
	if(null != searchLoanRequest.getUserFirstname() && !searchLoanRequest.getUserFirstname().isEmpty()) {
		logger.info("Entered search with loan user First Name block  ::{}",searchLoanRequest.getUserFirstname());
		LoanUserRequest response = userInfromationServiceProxy.retrieveUserInfoByFirstName(searchLoanRequest.getUserFirstname());
		
		if(response.getUserEmail()!=null) {
			logger.info("Loan User returned with  user Email ::{}",response.getUserEmail());
			List<LoanInformationRequest> loanresponse= loanInformationServiceProxy.retrieveLoanInfoByEmail(response.getUserEmail());
			logger.info("Size of Loan details returned with Loan user Email ::{}",loanresponse.size());
			return new ResponseEntity<> (loanresponse, HttpStatus.OK);
		}
	}
	
	if(null != searchLoanRequest.getUserLastname() && !searchLoanRequest.getUserLastname().isEmpty()) {
		logger.info("Entered search with loan user Last Name block  ::{}",searchLoanRequest.getUserLastname());
		LoanUserRequest response = userInfromationServiceProxy.retrieveUserInfoByLastName(searchLoanRequest.getUserLastname());		
		
		if(response.getUserEmail()!=null) {
			logger.info("Loan User returned with  user Email ::{}",response.getUserEmail());
			List<LoanInformationRequest> loanInfoResponse= loanInformationServiceProxy.retrieveLoanInfoByEmail(response.getUserEmail());
			logger.info("Size of Loan details returned with Loan user Email ::{}",loanInfoResponse.size());
			return new ResponseEntity<> (loanInfoResponse, HttpStatus.OK);
		}
		
	}
	
	return  ResponseEntity.badRequest().body("Search failed. Fine tune Search");	
}
	
}
