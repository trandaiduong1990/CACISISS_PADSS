package org.transinfo.cacis.dto.common;

import java.io.Serializable;
import java.util.Date;

public class CardProcessStatusDto implements Serializable{
	
	public CardProcessStatusDto(){}
	
	private int statusId;
	private String descriptin;
	private String lastUpdatedBy ="ASPSUPERADMIN";
	private Date  updatedDate =new Date();
	
	
	public String getDescriptin() {
		return descriptin;
	}
	public void setDescriptin(String descriptin) {
		this.descriptin = descriptin;
	}
	public String getLastUpdatedBy() {
		return lastUpdatedBy;
	}
	public void setLastUpdatedBy(String lastUpdatedBy) {
		this.lastUpdatedBy = lastUpdatedBy;
	}
	public int getStatusId() {
		return statusId;
	}
	public void setStatusId(int statusId) {
		this.statusId = statusId;
	}
	public Date getUpdatedDate() {
		return updatedDate;
	}
	public void setUpdatedDate(Date updatedDate) {
		this.updatedDate = updatedDate;
	}
	

}
