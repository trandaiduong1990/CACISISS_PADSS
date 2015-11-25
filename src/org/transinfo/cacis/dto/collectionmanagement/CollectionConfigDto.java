package org.transinfo.cacis.dto.collectionmanagement;

import java.io.Serializable;
import java.util.Date;

@SuppressWarnings("serial")
public class CollectionConfigDto implements Serializable {
	private String issuerId;
	private Integer minAmount;
	private Integer minDays;
	private String emailNotification;
	private String smsNotification;
	private String lastUpdatedBy;
	private Date lastUpdatedDate;
	public String getIssuerId() {
		return issuerId;
	}
	public void setIssuerId(String issuerId) {
		this.issuerId = issuerId;
	}
	public Integer getMinAmount() {
		return minAmount;
	}
	public void setMinAmount(Integer minAmount) {
		this.minAmount = minAmount;
	}
	public Integer getMinDays() {
		return minDays;
	}
	public void setMinDays(Integer minDays) {
		this.minDays = minDays;
	}
	public String getEmailNotification() {
		return emailNotification;
	}
	public void setEmailNotification(String emailNotification) {
		this.emailNotification = emailNotification;
	}
	public String getSmsNotification() {
		return smsNotification;
	}
	public void setSmsNotification(String smsNotification) {
		this.smsNotification = smsNotification;
	}
	public String getLastUpdatedBy() {
		return lastUpdatedBy;
	}
	public void setLastUpdatedBy(String lastUpdatedBy) {
		this.lastUpdatedBy = lastUpdatedBy;
	}
	public Date getLastUpdatedDate() {
		return lastUpdatedDate;
	}
	public void setLastUpdatedDate(Date lastUpdatedDate) {
		this.lastUpdatedDate = lastUpdatedDate;
	}

}
