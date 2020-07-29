package com.rabobank.searchuserloaninfo.services;

import java.util.List;

import com.rabobank.searchuserloaninfo.exceptions.LoanInformationNotFoundException;
import com.rabobank.searchuserloaninfo.request.LoanInformation;

public interface SearchLoanAndUserInfoService {

	public List<LoanInformation> searchLoanInformationWithLoanNUmber(String loanInfo);
	
	public List<LoanInformation> searchUserEmailByFirstName(String userFirstName) throws LoanInformationNotFoundException;
	
	public List<LoanInformation> searchuserEmailByLastName(String userLastName) throws LoanInformationNotFoundException;
	
}
