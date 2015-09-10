package org.transinfo.cacis.dto.authorization;

import java.io.Serializable;

public class SMSServiceSearchDto implements Serializable {

	private String cardNumber;

	public SMSServiceSearchDto() {
	}

	public String getCardNumber() {
		return cardNumber;
	}

	public void setCardNumber(String cardNumber) {
		this.cardNumber = cardNumber;
	}

}
