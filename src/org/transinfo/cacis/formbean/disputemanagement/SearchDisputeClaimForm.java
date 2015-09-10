package org.transinfo.cacis.formbean.disputemanagement;

import org.apache.struts.validator.ValidatorForm;

public class SearchDisputeClaimForm extends ValidatorForm {
	
	
	private static final long serialVersionUID = 1L;
	private String cardNumber;
	private String tranxDate;
	private String amount;
	
	public SearchDisputeClaimForm(){}
	
	public String getAmount() {
		return amount;
	}
	public void setAmount(String amount) {
		this.amount = amount;
	}
	public String getCardNumber() {
		return cardNumber;
	}
	public void setCardNumber(String cardNumber) {
		this.cardNumber = cardNumber;
	}
	public String getTranxDate() {
		return tranxDate;
	}
	public void setTranxDate(String tranxDate) {
		this.tranxDate = tranxDate;
	}
}
