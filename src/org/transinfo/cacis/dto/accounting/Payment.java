package org.transinfo.cacis.dto.accounting;

import java.io.Serializable;

@SuppressWarnings("serial")
public class Payment implements Serializable {
	
	private String payMode;
	private String payDesc;
	
	public String getPayMode() {
		return payMode;
	}
	public void setPayMode(String payMode) {
		this.payMode = payMode;
	}
	public String getPayDesc() {
		return payDesc;
	}
	public void setPayDesc(String payDesc) {
		this.payDesc = payDesc;
	}

}
