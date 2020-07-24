/**
 * 
 */
package com.rabobank.searchuserloaninfo.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

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
	
@Autowired
LoanInformationServiceProxy loanInformationServiceProxy;

@Autowired
UserInfromationServiceProxy userInfromationServiceProxy;

	
@PutMapping("/loanInformation")
public ResponseEntity<?> searchLoanInfoByLoanNum(@Valid @RequestBody SearchLoanRequest searchLoanRequest) {
	
	if(null != searchLoanRequest.getLoanNumber() && !searchLoanRequest.getLoanNumber().isEmpty()) {
		  Map<String, String> uriVariables = new HashMap<>();
		    uriVariables.put("loanNumber", searchLoanRequest.getLoanNumber());
		
		/*LoanInformationRequest response = new RestTemplate().getForObject(
		        "http://localhost:8085/loanInfo/getLoanInfo/{loanNumber}", LoanInformationRequest.class,
		        uriVariables);	*/	
		        
		LoanInformationRequest response= loanInformationServiceProxy.retrieveLoanInfo(searchLoanRequest.getLoanNumber());
				
		return new ResponseEntity<LoanInformationRequest> (response, HttpStatus.OK);
	}
	
	if(null != searchLoanRequest.getUserFirstname() && !searchLoanRequest.getUserFirstname().isEmpty()) {
		/*  Map<String, String> uriVariables = new HashMap<>();
		    uriVariables.put("userFirstName", searchLoanRequest.getUserFirstname());
		
		LoanUserRequest responseEntity = new RestTemplate().getForObject(
		        "http://localhost:8082/loanUser/getLoanUserByFirstName/{userFirstName}", LoanUserRequest.class,
		        uriVariables);*/
		
		LoanUserRequest response = userInfromationServiceProxy.retrieveUserInfoByFirstName(searchLoanRequest.getUserFirstname());
		
		if(response.getUserEmail()!=null) {
			/*LoanInformationRequest loanresponseEntity = new RestTemplate().getForObject(
			        "http://localhost:8085/loanInfo/getLoanInfoByEmail/{loanUserEmail}", LoanInformationRequest.class,
			        uriVariables);*/
			List<LoanInformationRequest> loanresponse= loanInformationServiceProxy.retrieveLoanInfoByEmail(response.getUserEmail());
			
			/*@SuppressWarnings("unchecked")
			List<LoanInformationRequest> loanInfoResponse = (List<LoanInformationRequest>) loanresponseEntity;	
			*/
			return new ResponseEntity<List<LoanInformationRequest>> (loanresponse, HttpStatus.OK);
		}
		return new ResponseEntity<LoanUserRequest> (response, HttpStatus.OK);
	}
	
	if(null != searchLoanRequest.getUserLastname() && !searchLoanRequest.getUserLastname().isEmpty()) {
		  /*Map<String, String> uriVariables = new HashMap<>();
		    uriVariables.put("userlastName", searchLoanRequest.getUserLastname());
		
		ResponseEntity<LoanUserRequest> responseEntity = new RestTemplate().getForEntity(
		        "http://localhost:8082/loanUser/getLoanUserByFirstName/{userlastName}", LoanUserRequest.class,
		        uriVariables);*/
		LoanUserRequest response = userInfromationServiceProxy.retrieveUserInfoByLastName(searchLoanRequest.getUserLastname());
		
		if(response.getUserEmail()!=null) {
			/*ResponseEntity<LoanInformationRequest> loanresponseEntity = new RestTemplate().getForEntity(
			        "http://localhost:8085/loanInfo//getLoanInfoByEmail/{loanUserEmail}", LoanInformationRequest.class,
			        uriVariables);*/
			List<LoanInformationRequest> loanInfoResponse= loanInformationServiceProxy.retrieveLoanInfoByEmail(response.getUserEmail());
//			@SuppressWarnings("unchecked")
//			List<LoanInformationRequest> loanInfoResponse = (List<LoanInformationRequest>) loanresponseEntity;	
			
			return new ResponseEntity<List<LoanInformationRequest>> (loanInfoResponse, HttpStatus.OK);
		}
		
		return new ResponseEntity<LoanUserRequest> (response, HttpStatus.OK);
	}
	
	return  ResponseEntity.badRequest().body("Search failed. Fine tune Search");	
}
	
}
