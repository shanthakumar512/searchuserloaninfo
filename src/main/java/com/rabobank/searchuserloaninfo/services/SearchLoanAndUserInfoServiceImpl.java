/**
 * 
 */
package com.rabobank.searchuserloaninfo.services;

import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.rabobank.searchuserloaninfo.controller.LoanInformationServiceClient;
import com.rabobank.searchuserloaninfo.controller.UserInfromationServiceClient;
import com.rabobank.searchuserloaninfo.exceptions.LoanInformationNotFoundException;
import com.rabobank.searchuserloaninfo.request.LoanInformationRequest;
import com.rabobank.searchuserloaninfo.request.LoanUserRequest;

/**
 * @author Admin
 *
 */
@Service
public class SearchLoanAndUserInfoServiceImpl implements SearchLoanAndUserInfoService {

	@Autowired
	LoanInformationServiceClient loanInformationServiceClient;

	@Autowired
	UserInfromationServiceClient userInfromationServiceClient;
	
	private static final Logger logger = LoggerFactory.getLogger(SearchLoanAndUserInfoServiceImpl.class);
	
	@Override
	public List<LoanInformationRequest> searchLoanInformationWithLoanNUmber(String loanNum) {
		LoanInformationRequest response= loanInformationServiceClient.retrieveLoanInfo(loanNum);
		List<LoanInformationRequest> list= new ArrayList<>();
		list.add(response);
		logger.info("Loan details returned with Loan user Email ::{}",response.getLoanUserEmail());
		return list;
	}

	/* (non-Javadoc)
	 * @see com.rabobank.searchuserloaninfo.services.SearchLoanAndUserInfoService#searchUserEmailByFirstName(java.lang.String)
	 */
	@Override
	public List<LoanInformationRequest> searchUserEmailByFirstName(String userFirstName) throws LoanInformationNotFoundException {
		LoanUserRequest response = userInfromationServiceClient.retrieveUserInfoByFirstName(userFirstName);
		
		if(response.getUserEmail()!=null) {
			logger.info("Loan User returned with  user Email ::{}",response.getUserEmail());
			List<LoanInformationRequest> loanresponse= loanInformationServiceClient.retrieveLoanInfoByEmail(response.getUserEmail());
			logger.info("Size of Loan details returned with Loan user Email ::{}",loanresponse.size());
			return loanresponse;
		} else {
			throw new LoanInformationNotFoundException("Loan Information not found for Email : "+response.getUserEmail()) ;
		}
		
	}

	/* (non-Javadoc)
	 * @see com.rabobank.searchuserloaninfo.services.SearchLoanAndUserInfoService#searchuserEmailByLastName(java.lang.String)
	 */
	@Override
	public List<LoanInformationRequest> searchuserEmailByLastName(String userLastName) throws LoanInformationNotFoundException {

		LoanUserRequest response = userInfromationServiceClient.retrieveUserInfoByLastName(userLastName);		
		
		if(response.getUserEmail()!=null) {
			logger.info("Loan User returned with  user Email ::{}",response.getUserEmail());
			List<LoanInformationRequest> loanInfoResponse= loanInformationServiceClient.retrieveLoanInfoByEmail(response.getUserEmail());
			logger.info("Size of Loan details returned with Loan user Email ::{}",loanInfoResponse.size());
			return loanInfoResponse;
		}  else {
			throw new LoanInformationNotFoundException("Loan Information not found for Email : "+response.getUserEmail() ) ;
		}
			
		
	}

}
