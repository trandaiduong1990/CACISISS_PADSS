package org.transinfo.cacis.dto.common;

import java.io.Serializable;
import java.sql.Timestamp;

@SuppressWarnings("serial")
public class CardStatusDto implements Serializable{
	
	private long cardStatusId;
	private String issuerId;
	private String description;
	private String response;
	private String lastUpdatedBy;
	private Timestamp timestamp;
	
	public CardStatusDto(){}
	
	public Timestamp getTimestamp() {
		return timestamp;
	}
	public void setTimestamp(Timestamp timestamp) {
		this.timestamp = timestamp;
	}
	
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public String getLastUpdatedBy() {
		return lastUpdatedBy;
	}
	public void setLastUpdatedBy(String lastUpdatedBy) {
		this.lastUpdatedBy = lastUpdatedBy;
	}

	public long getCardStatusId() {
		return cardStatusId;
	}

	public void setCardStatusId(long cardStatusId) {
		this.cardStatusId = cardStatusId;
	}

	public String getIssuerId() {
		return issuerId;
	}

	public void setIssuerId(String issuerId) {
		this.issuerId = issuerId;
	}

	public String getResponse() {
		return response;
	}

	public void setResponse(String response) {
		this.response = response;
	}

}
