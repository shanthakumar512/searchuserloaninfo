package com.rabobank.searchuserloaninfo.services;

import java.util.List;

import com.rabobank.searchuserloaninfo.exceptions.LoanInformationNotFoundException;
import com.rabobank.searchuserloaninfo.request.LoanInformationRequest;

public interface SearchLoanAndUserInfoService {

	public List<LoanInformationRequest> searchLoanInformationWithLoanNUmber(String loanInfo);
	
	public List<LoanInformationRequest> searchUserEmailByFirstName(String userFirstName) throws LoanInformationNotFoundException;
	
	public List<LoanInformationRequest> searchuserEmailByLastName(String userLastName) throws LoanInformationNotFoundException;
	
}
