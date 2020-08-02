package com.rabobank.searchloaninfo;


import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.actuate.autoconfigure.security.servlet.ManagementWebSecurityAutoConfiguration;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestClientException;

import com.rabobank.searchloaninfo.SearchLoanInformationApplication;
import com.rabobank.searchloaninfo.controller.BorrowerInfromationServiceClient;
import com.rabobank.searchloaninfo.controller.LoanInformationServiceClient;
import com.rabobank.searchloaninfo.exceptions.LoanInformationNotFoundException;
import com.rabobank.searchloaninfo.model.Address;
import com.rabobank.searchloaninfo.model.Borrower;
import com.rabobank.searchloaninfo.model.LoanInformation;
import com.rabobank.searchloaninfo.model.SearchLoanRequest;
import com.rabobank.searchloaninfo.services.SearchLoanAndBorrowerInfoServiceImpl;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.*;

@SpringBootTest(classes = {SearchLoanInformationApplication.class},webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@EnableAutoConfiguration(exclude = { SecurityAutoConfiguration.class, ManagementWebSecurityAutoConfiguration.class })
public class SearchuserloaninfoIntegrationTests {
	
	
	private static final String PATH = "/searchinfo/loanInformation";

	private static final String EMAIL = "abc@gmail.com";

	private static final String USER2 = "user2";

	private static final String LOANNUMBER2 = "ABC123";

	private static final String ORIGIN_ACCOUNT = "ACB123476";

	private static final String LOAN_STATUS = "ACTIVE";

	private static final String LOANNUM = "ABC12345";

	@MockBean()
	LoanInformationServiceClient loanInformationServiceClient;

	@MockBean()
	BorrowerInfromationServiceClient borrowerInfromationServiceClient;
	
	@Autowired
	SearchLoanAndBorrowerInfoServiceImpl searchLoanAndUserInfoService;
	
	@Autowired
	private TestRestTemplate restTemplate;

	@LocalServerPort
	private int port;

	private String getRootUrl() {
		return "http://localhost:" + port;
	}
	
	
	@Test
	public void searchByLoanNumTest() {
		
		List<LoanInformation> list= new ArrayList<>();
		
		LoanInformation loanInformation = new LoanInformation();
		loanInformation.setLoanUserEmail("abc@gmail.c");
		loanInformation.setLoanAmount(1234568);
		loanInformation.setLoanNumber(LOANNUM);
		loanInformation.setLoanTerm(45);
		loanInformation.setLoanStatus(LOAN_STATUS);
		loanInformation.setLoanMgtFees(7895);
		loanInformation.setOriginationAccount(ORIGIN_ACCOUNT);
		
		LoanInformation loanInformation1 = new LoanInformation();
		loanInformation1.setLoanUserEmail("abc@gmail.c");
		loanInformation1.setLoanAmount(1234568);
		loanInformation1.setLoanNumber(LOANNUMBER2);
		loanInformation1.setLoanTerm(45);
		loanInformation1.setLoanStatus(LOAN_STATUS);
		loanInformation1.setLoanMgtFees(7895);
		loanInformation1.setOriginationAccount(ORIGIN_ACCOUNT);
		list.add(loanInformation);
		list.add(loanInformation1);

		
		when(loanInformationServiceClient.retrieveLoanInfo(LOANNUM)).thenReturn(loanInformation);
		
		SearchLoanRequest searchLoanRequest= new SearchLoanRequest();
		searchLoanRequest.setLoanNumber(LOANNUM);
		
		ResponseEntity<LoanInformation[]> updatedEmployee = restTemplate.postForEntity(getRootUrl() + PATH, searchLoanRequest, LoanInformation[].class);
		assertNotNull(updatedEmployee.getBody());
	}
	
	@Test
	public void searchByFirstLoanNumTest() {
		
		List<LoanInformation> list= new ArrayList<>();
		
		LoanInformation loanInformation = new LoanInformation();
		loanInformation.setLoanUserEmail(EMAIL);
		loanInformation.setLoanAmount(1234568);
		loanInformation.setLoanNumber(LOANNUM);
		loanInformation.setLoanTerm(45);
		loanInformation.setLoanStatus(LOAN_STATUS);
		loanInformation.setLoanMgtFees(7895);
		loanInformation.setOriginationAccount(ORIGIN_ACCOUNT);
		
		LoanInformation loanInformation1 = new LoanInformation();
		loanInformation1.setLoanUserEmail(EMAIL);
		loanInformation1.setLoanAmount(1234568);
		loanInformation1.setLoanNumber(LOANNUMBER2);
		loanInformation1.setLoanTerm(45);
		loanInformation1.setLoanStatus(LOAN_STATUS);
		loanInformation1.setLoanMgtFees(7895);
		loanInformation1.setOriginationAccount(ORIGIN_ACCOUNT);
		list.add(loanInformation);
		list.add(loanInformation1);
		
		Borrower addUserRequest= new Borrower();
		addUserRequest.setBorrowerFirstname(USER2);
		addUserRequest.setBorrowerLastname(USER2);
		addUserRequest.setBorrowerEmail(EMAIL);
		Address propertyAddress = new Address();
		propertyAddress.setAddressLine1("a1");
		propertyAddress.setAddressLine2("a2");
		propertyAddress.setAddressLine3("a3");
		propertyAddress.setCity("city");
		propertyAddress.setState("TN");
		propertyAddress.setCountry("Ind");
		addUserRequest.setPropertyAddress(propertyAddress);
		
		when(borrowerInfromationServiceClient.retrieveBorrowerInfoByFirstName(USER2)).thenReturn(addUserRequest);
		when(loanInformationServiceClient.retrieveLoanInfoByEmail(EMAIL)).thenReturn(list);
		
		SearchLoanRequest searchLoanRequest= new SearchLoanRequest();
		searchLoanRequest.setBorrowerFirstname(USER2);
		
		ResponseEntity<LoanInformation[]> updatedEmployee = restTemplate.postForEntity(getRootUrl() + PATH, searchLoanRequest, LoanInformation[].class);
		assertNotNull(updatedEmployee.getBody());
	}
	
	 @Test
	public void searchByLastLoanNumTest() throws LoanInformationNotFoundException {
	List<LoanInformation> list= new ArrayList<>();
		
		LoanInformation loanInformation = new LoanInformation();
		loanInformation.setLoanUserEmail(EMAIL);
		loanInformation.setLoanAmount(1234568);
		loanInformation.setLoanNumber(LOANNUM);
		loanInformation.setLoanTerm(45);
		loanInformation.setLoanStatus(LOAN_STATUS);
		loanInformation.setLoanMgtFees(7895);
		loanInformation.setOriginationAccount(ORIGIN_ACCOUNT);
		
		LoanInformation loanInformation1 = new LoanInformation();
		loanInformation1.setLoanUserEmail(EMAIL);
		loanInformation1.setLoanAmount(1234568);
		loanInformation1.setLoanNumber(LOANNUMBER2);
		loanInformation1.setLoanTerm(45);
		loanInformation1.setLoanStatus(LOAN_STATUS);
		loanInformation1.setLoanMgtFees(7895);
		loanInformation1.setOriginationAccount(ORIGIN_ACCOUNT);
		list.add(loanInformation);
		list.add(loanInformation1);
		
		Borrower addUserRequest= new Borrower();
		addUserRequest.setBorrowerFirstname(USER2);
		addUserRequest.setBorrowerLastname(USER2);
		addUserRequest.setBorrowerEmail(EMAIL);
		Address propertyAddress = new Address();
		propertyAddress.setAddressLine1("a1");
		propertyAddress.setAddressLine2("a2");
		propertyAddress.setAddressLine3("a3");
		propertyAddress.setCity("city");
		propertyAddress.setState("TN");
		propertyAddress.setCountry("Ind");
		addUserRequest.setPropertyAddress(propertyAddress);
		when(borrowerInfromationServiceClient.retrieveBorrowerInfoByLastName(USER2)).thenReturn(addUserRequest);
		when(loanInformationServiceClient.retrieveLoanInfoByEmail(EMAIL)).thenReturn(list);
		
		SearchLoanRequest searchLoanRequest= new SearchLoanRequest();
		searchLoanRequest.setBorrowerLastname(USER2);
		
		ResponseEntity<LoanInformation[]> updatedEmployee = restTemplate.postForEntity(getRootUrl() + PATH, searchLoanRequest, LoanInformation[].class);
		assertNotNull(updatedEmployee.getBody());
	}
	 
	 @Test
	 public void searchByLastLoanNumTestControllerAdvice() {
			List<LoanInformation> list= new ArrayList<>();
				
				LoanInformation loanInformation = new LoanInformation();
				loanInformation.setLoanUserEmail(EMAIL);
				loanInformation.setLoanAmount(1234568);
				loanInformation.setLoanNumber(LOANNUM);
				loanInformation.setLoanTerm(45);
				loanInformation.setLoanStatus(LOAN_STATUS);
				loanInformation.setLoanMgtFees(7895);
				loanInformation.setOriginationAccount(ORIGIN_ACCOUNT);
				
				LoanInformation loanInformation1 = new LoanInformation();
				loanInformation1.setLoanUserEmail(EMAIL);
				loanInformation1.setLoanAmount(1234568);
				loanInformation1.setLoanNumber(LOANNUMBER2);
				loanInformation1.setLoanTerm(45);
				loanInformation1.setLoanStatus(LOAN_STATUS);
				loanInformation1.setLoanMgtFees(7895);
				loanInformation1.setOriginationAccount(ORIGIN_ACCOUNT);
				list.add(loanInformation);
				list.add(loanInformation1);
				
				Borrower addUserRequest= new Borrower();
				addUserRequest.setBorrowerFirstname(USER2);
				addUserRequest.setBorrowerLastname(USER2);
				Address propertyAddress = new Address();
				propertyAddress.setAddressLine1("a1");
				propertyAddress.setAddressLine2("a2");
				propertyAddress.setAddressLine3("a3");
				propertyAddress.setCity("city");
				propertyAddress.setState("TN");
				propertyAddress.setCountry("Ind");
				addUserRequest.setPropertyAddress(propertyAddress);
				
				when(borrowerInfromationServiceClient.retrieveBorrowerInfoByLastName(USER2)).thenReturn(addUserRequest);
				when(loanInformationServiceClient.retrieveLoanInfoByEmail(EMAIL)).thenReturn(list);
				
				SearchLoanRequest searchLoanRequest= new SearchLoanRequest();
				searchLoanRequest.setBorrowerLastname(USER2);
				Assertions.assertThrows(RestClientException.class,()-> restTemplate.postForEntity(getRootUrl() + PATH, searchLoanRequest, LoanInformation[].class));
			}
	 
	 		@Test
	 		public void testURINotFoundException() {
	 			SearchLoanRequest searchLoanRequest= new SearchLoanRequest();
				searchLoanRequest.setBorrowerLastname(USER2);
				Assertions.assertThrows(RestClientException.class,()-> restTemplate.postForEntity(getRootUrl() + "/search/", searchLoanRequest, LoanInformation[].class));		
	 			
	 		}

}
