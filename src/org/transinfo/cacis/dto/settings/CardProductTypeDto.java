package org.transinfo.cacis.dto.settings;

import java.io.Serializable;
import java.sql.Timestamp;

@SuppressWarnings("serial")
public class CardProductTypeDto implements Serializable {

	//private long cardProductTypeId;
	private String cardProductTypeId;
	private String cardProductType;
	private Timestamp timestamp;
	private String lastUpdatedBy;

	public CardProductTypeDto() {
	}

	public String getCardProductType() {
		return cardProductType;
	}

	public void setCardProductType(String cardProductType) {
		this.cardProductType = cardProductType;
	}

	public String getLastUpdatedBy() {
		return lastUpdatedBy;
	}

	public void setLastUpdatedBy(String lastUpdatedBy) {
		this.lastUpdatedBy = lastUpdatedBy;
	}

	public Timestamp getTimestamp() {
		return timestamp;
	}

	public void setTimestamp(Timestamp timestamp) {
		this.timestamp = timestamp;
	}

	public String getCardProductTypeId() {
		return cardProductTypeId;
	}

	public void setCardProductTypeId(String cardProductTypeId) {
		this.cardProductTypeId = cardProductTypeId;
	}

}
