package com.rabobank.searchuserloaninfo.services;

public interface SearchLoanAndUserInfoService {

	public void searchLoanInformationWithLoanNUmber(String loanInfo);
	
	public void searchUserEmailByFirstName(String userFirstName) ;
	
	public void searchuserEmailByLastName(String userLastName);
	
}
