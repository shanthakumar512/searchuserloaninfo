package com.rabobank.searchuserloaninfo;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.ArrayList;
import java.util.List;

import org.junit.Test;
import org.junit.jupiter.api.Assertions;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.test.context.junit4.SpringRunner;
import static org.mockito.Mockito.*;

import com.rabobank.searchuserloaninfo.request.LoanInformation;
import com.rabobank.searchuserloaninfo.controller.LoanInformationServiceClient;
import com.rabobank.searchuserloaninfo.controller.UserInfromationServiceClient;
import com.rabobank.searchuserloaninfo.exceptions.LoanInformationNotFoundException;
import com.rabobank.searchuserloaninfo.services.SearchLoanAndUserInfoServiceImpl;
import com.rabobank.searchuserloaninfo.request.Address;
import com.rabobank.searchuserloaninfo.request.LoanUser;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = {SearchuserloaninfoApplication.class},
webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)

public class SearchuserloaninfoServiceTests {
	
	
	
	@MockBean()
	LoanInformationServiceClient loanInformationServiceClient;

	@MockBean()
	UserInfromationServiceClient userInfromationServiceClient;
	
	@Autowired
	SearchLoanAndUserInfoServiceImpl searchLoanAndUserInfoService;
	
	@LocalServerPort
	private int port;
	
	
	@Test
	public void searchByLoanNumTest() {
		
		List<LoanInformation> list= new ArrayList<>();
		
		LoanInformation loanInformation = new LoanInformation();
		loanInformation.setLoanUserEmail("abc@gmail.c");
		loanInformation.setLoanAmount(1234568);
		loanInformation.setLoanNumber("ABC12345");
		loanInformation.setLoanTerm(45);
		loanInformation.setLoanStatus("ACTIVE");
		loanInformation.setLoanMgtFees(7895);
		loanInformation.setOriginationAccount("ACB123476");
		
		LoanInformation loanInformation1 = new LoanInformation();
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
		
		list= searchLoanAndUserInfoService.searchLoanInformationWithLoanNUmber("ABC12345");
		
		assertEquals(list.size(), 1);
		
	}
	
	@Test
	public void searchByFirstLoanNumTest() throws LoanInformationNotFoundException {
		
		List<LoanInformation> list= new ArrayList<>();
		
		LoanInformation loanInformation = new LoanInformation();
		loanInformation.setLoanUserEmail("abc@gmail.com");
		loanInformation.setLoanAmount(1234568);
		loanInformation.setLoanNumber("ABC12345");
		loanInformation.setLoanTerm(45);
		loanInformation.setLoanStatus("ACTIVE");
		loanInformation.setLoanMgtFees(7895);
		loanInformation.setOriginationAccount("ACB123476");
		
		LoanInformation loanInformation1 = new LoanInformation();
		loanInformation1.setLoanUserEmail("abc@gmail.com");
		loanInformation1.setLoanAmount(1234568);
		loanInformation1.setLoanNumber("ABC123");
		loanInformation1.setLoanTerm(45);
		loanInformation1.setLoanStatus("ACTIVE");
		loanInformation1.setLoanMgtFees(7895);
		loanInformation1.setOriginationAccount("ACB123476");
		list.add(loanInformation);
		list.add(loanInformation1);
		
		LoanUser addUserRequest= new LoanUser();
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
		
		list= searchLoanAndUserInfoService.searchUserEmailByFirstName("user2");
		assertEquals(2, list.size());
		
	}
	
	@Test
	public void searchByLastLoanNumIncorrectTest() {
	List<LoanInformation> list= new ArrayList<>();
		
		LoanInformation loanInformation = new LoanInformation();
		loanInformation.setLoanUserEmail("abc@gmail.com");
		loanInformation.setLoanAmount(1234568);
		loanInformation.setLoanNumber("ABC12345");
		loanInformation.setLoanTerm(45);
		loanInformation.setLoanStatus("ACTIVE");
		loanInformation.setLoanMgtFees(7895);
		loanInformation.setOriginationAccount("ACB123476");
		
		LoanInformation loanInformation1 = new LoanInformation();
		loanInformation1.setLoanUserEmail("abc@gmail.com");
		loanInformation1.setLoanAmount(1234568);
		loanInformation1.setLoanNumber("ABC123");
		loanInformation1.setLoanTerm(45);
		loanInformation1.setLoanStatus("ACTIVE");
		loanInformation1.setLoanMgtFees(7895);
		loanInformation1.setOriginationAccount("ACB123476");
		list.add(loanInformation);
		list.add(loanInformation1);
		
		LoanUser addUserRequest= new LoanUser();
		addUserRequest.setUserFirstname("user2");
		addUserRequest.setUserLastname("user2");
		Address propertyAddress = new Address();
		propertyAddress.setAddressLine1("a1");
		propertyAddress.setAddressLine2("a2");
		propertyAddress.setAddressLine3("a3");
		propertyAddress.setCity("city");
		propertyAddress.setState("TN");
		propertyAddress.setCountry("Ind");
		addUserRequest.setPropertyAddress(propertyAddress);
		
		when(userInfromationServiceClient.retrieveUserInfoByLastName("user2")).thenReturn(addUserRequest);
		when(userInfromationServiceClient.retrieveUserInfoByFirstName("user2")).thenReturn(addUserRequest);
		when(loanInformationServiceClient.retrieveLoanInfoByEmail("abc@gmail.com")).thenReturn(list);

		
		Assertions.assertThrows(LoanInformationNotFoundException.class,()->searchLoanAndUserInfoService.searchuserEmailByLastName("user2"));
		Assertions.assertThrows(LoanInformationNotFoundException.class,()->searchLoanAndUserInfoService.searchUserEmailByFirstName("user2"));		
	}
	
	@Test
	public void searchByLastLoanNumTest() throws LoanInformationNotFoundException {
	List<LoanInformation> list= new ArrayList<>();
		
		LoanInformation loanInformation = new LoanInformation();
		loanInformation.setLoanUserEmail("abc@gmail.com");
		loanInformation.setLoanAmount(1234568);
		loanInformation.setLoanNumber("ABC12345");
		loanInformation.setLoanTerm(45);
		loanInformation.setLoanStatus("ACTIVE");
		loanInformation.setLoanMgtFees(7895);
		loanInformation.setOriginationAccount("ACB123476");
		
		LoanInformation loanInformation1 = new LoanInformation();
		loanInformation1.setLoanUserEmail("abc@gmail.com");
		loanInformation1.setLoanAmount(1234568);
		loanInformation1.setLoanNumber("ABC123");
		loanInformation1.setLoanTerm(45);
		loanInformation1.setLoanStatus("ACTIVE");
		loanInformation1.setLoanMgtFees(7895);
		loanInformation1.setOriginationAccount("ACB123476");
		list.add(loanInformation);
		list.add(loanInformation1);
		
		LoanUser addUserRequest= new LoanUser();
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
		
		list= searchLoanAndUserInfoService.searchuserEmailByLastName("user2");
	
		assertEquals(2, list.size());
	}

}
