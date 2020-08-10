/**
 * 
 */
package com.rabobank.searchloaninfo.services;

import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.rabobank.searchloaninfo.controller.BorrowerInfromationServiceClient;
import com.rabobank.searchloaninfo.controller.LoanInformationServiceClient;
import com.rabobank.searchloaninfo.exceptions.LoanInformationNotFoundException;
import com.rabobank.searchloaninfo.model.Borrower;
import com.rabobank.searchloaninfo.model.LoanInformation;

/**
 * @author Admin
 *
 */
@Service
public class SearchLoanAndBorrowerInfoServiceImpl implements SearchLoanAndBorrowerInfoService {

	@Autowired
	LoanInformationServiceClient loanInformationServiceClient;

	@Autowired
	BorrowerInfromationServiceClient borrowerInfromationServiceClient;
	
	private static final Logger logger = LoggerFactory.getLogger(SearchLoanAndBorrowerInfoServiceImpl.class);
	
	@Override
	public List<LoanInformation> searchLoanInformationWithLoanNUmber(String loanNum) {
		LoanInformation response= loanInformationServiceClient.retrieveLoanInfo(loanNum);
		List<LoanInformation> list= new ArrayList<>();
		list.add(response);
		logger.info("Loan details returned for Loan Borrower Email ::{}",response.getLoanUserEmail());
		return list;
	}

	/* (non-Javadoc)
	 * @see com.rabobank.searchloaninfo.services.SearchLoanAndBorrowerInfoService#searchUserEmailByFirstName(java.lang.String)
	 */
	@Override
	public List<LoanInformation> searchBorrowerEmailByFirstName(String borrowerFirstName) throws LoanInformationNotFoundException {
		Borrower response = borrowerInfromationServiceClient.retrieveBorrowerInfoByFirstName(borrowerFirstName);
		
		if(response.getBorrowerEmail()!=null) {
			logger.info("Borrower Informaton returned with  Borrower Email");
			List<LoanInformation> loanresponse= loanInformationServiceClient.retrieveLoanInfoByEmail(response.getBorrowerEmail());
			logger.debug("Size of Loan details returned with Loan Borrower Email ::{}",loanresponse.size());
			return loanresponse;
		} else {
			throw new LoanInformationNotFoundException("Loan Information not found for Email : "+response.getBorrowerEmail()) ;
		}
		
	}

	/* (non-Javadoc)
	 * @see com.rabobank.searchloaninfo.services.SearchLoanAndBorrowerInfoService#searchuserEmailByLastName(java.lang.String)
	 */
	@Override
	public List<LoanInformation> searchBorrowerEmailByLastName(String borrowerLastName) throws LoanInformationNotFoundException {

		Borrower response = borrowerInfromationServiceClient.retrieveBorrowerInfoByLastName(borrowerLastName);		
		
		if(response.getBorrowerEmail()!=null) {
			logger.info("Borrower information returned with  Borrower Email");
			List<LoanInformation> loanInfoResponse= loanInformationServiceClient.retrieveLoanInfoByEmail(response.getBorrowerEmail());
			logger.debug("Size of Loan details returned with Borrower Email ::{}",loanInfoResponse.size());
			return loanInfoResponse;
		}  else {
			throw new LoanInformationNotFoundException("Loan Information not found for Email : "+response.getBorrowerEmail() ) ;
		}
			
		
	}

}
