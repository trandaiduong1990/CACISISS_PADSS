package org.transinfo.cacis.dto.disputemanagement;

import java.io.Serializable;

public class StatisticReportsSearchDto implements Serializable {

	private String strStartDate;

	private String strEndDate;

	private String issuerId;

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
