package org.transinfo.cacis.formbean.collectionmanagement;

import java.util.Map;

import org.apache.struts.validator.ValidatorForm;
import org.transinfo.cacis.dataacess.DAOFactory;
import org.transinfo.cacis.dataacess.dao.collectionmanagement.DelinquencyPolicyDAO;

@SuppressWarnings("serial")
public class DelinquencyFeeSetupForm extends ValidatorForm {
	private String issuerId;
	private String policyId;
	private String feeId;
	private String status;
	private String agingBeginDays;
	private String agingEndDays;
	private String gracePeriodDays = "0";
	private String minAmt = "0.0";
	private String startEffectDate;
	private String endEffectDate;
	private String annualPerFee = "0.0";
	private String flatFee = "0.0";
	private String nofityCollectionMgmr = "N";
	private String collectionMgmrReq = "N";
	private String userId;
	private String policyName;
	private Map policyList;

	public String getPolicyName() {
		return policyName;
	}

	public void setPolicyName(String policyName) {
		this.policyName = policyName;
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

	public String getFeeId() {
		return feeId;
	}

	public void setFeeId(String feeId) {
		this.feeId = feeId;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getAgingBeginDays() {
		return agingBeginDays;
	}

	public void setAgingBeginDays(String agingBeginDays) {
		this.agingBeginDays = agingBeginDays;
	}

	public String getAgingEndDays() {
		return agingEndDays;
	}

	public void setAgingEndDays(String agingEndDays) {
		this.agingEndDays = agingEndDays;
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

	public String getStartEffectDate() {
		return startEffectDate;
	}

	public void setStartEffectDate(String startEffectDate) {
		this.startEffectDate = startEffectDate;
	}

	public String getEndEffectDate() {
		return endEffectDate;
	}

	public void setEndEffectDate(String endEffectDate) {
		this.endEffectDate = endEffectDate;
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

	public String getNofityCollectionMgmr() {
		return nofityCollectionMgmr;
	}

	public void setNofityCollectionMgmr(String nofityCollectionMgmr) {
		this.nofityCollectionMgmr = nofityCollectionMgmr;
	}

	public String getCollectionMgmrReq() {
		return collectionMgmrReq;
	}

	public void setCollectionMgmrReq(String collectionMgmrReq) {
		this.collectionMgmrReq = collectionMgmrReq;
	}

	public Map getPolicyList() {
		return policyList;
	}

	public void setPolicyList(Map policyList) {
		this.policyList = policyList;
	}

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
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
					.println("Error while getting PreList in DelinquencyFeeSetupForm:"
							+ e.getMessage());
		}
	}
}
