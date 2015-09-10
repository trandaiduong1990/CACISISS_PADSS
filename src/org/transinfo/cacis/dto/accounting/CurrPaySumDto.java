package org.transinfo.cacis.dto.accounting;

import java.io.Serializable;

@SuppressWarnings("serial")
public class CurrPaySumDto implements Serializable {
	
	private Long cardNumber;
	private Double paySum;
	
	public Long getCardNumber() {
		return cardNumber;
	}
	public void setCardNumber(Long cardNumber) {
		this.cardNumber = cardNumber;
	}
	public Double getPaySum() {
		return paySum;
	}
	public void setPaySum(Double paySum) {
		this.paySum = paySum;
	}

}
