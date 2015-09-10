package org.transinfo.cacis.dto.csr;

import java.io.Serializable;

public class CallTypeDto implements Serializable{
	
	
	private int callTypeId;
	private String callType;
	
	public CallTypeDto(){}

	public String getCallType() {
		return callType;
	}

	public void setCallType(String callType) {
		this.callType = callType;
	}

	public int getCallTypeId() {
		return callTypeId;
	}

	public void setCallTypeId(int callTypeId) {
		this.callTypeId = callTypeId;
	}
	
	

}
