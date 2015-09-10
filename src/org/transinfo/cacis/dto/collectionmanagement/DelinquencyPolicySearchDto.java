package org.transinfo.cacis.dto.collectionmanagement;

import java.io.Serializable;
import java.util.Map;

@SuppressWarnings("serial")
public class DelinquencyPolicySearchDto implements Serializable {
	private String policyId;
	private String issuerId;
	Map policyList;

	public String getPolicyId() {
		return policyId;
	}

	public void setPolicyId(String policyId) {
		this.policyId = policyId;
	}

	public Map getPolicyList() {
		return policyList;
	}

	public void setPolicyList(Map policyList) {
		this.policyList = policyList;
	}

	public String getIssuerId() {
		return issuerId;
	}

	public void setIssuerId(String issuerId) {
		this.issuerId = issuerId;
	}

}