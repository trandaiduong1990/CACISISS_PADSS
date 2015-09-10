package org.transinfo.cacis.formbean.disputemanagement;

import org.apache.struts.validator.ValidatorForm;

public class StatisticReportsSearchForm extends ValidatorForm {

	private String strStartDate;

	private String strEndDate;

	private String issuerId;

	private String agency;

	public String getAgency() {
		return agency;
	}

	public void setAgency(String agency) {
		this.agency = agency;
	}

	public String getIssuerId() {
		return issuerId;
	}

	public void setIssuerId(String issuerId) {
		this.issuerId = issuerId;
	}

	public String getStrEndDate() {
		return strEndDate;
	}

	public void setStrEndDate(String strEndDate) {
		this.strEndDate = strEndDate;
	}

	public String getStrStartDate() {
		return strStartDate;
	}

	public void setStrStartDate(String strStartDate) {
		this.strStartDate = strStartDate;
	}

}
