package org.transinfo.cacis.dto.riskmanagement;

import java.io.Serializable;

public class CardholderLimitAdjustmentSearchDto implements Serializable {

	private long cardNumber;

	public long getCardNumber() {
		return new Long(cardNumber).longValue();
	}

	public void setCardNumber(long cardNumber) {
		this.cardNumber = cardNumber;
	}

	public CardholderLimitAdjustmentSearchDto(){}

}
