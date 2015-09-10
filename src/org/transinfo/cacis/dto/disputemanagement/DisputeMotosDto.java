package org.transinfo.cacis.dto.disputemanagement;

import java.io.Serializable;

@SuppressWarnings("serial")
public class DisputeMotosDto implements Serializable {

	private String motoId;
	private String motoCode;
	private String motoDescription;
	
	public String getMotoId() {
		return motoId;
	}
	public void setMotoId(String motoId) {
		this.motoId = motoId;
	}
	public String getMotoCode() {
		return motoCode;
	}
	public void setMotoCode(String motoCode) {
		this.motoCode = motoCode;
	}
	public String getMotoDescription() {
		return motoDescription;
	}
	public void setMotoDescription(String motoDescription) {
		this.motoDescription = motoDescription;
	}
	
}
