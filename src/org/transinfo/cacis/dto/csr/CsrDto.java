package org.transinfo.cacis.dto.csr;

import java.io.Serializable;

public class CsrDto implements Serializable {

	
	private long cardNumber;
	private String issuerId;
	private String nricNo;
	private String customerName;
	
	private String transDateFrom;
	private String transDateTo;
	private String userId;

	private String maskedCardNo;
	
	public CsrDto(){}

	public long getCardNumber() {
		return cardNumber;
	}

	public void setCardNumber(long cardNumber) {
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
