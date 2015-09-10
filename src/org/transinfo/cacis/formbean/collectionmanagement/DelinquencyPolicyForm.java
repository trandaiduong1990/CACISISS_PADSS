package org.transinfo.cacis.formbean.collectionmanagement;

import java.util.Map;

import org.apache.struts.validator.ValidatorForm;

@SuppressWarnings("serial")
public class DelinquencyPolicyForm extends ValidatorForm {
	private String policyId;
	private String description;
	private String userId;
	private String issuerId;
	private String status;
	
	private Map policyList;

	public String getPolicyId() {
		return policyId;
	}

	public void setPolicyId(String policyId) {
		this.policyId = policyId;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public String getIssuerId() {
		return issuerId;
	}

	public void setIssuerId(String issuerId) {
		this.issuerId = issuerId;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public Map getPolicyList() {
		return policyList;
	}

	public void setPolicyList(Map policyList) {
		this.policyList = policyList;
	}

}
