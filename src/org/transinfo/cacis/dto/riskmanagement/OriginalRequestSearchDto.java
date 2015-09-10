package org.transinfo.cacis.dto.riskmanagement;

import java.io.Serializable;

public class OriginalRequestSearchDto implements Serializable {

	private String cardNumber;

	private String strTranxDate;

	private String refNumber;

	public OriginalRequestSearchDto() {
	}

	public String getCardNumber() {
		return cardNumber;
	}

	public void setCardNumber(String cardNumber) {
		this.cardNumber = cardNumber;
	}

	public String getRefNumber() {
		return refNumber;
	}

	public void setRefNumber(String refNumber) {
		this.refNumber = refNumber;
	}

	public String getStrTranxDate() {
		return strTranxDate;
	}

	public void setStrTranxDate(String strTranxDate) {
		this.strTranxDate = strTranxDate;
	}

}
