
package org.transinfo.cacis.formbean.cardproduction;

import javax.servlet.http.HttpServletRequest;

import org.apache.struts.action.ActionMapping;
import org.apache.struts.validator.ValidatorActionForm;

public class ApplicationProcessSearchForm extends ValidatorActionForm{
	
	
	private static final long serialVersionUID = 1L;
	private String issuerId;
	private String userId;
	private String customerName;
	private String idNumber;
	private String branchId;

	public ApplicationProcessSearchForm(){}
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
	public String getIssuerId() {
		return issuerId;
	}
	public void setIssuerId(String issuerId) {
		this.issuerId = issuerId;
	}
	public String getUserId() {
		return userId;
	}
	public void setUserId(String userId) {
		this.userId = userId;
	}
	
	@Override
	public void reset(ActionMapping mapping, HttpServletRequest request) {
		customerName = null;
		idNumber = null;
	}
	public String getBranchId() {
		return branchId;
	}
	public void setBranchId(String branchId) {
		this.branchId = branchId;
	}
}
