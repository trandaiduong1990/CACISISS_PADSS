package org.transinfo.cacis.dto.disputemanagement;

import java.io.Serializable;

@SuppressWarnings("serial")
public class Base2TranxCodeDto implements Serializable {

	private String tranxCode;
	private String description;

	public String getTranxCode() {
		return tranxCode;
	}

	public void setTranxCode(String tranxCode) {
		this.tranxCode = tranxCode;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

}
