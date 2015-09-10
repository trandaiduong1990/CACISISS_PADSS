package org.transinfo.cacis.formbean.csr;
import org.apache.struts.validator.ValidatorForm;

public class CsrForm extends ValidatorForm{
	
	private String cardNumber;
	private String nricNo;
	private String customerName;
	private String issuerId;
	private String userId;
	//for customer tranx hostory
	private String transDateFrom;
	private String transDateTo;

	private String maskedCardNo;
  
	public CsrForm(){}
	
	public String getCardNumber() {
		return cardNumber;
	}

	public void setCardNumber(String cardNumber) {
		this.cardNumber = cardNumber;
	}

	public String getTransDateFrom() {
		return transDateFrom;
	}

	public void setTransDateFrom(String transDateFrom) {
		this.transDateFrom = transDateFrom;
	}

	public String getTransDateTo() {
		return transDateTo;
	}

	public void setTransDateTo(String transDateTo) {
		this.transDateTo = transDateTo;
	}

	public String getIssuerId() {
		return issuerId;
	}

	public void setIssuerId(String issuerId) {
		this.issuerId = issuerId;
	}

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public String getCustomerName() {
		return customerName;
	}

	public void setCustomerName(String customerName) {
		this.customerName = customerName;
	}

	public String getNricNo() {
		return nricNo;
	}

	public void setNricNo(String nricNo) {
		this.nricNo = nricNo;
	}

	public String getMaskedCardNo() {
		return maskedCardNo;
	}

	public void setMaskedCardNo(String maskedCardNo) {
		this.maskedCardNo = maskedCardNo;
	}

	

	

}
