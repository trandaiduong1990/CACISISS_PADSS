package org.transinfo.cacis.dto.applicationforms;

import java.io.Serializable;

public class CardGradeFormSearchDto implements Serializable {

	private String issuerId;
	private long cardNumber;
    

	public CardGradeFormSearchDto(){}


	public long getCardNumber() {
		return cardNumber;
	}


	public void setCardNumber(long cardNumber) {
		this.cardNumber = cardNumber;
	}


	public String getIssuerId() {
		return issuerId;
	}


	public void setIssuerId(String issuerId) {
		this.issuerId = issuerId;
	}

   


}
