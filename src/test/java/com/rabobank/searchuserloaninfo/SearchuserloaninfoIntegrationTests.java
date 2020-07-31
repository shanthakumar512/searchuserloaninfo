package com.rabobank.searchuserloaninfo;

import static org.junit.Assert.assertNotNull;

import java.util.ArrayList;
import java.util.List;

import org.junit.Test;
import org.junit.jupiter.api.Assertions;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.web.client.RestClientException;

import static org.mockito.Mockito.*;

import com.rabobank.searchuserloaninfo.request.LoanInformation;
import com.rabobank.searchuserloaninfo.controller.LoanInformationServiceClient;
import com.rabobank.searchuserloaninfo.controller.UserInfromationServiceClient;
import com.rabobank.searchuserloaninfo.request.SearchLoanRequest;
import com.rabobank.searchuserloaninfo.services.SearchLoanAndUserInfoServiceImpl;
import com.rabobank.searchuserloaninfo.request.Address;
import com.rabobank.searchuserloaninfo.request.LoanUser;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = {SearchuserloaninfoApplication.class},
webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)

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
	UserInfromationServiceClient userInfromationServiceClient;
	
	@Autowired
	SearchLoanAndUserInfoServiceImpl searchLoanAndUserInfoService;
	
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
		
		LoanUser addUserRequest= new LoanUser();
		addUserRequest.setUserFirstname(USER2);
		addUserRequest.setUserLastname(USER2);
		addUserRequest.setUserEmail(EMAIL);
		Address propertyAddress = new Address();
		propertyAddress.setAddressLine1("a1");
		propertyAddress.setAddressLine2("a2");
		propertyAddress.setAddressLine3("a3");
		propertyAddress.setCity("city");
		propertyAddress.setState("TN");
		propertyAddress.setCountry("Ind");
		addUserRequest.setPropertyAddress(propertyAddress);
		
		when(userInfromationServiceClient.retrieveUserInfoByFirstName(USER2)).thenReturn(addUserRequest);
		when(loanInformationServiceClient.retrieveLoanInfoByEmail(EMAIL)).thenReturn(list);
		
		SearchLoanRequest searchLoanRequest= new SearchLoanRequest();
		searchLoanRequest.setUserFirstname(USER2);
		
		ResponseEntity<LoanInformation[]> updatedEmployee = restTemplate.postForEntity(getRootUrl() + PATH, searchLoanRequest, LoanInformation[].class);
		assertNotNull(updatedEmployee.getBody());
	}
	
	 @Test
	public void searchByLastLoanNumTest() {
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
		
		LoanUser addUserRequest= new LoanUser();
		addUserRequest.setUserFirstname(USER2);
		addUserRequest.setUserLastname(USER2);
		addUserRequest.setUserEmail(EMAIL);
		Address propertyAddress = new Address();
		propertyAddress.setAddressLine1("a1");
		propertyAddress.setAddressLine2("a2");
		propertyAddress.setAddressLine3("a3");
		propertyAddress.setCity("city");
		propertyAddress.setState("TN");
		propertyAddress.setCountry("Ind");
		addUserRequest.setPropertyAddress(propertyAddress);
		
		when(userInfromationServiceClient.retrieveUserInfoByLastName(USER2)).thenReturn(addUserRequest);
		when(loanInformationServiceClient.retrieveLoanInfoByEmail(EMAIL)).thenReturn(list);
		
		SearchLoanRequest searchLoanRequest= new SearchLoanRequest();
		searchLoanRequest.setUserLastname(USER2);
		
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
				
				LoanUser addUserRequest= new LoanUser();
				addUserRequest.setUserFirstname(USER2);
				addUserRequest.setUserLastname(USER2);
				Address propertyAddress = new Address();
				propertyAddress.setAddressLine1("a1");
				propertyAddress.setAddressLine2("a2");
				propertyAddress.setAddressLine3("a3");
				propertyAddress.setCity("city");
				propertyAddress.setState("TN");
				propertyAddress.setCountry("Ind");
				addUserRequest.setPropertyAddress(propertyAddress);
				
				when(userInfromationServiceClient.retrieveUserInfoByLastName(USER2)).thenReturn(addUserRequest);
				when(loanInformationServiceClient.retrieveLoanInfoByEmail(EMAIL)).thenReturn(list);
				
				SearchLoanRequest searchLoanRequest= new SearchLoanRequest();
				searchLoanRequest.setUserLastname(USER2);
				Assertions.assertThrows(RestClientException.class,()-> restTemplate.postForEntity(getRootUrl() + PATH, searchLoanRequest, LoanInformation[].class));
			}
	 
	 		@Test
	 		public void testURINotFoundException() {
	 			SearchLoanRequest searchLoanRequest= new SearchLoanRequest();
				searchLoanRequest.setUserLastname(USER2);
				Assertions.assertThrows(RestClientException.class,()-> restTemplate.postForEntity(getRootUrl() + "/search/", searchLoanRequest, LoanInformation[].class));		
	 			
	 		}

}
