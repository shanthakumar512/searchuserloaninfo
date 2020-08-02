package com.rabobank.searchloaninfo.model;
/**
 * 
 * @author Shanthakumar
 *
 */

public class SearchLoanRequest {

	private String borrowerFirstname;
	
	private String borrowerLastname;
	
	private String loanNumber;
	
	/**
	 * @return the borrowerFirstname
	 */
	public String getBorrowerFirstname() {
		return borrowerFirstname;
	}
	/**
	 * @param borrowerLastname the borrowerLastname to set
	 */
	public void setBorrowerLastname(String userLastname) {
		this.borrowerLastname = userLastname;
	}
	/**
	 * @param borrowerFirstname the borrowerFirstname to set
	 */
	public void setBorrowerFirstname(String userFirstname) {
		this.borrowerFirstname = userFirstname;
	}
	/**
	 * @return the borrowerLastname
	 */
	public String getBorrowerLastname() {
		return borrowerLastname;
	}
	/**
	 * @return the loanNumber
	 */
	public String getLoanNumber() {
		return loanNumber;
	}
	/**
	 * @param loanNumber the loanNumber to set
	 */
	public void setLoanNumber(String loanNumber) {
		this.loanNumber = loanNumber;
	}
	
}
