package org.transinfo.cacis.formbean.printing;

import org.apache.struts.validator.ValidatorForm;

public class PrintSearchForm extends ValidatorForm {
	private String issuerId;

	public String getIssuerId() {
		return issuerId;
	}

	public void setIssuerId(String issuerId) {
		this.issuerId = issuerId;
	}

}
