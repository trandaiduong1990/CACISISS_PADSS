package org.transinfo.cacis.dto.disputemanagement;

import java.io.Serializable;

public class SearchWorkItemDto implements Serializable{
	
	
	private static final long serialVersionUID = 1L;
	private long cardNumber;
	private String claimDate;
	private String claimNumber;
	
	public  SearchWorkItemDto(){}

	public long getCardNumber() {
		return cardNumber;
	}

	public void setCardNumber(long cardNumber) {
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
