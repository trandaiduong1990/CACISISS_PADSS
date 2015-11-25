package org.transinfo.cacis.formbean.collectionmanagement;

import java.util.Map;

import org.apache.struts.validator.ValidatorForm;

@SuppressWarnings("serial")
public class CollectionConfigForm extends ValidatorForm {
	private String minAmount="0.0";
	private String minDays;
	private String issuerId;
	private String smsNotification;
	private String emailNotification;
	
	public String getMinAmount() {
		return minAmount;
	}
	public void setMinAmount(String minAmount) {
		this.minAmount = minAmount;
	}
	public String getMinDays() {
		return minDays;
	}
	public void setMinDays(String minDays) {
		this.minDays = minDays;
	}
	public String getIssuerId() {
		return issuerId;
	}
	public void setIssuerId(String issuerId) {
		this.issuerId = issuerId;
	}
	public String getSmsNotification() {
		return smsNotification;
	}
	public void setSmsNotification(String smsNotification) {
		this.smsNotification = smsNotification;
	}
	public String getEmailNotification() {
		return emailNotification;
	}
	public void setEmailNotification(String emailNotification) {
		this.emailNotification = emailNotification;
	}

}
