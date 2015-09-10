package org.transinfo.cacis.dto.customerservice;

import java.io.Serializable;
import java.util.Date;

@SuppressWarnings("serial")
public class CardStatusRemarksDto implements Serializable {

	private String statusRemarksId;;
	private long statusNo;
	private String cardNo;
	private String remarks;
	private Date updatedDate = new Date();;
	private String userId;
	
	public String getStatusRemarksId() {
		return statusRemarksId;
	}
	public void setStatusRemarksId(String statusRemarksId) {
		this.statusRemarksId = statusRemarksId;
	}
	public long getStatusNo() {
		return statusNo;
	}
	public void setStatusNo(long statusNo) {
		this.statusNo = statusNo;
	}
	public String getCardNo() {
		return cardNo;
	}
	public void setCardNo(String cardNo) {
		this.cardNo = cardNo;
	}
	public String getRemarks() {
		return remarks;
	}
	public void setRemarks(String remarks) {
		this.remarks = remarks;
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
	
}
