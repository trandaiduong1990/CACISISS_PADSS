package org.transinfo.cacis.dto.settings;

import java.io.Serializable;

public class CardProductSearchDto implements Serializable {

	private String cardProductId;
	private String issuerId;

	public String getCardProductId() {
		return cardProductId;
	}

	public void setCardProductId(String cardProductId) {
		this.cardProductId = cardProductId;
	}

	public String getIssuerId() {
		return issuerId;
	}

	public void setIssuerId(String issuerId) {
		this.issuerId = issuerId;
	}

}