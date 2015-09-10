package org.transinfo.cacis.formbean.accounting;

import org.apache.struts.validator.ValidatorForm;

public class CardBillingForm extends ValidatorForm {
	
	private String issuerId;
	private String userId;
   // private String billingCycleDone;
	private String prevCycleNo;
	private String prevCycleDate;
	private String nextCycleNo;
	private String nextCycleDate;
	private String numberOfAccounts;
	
	
	public CardBillingForm(){}


	public String getIssuerId() {
		return issuerId;
	}


	public void setIssuerId(String issuerId) {
		this.issuerId = issuerId;
	}


	public String getNextCycleDate() {
		return nextCycleDate;
	}


	public void setNextCycleDate(String nextCycleDate) {
		this.nextCycleDate = nextCycleDate;
	}


	public String getNextCycleNo() {
		return nextCycleNo;
	}


	public void setNextCycleNo(String nextCycleNo) {
		this.nextCycleNo = nextCycleNo;
	}


	public String getNumberOfAccounts() {
		return numberOfAccounts;
	}


	public void setNumberOfAccounts(String numberOfAccounts) {
		this.numberOfAccounts = numberOfAccounts;
	}


	public String getPrevCycleDate() {
		return prevCycleDate;
	}


	public void setPrevCycleDate(String prevCycleDate) {
		this.prevCycleDate = prevCycleDate;
	}


	public String getPrevCycleNo() {
		return prevCycleNo;
	}


	public void setPrevCycleNo(String prevCycleNo) {
		this.prevCycleNo = prevCycleNo;
	}


	public String getUserId() {
		return userId;
	}


	public void setUserId(String userId) {
		this.userId = userId;
	}
	
	
}
