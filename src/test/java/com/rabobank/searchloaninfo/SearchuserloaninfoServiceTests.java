package com.rabobank.searchloaninfo;

import static org.junit.jupiter.api.Assertions.assertEquals;

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
import org.springframework.boot.web.server.LocalServerPort;

import com.rabobank.searchloaninfo.SearchLoanInformationApplication;
import com.rabobank.searchloaninfo.controller.BorrowerInfromationServiceClient;
import com.rabobank.searchloaninfo.controller.LoanInformationServiceClient;
import com.rabobank.searchloaninfo.exceptions.LoanInformationNotFoundException;
import com.rabobank.searchloaninfo.model.Address;
import com.rabobank.searchloaninfo.model.Borrower;
import com.rabobank.searchloaninfo.model.LoanInformation;
import com.rabobank.searchloaninfo.services.SearchLoanAndBorrowerInfoServiceImpl;

import static org.mockito.Mockito.*;

@SpringBootTest(classes = {SearchLoanInformationApplication.class},
webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@EnableAutoConfiguration(exclude = { SecurityAutoConfiguration.class, ManagementWebSecurityAutoConfiguration.class })
public class SearchuserloaninfoServiceTests {
	
	
	
	@MockBean()
	LoanInformationServiceClient loanInformationServiceClient;

	@MockBean()
	BorrowerInfromationServiceClient borrowerInfromationServiceClient;
	
	@Autowired
	SearchLoanAndBorrowerInfoServiceImpl searchLoanAndUserInfoService;
	
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
		
		Borrower addUserRequest= new Borrower();
		addUserRequest.setBorrowerFirstname("user2");
		addUserRequest.setBorrowerLastname("user2");
		addUserRequest.setBorrowerEmail("abc@gmail.com");
		Address propertyAddress = new Address();
		propertyAddress.setAddressLine1("a1");
		propertyAddress.setAddressLine2("a2");
		propertyAddress.setAddressLine3("a3");
		propertyAddress.setCity("city");
		propertyAddress.setState("TN");
		propertyAddress.setCountry("Ind");
		addUserRequest.setPropertyAddress(propertyAddress);
		
		when(borrowerInfromationServiceClient.retrieveBorrowerInfoByFirstName("user2")).thenReturn(addUserRequest);
		when(loanInformationServiceClient.retrieveLoanInfoByEmail("abc@gmail.com")).thenReturn(list);
		
		list= searchLoanAndUserInfoService.searchBorrowerEmailByFirstName("user2");
		assertEquals(2, list.size());
		
	}
	
	@Test
	public void searchByLastLoanNumIncorrectTest() throws LoanInformationNotFoundException {
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
		
		Borrower addUserRequest= new Borrower();
		addUserRequest.setBorrowerFirstname("user2");
		addUserRequest.setBorrowerLastname("user2");
		Address propertyAddress = new Address();
		propertyAddress.setAddressLine1("a1");
		propertyAddress.setAddressLine2("a2");
		propertyAddress.setAddressLine3("a3");
		propertyAddress.setCity("city");
		propertyAddress.setState("TN");
		propertyAddress.setCountry("Ind");
		addUserRequest.setPropertyAddress(propertyAddress);
		
		when(borrowerInfromationServiceClient.retrieveBorrowerInfoByLastName("user2")).thenReturn(addUserRequest);
		when(borrowerInfromationServiceClient.retrieveBorrowerInfoByFirstName("user2")).thenReturn(addUserRequest);
		when(loanInformationServiceClient.retrieveLoanInfoByEmail("abc@gmail.com")).thenReturn(list);
//		searchLoanAndBorrowerInfoService.searchuserEmailByLastName("user2");
		
		Assertions.assertThrows(LoanInformationNotFoundException.class,()->searchLoanAndUserInfoService.searchBorrowerEmailByLastName("user2"));
		Assertions.assertThrows(LoanInformationNotFoundException.class,()->searchLoanAndUserInfoService.searchBorrowerEmailByFirstName("user2"));		
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
		
		Borrower addUserRequest= new Borrower();
		addUserRequest.setBorrowerFirstname("user2");
		addUserRequest.setBorrowerLastname("user2");
		addUserRequest.setBorrowerEmail("abc@gmail.com");
		Address propertyAddress = new Address();
		propertyAddress.setAddressLine1("a1");
		propertyAddress.setAddressLine2("a2");
		propertyAddress.setAddressLine3("a3");
		propertyAddress.setCity("city");
		propertyAddress.setState("TN");
		propertyAddress.setCountry("Ind");
		addUserRequest.setPropertyAddress(propertyAddress);
		
		when(borrowerInfromationServiceClient.retrieveBorrowerInfoByLastName("user2")).thenReturn(addUserRequest);
		when(loanInformationServiceClient.retrieveLoanInfoByEmail("abc@gmail.com")).thenReturn(list);
		
		list= searchLoanAndUserInfoService.searchBorrowerEmailByLastName("user2");
	
		assertEquals(2, list.size());
	}

}
