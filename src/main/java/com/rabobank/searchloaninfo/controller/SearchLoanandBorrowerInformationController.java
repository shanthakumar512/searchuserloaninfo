/**
 * 
 */
package com.rabobank.searchloaninfo.controller;

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
import org.springframework.web.server.ResponseStatusException;

import com.rabobank.searchloaninfo.exceptions.LoanInformationNotFoundException;
import com.rabobank.searchloaninfo.model.LoanInformation;
import com.rabobank.searchloaninfo.model.SearchLoanRequest;
import com.rabobank.searchloaninfo.services.SearchLoanAndBorrowerInfoService;

import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

/**
 * @author Admin
 *
 */
@RestController
@RequestMapping("/searchinfo")
public class SearchLoanandBorrowerInformationController {
	
private static final Logger logger = LoggerFactory.getLogger(SearchLoanandBorrowerInformationController.class);

@Autowired
SearchLoanAndBorrowerInfoService searchLoanAndBorrowerInfoService;

@ApiOperation(value = "Search the loan information for given search criteria", response = LoanInformation.class, tags = "searchLoanInfoByLoanNum")
@ApiResponses(value = { 
            @ApiResponse(code = 200, message = "Success|OK"),
            @ApiResponse(code = 401, message = "not authorized!"), 
            @ApiResponse(code = 403, message = "forbidden!!!"),
            @ApiResponse(code = 404, message = "not found!!!") })	
@PostMapping("/loanInformation")
public ResponseEntity<List<LoanInformation>> searchLoanInfoByLoanNum(@Valid @RequestBody SearchLoanRequest searchLoanRequest) throws LoanInformationNotFoundException {
	logger.info("Entered searchLoanInfoByLoanNum() method");
	
	if(null != searchLoanRequest.getLoanNumber() && !searchLoanRequest.getLoanNumber().isEmpty()) {
		logger.debug("Entered search with loan Number block Loan NUmber ::{}",searchLoanRequest.getLoanNumber());
		List<LoanInformation> list= searchLoanAndBorrowerInfoService.searchLoanInformationWithLoanNUmber(searchLoanRequest.getLoanNumber());
		return new ResponseEntity<> (list, HttpStatus.OK);
	} 
	
	else if(null != searchLoanRequest.getBorrowerFirstname() && !searchLoanRequest.getBorrowerFirstname().isEmpty()) {
		logger.info("Entered search with loan user First Name block  ::{}",searchLoanRequest.getBorrowerFirstname());
		List<LoanInformation> loanInfoList =	searchLoanAndBorrowerInfoService.searchBorrowerEmailByFirstName(searchLoanRequest.getBorrowerFirstname());
		return new ResponseEntity<> (loanInfoList, HttpStatus.OK);
		
	}
	
	else if(null != searchLoanRequest.getBorrowerLastname() && !searchLoanRequest.getBorrowerLastname().isEmpty()) {
		logger.info("Entered search with loan user Last Name block  ::{}",searchLoanRequest.getBorrowerLastname());
		List<LoanInformation> loanInfoList =	searchLoanAndBorrowerInfoService.searchBorrowerEmailByLastName(searchLoanRequest.getBorrowerLastname());
		return new ResponseEntity<> (loanInfoList, HttpStatus.OK);
	}
	else throw new ResponseStatusException(HttpStatus.BAD_REQUEST);
}
	
}
