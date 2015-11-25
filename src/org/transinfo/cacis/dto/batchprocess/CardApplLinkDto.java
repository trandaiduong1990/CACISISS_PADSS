package org.transinfo.cacis.dto.batchprocess;

import java.io.Serializable;
import java.util.Date;

@SuppressWarnings("serial")
public class CardApplLinkDto implements Serializable {
	
	private String id;
	private Long cardNumber;
	private String applicationId;
	private int applicationType;
	private String batchId;
	private String updateddBy;
	private Date updatedDate;
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public Long getCardNumber() {
		return cardNumber;
	}
	public void setCardNumber(Long cardNumber) {
		this.cardNumber = cardNumber;
	}
	public String getApplicationId() {
		return applicationId;
	}
	public void setApplicationId(String applicationId) {
		this.applicationId = applicationId;
	}
	public String getBatchId() {
		return batchId;
	}
	public void setBatchId(String batchId) {
		this.batchId = batchId;
	}
	public String getUpdateddBy() {
		return updateddBy;
	}
	public void setUpdateddBy(String updateddBy) {
		this.updateddBy = updateddBy;
	}
	public Date getUpdatedDate() {
		return updatedDate;
	}
	public void setUpdatedDate(Date updatedDate) {
		this.updatedDate = updatedDate;
	}
	public int getApplicationType() {
		return applicationType;
	}
	public void setApplicationType(int applicationType) {
		this.applicationType = applicationType;
	}
	
	
}
