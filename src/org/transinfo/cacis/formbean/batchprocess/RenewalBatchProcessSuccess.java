package org.transinfo.cacis.formbean.batchprocess;

public class RenewalBatchProcessSuccess {

	private String applicationId;
	private String customerName;
	private String oldCardNumber;
	private String newCardNumber;
	private String reason;
	private String appliedDate;
	
	public String getApplicationId() {
		return applicationId;
	}
	public void setApplicationId(String applicationId) {
		this.applicationId = applicationId;
	}
	public String getCustomerName() {
		return customerName;
	}
	public void setCustomerName(String customerName) {
		this.customerName = customerName;
	}
	public String getOldCardNumber() {
		return oldCardNumber;
	}
	public void setOldCardNumber(String oldCardNumber) {
		this.oldCardNumber = oldCardNumber;
	}
	public String getNewCardNumber() {
		return newCardNumber;
	}
	public void setNewCardNumber(String newCardNumber) {
		this.newCardNumber = newCardNumber;
	}
	public String getReason() {
		return reason;
	}
	public void setReason(String reason) {
		this.reason = reason;
	}
	public String getAppliedDate() {
		return appliedDate;
	}
	public void setAppliedDate(String appliedDate) {
		this.appliedDate = appliedDate;
	}

}
