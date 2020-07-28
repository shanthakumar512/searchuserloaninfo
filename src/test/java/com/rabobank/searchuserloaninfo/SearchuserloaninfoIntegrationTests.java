package com.rabobank.searchuserloaninfo;

import static org.junit.Assert.assertNotNull;

import java.util.ArrayList;
import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringRunner;
import static org.mockito.Mockito.*;

import com.rabobank.searchuserloaninfo.request.LoanInformationRequest;
import com.rabobank.searchuserloaninfo.controller.LoanInformationServiceClient;
import com.rabobank.searchuserloaninfo.controller.UserInfromationServiceClient;
import com.rabobank.searchuserloaninfo.request.SearchLoanRequest;
import com.rabobank.searchuserloaninfo.services.SearchLoanAndUserInfoServiceImpl;
import com.rabobank.searchuserloaninfo.request.Address;
import com.rabobank.searchuserloaninfo.request.LoanUserRequest;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = {SearchuserloaninfoApplication.class},
webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)

public class SearchuserloaninfoIntegrationTests {
	
	
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
		
		List<LoanInformationRequest> list= new ArrayList<>();
		
		LoanInformationRequest loanInformation = new LoanInformationRequest();
		loanInformation.setLoanUserEmail("abc@gmail.c");
		loanInformation.setLoanAmount(1234568);
		loanInformation.setLoanNumber("ABC12345");
		loanInformation.setLoanTerm(45);
		loanInformation.setLoanStatus("ACTIVE");
		loanInformation.setLoanMgtFees(7895);
		loanInformation.setOriginationAccount("ACB123476");
		
		LoanInformationRequest loanInformation1 = new LoanInformationRequest();
		loanInformation1.setLoanUserEmail("abc@gmail.c");
		loanInformation1.setLoanAmount(1234568);
		loanInformation1.setLoanNumber("ABC123");
		loanInformation1.setLoanTerm(45);
		loanInformation1.setLoanStatus("ACTIVE");
		loanInformation1.setLoanMgtFees(7895);
		loanInformation1.setOriginationAccount("ACB123476");
		list.add(loanInformation);
		list.add(loanInformation1);

		
		when(loanInformationServiceClient.retrieveLoanInfo("ABC12345")).thenReturn(loanInformation);
		
		SearchLoanRequest searchLoanRequest= new SearchLoanRequest();
		searchLoanRequest.setLoanNumber("ABC12345");
		
		ResponseEntity<LoanInformationRequest[]> updatedEmployee = restTemplate.postForEntity(getRootUrl() + "/searchinfo/loanInformation", searchLoanRequest, LoanInformationRequest[].class);
		assertNotNull(updatedEmployee.getBody());
	}
	
	@Test
	public void searchByFirstLoanNumTest() {
		
		List<LoanInformationRequest> list= new ArrayList<>();
		
		LoanInformationRequest loanInformation = new LoanInformationRequest();
		loanInformation.setLoanUserEmail("abc@gmail.com");
		loanInformation.setLoanAmount(1234568);
		loanInformation.setLoanNumber("ABC12345");
		loanInformation.setLoanTerm(45);
		loanInformation.setLoanStatus("ACTIVE");
		loanInformation.setLoanMgtFees(7895);
		loanInformation.setOriginationAccount("ACB123476");
		
		LoanInformationRequest loanInformation1 = new LoanInformationRequest();
		loanInformation1.setLoanUserEmail("abc@gmail.com");
		loanInformation1.setLoanAmount(1234568);
		loanInformation1.setLoanNumber("ABC123");
		loanInformation1.setLoanTerm(45);
		loanInformation1.setLoanStatus("ACTIVE");
		loanInformation1.setLoanMgtFees(7895);
		loanInformation1.setOriginationAccount("ACB123476");
		list.add(loanInformation);
		list.add(loanInformation1);
		
		LoanUserRequest addUserRequest= new LoanUserRequest();
		addUserRequest.setUserFirstname("user2");
		addUserRequest.setUserLastname("user2");
		addUserRequest.setUserEmail("abc@gmail.com");
		Address propertyAddress = new Address();
		propertyAddress.setAddressLine1("a1");
		propertyAddress.setAddressLine2("a2");
		propertyAddress.setAddressLine3("a3");
		propertyAddress.setCity("city");
		propertyAddress.setState("TN");
		propertyAddress.setCountry("Ind");
		addUserRequest.setPropertyAddress(propertyAddress);
		
		when(userInfromationServiceClient.retrieveUserInfoByFirstName("user2")).thenReturn(addUserRequest);
		when(loanInformationServiceClient.retrieveLoanInfoByEmail("abc@gmail.com")).thenReturn(list);
		
		SearchLoanRequest searchLoanRequest= new SearchLoanRequest();
		searchLoanRequest.setUserFirstname("user2");
		
		ResponseEntity<LoanInformationRequest[]> updatedEmployee = restTemplate.postForEntity(getRootUrl() + "/searchinfo/loanInformation", searchLoanRequest, LoanInformationRequest[].class);
		assertNotNull(updatedEmployee.getBody());
	}
	
	@Test
	public void searchByLastLoanNumTest() {
	List<LoanInformationRequest> list= new ArrayList<>();
		
		LoanInformationRequest loanInformation = new LoanInformationRequest();
		loanInformation.setLoanUserEmail("abc@gmail.com");
		loanInformation.setLoanAmount(1234568);
		loanInformation.setLoanNumber("ABC12345");
		loanInformation.setLoanTerm(45);
		loanInformation.setLoanStatus("ACTIVE");
		loanInformation.setLoanMgtFees(7895);
		loanInformation.setOriginationAccount("ACB123476");
		
		LoanInformationRequest loanInformation1 = new LoanInformationRequest();
		loanInformation1.setLoanUserEmail("abc@gmail.com");
		loanInformation1.setLoanAmount(1234568);
		loanInformation1.setLoanNumber("ABC123");
		loanInformation1.setLoanTerm(45);
		loanInformation1.setLoanStatus("ACTIVE");
		loanInformation1.setLoanMgtFees(7895);
		loanInformation1.setOriginationAccount("ACB123476");
		list.add(loanInformation);
		list.add(loanInformation1);
		
		LoanUserRequest addUserRequest= new LoanUserRequest();
		addUserRequest.setUserFirstname("user2");
		addUserRequest.setUserLastname("user2");
		addUserRequest.setUserEmail("abc@gmail.com");
		Address propertyAddress = new Address();
		propertyAddress.setAddressLine1("a1");
		propertyAddress.setAddressLine2("a2");
		propertyAddress.setAddressLine3("a3");
		propertyAddress.setCity("city");
		propertyAddress.setState("TN");
		propertyAddress.setCountry("Ind");
		addUserRequest.setPropertyAddress(propertyAddress);
		
		when(userInfromationServiceClient.retrieveUserInfoByLastName("user2")).thenReturn(addUserRequest);
		when(loanInformationServiceClient.retrieveLoanInfoByEmail("abc@gmail.com")).thenReturn(list);
		
		SearchLoanRequest searchLoanRequest= new SearchLoanRequest();
		searchLoanRequest.setUserLastname("user2");
		
		ResponseEntity<LoanInformationRequest[]> updatedEmployee = restTemplate.postForEntity(getRootUrl() + "/searchinfo/loanInformation", searchLoanRequest, LoanInformationRequest[].class);
		assertNotNull(updatedEmployee.getBody());
	}
}
