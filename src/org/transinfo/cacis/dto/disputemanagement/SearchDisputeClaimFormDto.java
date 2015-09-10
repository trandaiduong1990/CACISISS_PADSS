package org.transinfo.cacis.dto.disputemanagement;

import java.io.Serializable;

public class SearchDisputeClaimFormDto implements Serializable{
	

	private static final long serialVersionUID = 1L;
	private long cardNumber;
	private double amount;
	private String tranxDate;
	
	public SearchDisputeClaimFormDto(){}
	
	public double getAmount() {
		return amount;
	}
	public void setAmount(double amount) {
		this.amount = amount;
	}
	public long getCardNumber() {
		return cardNumber;
	}
	public void setCardNumber(long cardNumber) {
		this.cardNumber = cardNumber;
	}

	public String getTranxDate() {
		return tranxDate;
	}

	public void setTranxDate(String tranxDate) {
		this.tranxDate = tranxDate;
	}
	

}
