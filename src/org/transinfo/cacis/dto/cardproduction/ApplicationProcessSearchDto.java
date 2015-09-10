package org.transinfo.cacis.dto.cardproduction;

import java.io.Serializable;

@SuppressWarnings("serial")
public class ApplicationProcessSearchDto implements Serializable {
	
	//private String applicationId;
	private String customerName;
	private String idNumber;
	private String branchId;
	private String issuerId;
	
	public ApplicationProcessSearchDto(){}
	
	public String getCustomerName() {
		return customerName;
	}
	public void setCustomerName(String customerName) {
		this.customerName = customerName;
	}
	public String getIdNumber() {
		return idNumber;
	}
	public void setIdNumber(String idNumber) {
		this.idNumber = idNumber;
	}

	public String getBranchId() {
		return branchId;
	}

	public void setBranchId(String branchId) {
		this.branchId = branchId;
	}

	public String getIssuerId() {
		return issuerId;
	}

	public void setIssuerId(String issuerId) {
		this.issuerId = issuerId;
	}

	
}
