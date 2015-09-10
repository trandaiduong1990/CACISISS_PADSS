package org.transinfo.cacis.formbean.disputemanagement;

import org.apache.struts.validator.ValidatorForm;

public class SearchRequestWorkItemForm extends ValidatorForm {
	
	
	private static final long serialVersionUID = 1L;
	
	private String cardNumber;
	private String claimDate;
	private String claimNumber;
	
	public SearchRequestWorkItemForm(){}

	public String getCardNumber() {
		return cardNumber;
	}

	public void setCardNumber(String cardNumber) {
		this.cardNumber = cardNumber;
	}

	public String getClaimDate() {
		return claimDate;
	}

	public void setClaimDate(String claimDate) {
		this.claimDate = claimDate;
	}

	public String getClaimNumber() {
		return claimNumber;
	}

	public void setClaimNumber(String claimNumber) {
		this.claimNumber = claimNumber;
	}
	
	
}
