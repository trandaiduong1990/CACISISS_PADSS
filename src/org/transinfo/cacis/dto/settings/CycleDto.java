package org.transinfo.cacis.dto.settings;

import java.io.Serializable;
import java.util.Date;

public class CycleDto implements Serializable {
	
	
	 private static final long serialVersionUID = 1L;
	 private String issuerId;
	 private int    cycleNo;
	 private int    dayOfMonth;
	 private String status;
	 private  Date  lastBillingDate;
	 private  Date  updatedDate = new Date();
	 private String  userId;
	 
	 public CycleDto(){}
	 
	public int getCycleNo() {
		return cycleNo;
	}
	public void setCycleNo(int cycleNo) {
		this.cycleNo = cycleNo;
	}
	public int getDayOfMonth() {
		return dayOfMonth;
	}
	public void setDayOfMonth(int dayOfMonth) {
		this.dayOfMonth = dayOfMonth;
	}
	public String getIssuerId() {
		return issuerId;
	}
	public void setIssuerId(String issuerId) {
		this.issuerId = issuerId;
	}
	public Date getLastBillingDate() {
		return lastBillingDate;
	}
	public void setLastBillingDate(Date lastBillingDate) {
		this.lastBillingDate = lastBillingDate;
	}
	

	public Date getUpdatedDate() {
		return updatedDate;
	}

	public void setUpdatedDate(Date updatedDate) {
		this.updatedDate = updatedDate;
	}

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	


}
