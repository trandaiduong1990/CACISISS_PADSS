package org.transinfo.cacis.formbean.collectionmanagement;

import java.util.Date;
import java.util.Map;

import org.apache.struts.validator.ValidatorForm;
import org.transinfo.cacis.dataacess.DAOFactory;
import org.transinfo.cacis.dataacess.dao.collectionmanagement.DelinquencyPolicyDAO;

@SuppressWarnings("serial")
public class DelinquencyNotificationSetupForm extends ValidatorForm {
	private String issuerId;
	private String policyId;
	private String notificationId;
	private String agingSeverity;
	private String thresholdPer;
	private String gracePeriodDays = "0";
	private String minAmt = "0.0";
	private String payByDays = "0";
	private String notificationBtwDays = "0";
	private String notifyReq = "N";
	private String mgmrApprReq = "N";
	private String externalCollectionReq = "N";
	private String lastUpdatedBy;
	private String lastUpdatedDate;
	private String annualPerFee;
	private String flatFee;
	private String status;
	private String userId;
	private String policyName;
	private Map policyList;

	public String getPolicyName() {
		return policyName;
	}

	public void setPolicyName(String policyName) {
		this.policyName = policyName;
	}

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getAnnualPerFee() {
		return annualPerFee;
	}

	public void setAnnualPerFee(String annualPerFee) {
		this.annualPerFee = annualPerFee;
	}

	public String getFlatFee() {
		return flatFee;
	}

	public void setFlatFee(String flatFee) {
		this.flatFee = flatFee;
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

	public String getAgingSeverity() {
		return agingSeverity;
	}

	public void setAgingSeverity(String agingSeverity) {
		this.agingSeverity = agingSeverity;
	}

	public String getThresholdPer() {
		return thresholdPer;
	}

	public void setThresholdPer(String thresholdPer) {
		this.thresholdPer = thresholdPer;
	}

	public String getGracePeriodDays() {
		return gracePeriodDays;
	}

	public void setGracePeriodDays(String gracePeriodDays) {
		this.gracePeriodDays = gracePeriodDays;
	}

	public String getMinAmt() {
		return minAmt;
	}

	public void setMinAmt(String minAmt) {
		this.minAmt = minAmt;
	}

	public String getPayByDays() {
		return payByDays;
	}

	public void setPayByDays(String payByDays) {
		this.payByDays = payByDays;
	}

	public String getNotificationBtwDays() {
		return notificationBtwDays;
	}

	public void setNotificationBtwDays(String notificationBtwDays) {
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

	public String getLastUpdatedDate() {
		return lastUpdatedDate;
	}

	public void setLastUpdatedDate(String lastUpdatedDate) {
		this.lastUpdatedDate = lastUpdatedDate;
	}

	public Map getPolicyList() {
		return policyList;
	}

	public void setPolicyList(Map policyList) {
		this.policyList = policyList;
	}

	public void getPreListData() {
		try {
			DelinquencyPolicyDAO objDAO = DAOFactory.getInstance()
					.getDelinquencyPolicyDAO();

			if (policyList == null) {
				policyList = objDAO.delinquencyPolicyList(issuerId);
			}
		} catch (Exception e) {
			System.out
					.println("Error while getting PreList in DelinquencyNotificationSetupForm:"
							+ e.getMessage());
		}
	}
}
