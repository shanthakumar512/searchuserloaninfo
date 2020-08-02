package com.rabobank.searchloaninfo.services;

import java.util.List;

import com.rabobank.searchloaninfo.exceptions.LoanInformationNotFoundException;
import com.rabobank.searchloaninfo.model.LoanInformation;

public interface SearchLoanAndBorrowerInfoService {

	public List<LoanInformation> searchLoanInformationWithLoanNUmber(String loanInfo);
	
	public List<LoanInformation> searchBorrowerEmailByFirstName(String borrowerFirstName) throws LoanInformationNotFoundException;
	
	public List<LoanInformation> searchBorrowerEmailByLastName(String borrowerLastName) throws LoanInformationNotFoundException;
	
}
