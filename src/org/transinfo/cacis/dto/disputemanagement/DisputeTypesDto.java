package org.transinfo.cacis.dto.disputemanagement;

import java.io.Serializable;

@SuppressWarnings("serial")
public class DisputeTypesDto implements Serializable {

	private String sno;
	private String description;
	
	public String getSno() {
		return sno;
	}
	public void setSno(String sno) {
		this.sno = sno;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}

}
