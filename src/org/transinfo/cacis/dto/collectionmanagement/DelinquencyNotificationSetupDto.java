package org.transinfo.cacis.dto.collectionmanagement;

import java.io.Serializable;
import java.util.Date;

@SuppressWarnings("serial")
public class DelinquencyNotificationSetupDto implements Serializable {
	private String issuerId;
	private String policyId;
	private String notificationId;
	private Integer agingSeverity;
	private Integer thresholdPer;
	private Integer gracePeriodDays;
	private Integer minAmt;
	private Integer payByDays;
	private Integer notificationBtwDays;
	private String notifyReq;
	private String mgmrApprReq;
	private String externalCollectionReq;
	private String lastUpdatedBy;
	private Date lastUpdatedDate;
	private String status;
	private Integer annualPerFee;
	private Integer flatFee;

	public Integer getAnnualPerFee() {
		return annualPerFee;
	}

	public void setAnnualPerFee(Integer annualPerFee) {
		this.annualPerFee = annualPerFee;
	}

	public Integer getFlatFee() {
		return flatFee;
	}

	public void setFlatFee(Integer flatFee) {
		this.flatFee = flatFee;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getIssuerId() {
		return issuerId;
	}

	public void setIssuerId(String issuerId) {
		this.issuerId = issuerId;
	}

	public String getPolicyId() {
		return policyId;
	}

	public void setPolicyId(String policyId) {
		this.policyId = policyId;
	}

	public String getNotificationId() {
		return notificationId;
	}

	public void setNotificationId(String notificationId) {
		this.notificationId = notificationId;
	}

	public Integer getAgingSeverity() {
		return agingSeverity;
	}

	public void setAgingSeverity(Integer agingSeverity) {
		this.agingSeverity = agingSeverity;
	}

	public Integer getThresholdPer() {
		return thresholdPer;
	}

	public void setThresholdPer(Integer thresholdPer) {
		this.thresholdPer = thresholdPer;
	}

	public Integer getGracePeriodDays() {
		return gracePeriodDays;
	}

	public void setGracePeriodDays(Integer gracePeriodDays) {
		this.gracePeriodDays = gracePeriodDays;
	}

	public Integer getMinAmt() {
		return minAmt;
	}

	public void setMinAmt(Integer minAmt) {
		this.minAmt = minAmt;
	}

	public Integer getPayByDays() {
		return payByDays;
	}

	public void setPayByDays(Integer payByDays) {
		this.payByDays = payByDays;
	}

	public Integer getNotificationBtwDays() {
		return notificationBtwDays;
	}

	public void setNotificationBtwDays(Integer notificationBtwDays) {
		this.notificationBtwDays = notificationBtwDays;
	}

	public String getNotifyReq() {
		return notifyReq;
	}

	public void setNotifyReq(String notifyReq) {
		this.notifyReq = notifyReq;
	}

	public String getMgmrApprReq() {
		return mgmrApprReq;
	}

	public void setMgmrApprReq(String mgmrApprReq) {
		this.mgmrApprReq = mgmrApprReq;
	}

	public String getExternalCollectionReq() {
		return externalCollectionReq;
	}

	public void setExternalCollectionReq(String externalCollectionReq) {
		this.externalCollectionReq = externalCollectionReq;
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
