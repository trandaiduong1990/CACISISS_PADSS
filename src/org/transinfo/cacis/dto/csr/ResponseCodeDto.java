package org.transinfo.cacis.dto.csr;

import java.io.Serializable;

@SuppressWarnings("serial")
public class ResponseCodeDto implements Serializable {

	private String responseCode;
	private String reason;

	public String getResponseCode() {
		return responseCode;
	}

	public void setResponseCode(String responseCode) {
		this.responseCode = responseCode;
	}

	public String getReason() {
		return reason;
	}

	public void setReason(String reason) {
		this.reason = reason;
	}

}
