package org.transinfo.cacis.dto.batchprocess;

import java.io.Serializable;
import java.util.Date;

@SuppressWarnings("serial")
public class CardBatchDto implements Serializable {
	
	private String batchId;
	private Integer noOfCardsGenerated;
	private String status;
	private String errorMsg;
	private String authorizedBy;
	private Date authorizedDate;
	private String updateddBy;
	private Date updatedDate;
	private String createdBy;
	private Date createdDate;
	private Integer noApplications;
	private Integer rerun;
	
	public String getBatchId() {
		return batchId;
	}
	public void setBatchId(String batchId) {
		this.batchId = batchId;
	}
	public Integer getNoOfCardsGenerated() {
		return noOfCardsGenerated;
	}
	public void setNoOfCardsGenerated(Integer noOfCardsGenerated) {
		this.noOfCardsGenerated = noOfCardsGenerated;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public String getErrorMsg() {
		return errorMsg;
	}
	public void setErrorMsg(String errorMsg) {
		this.errorMsg = errorMsg;
	}
	public String getAuthorizedBy() {
		return authorizedBy;
	}
	public void setAuthorizedBy(String authorizedBy) {
		this.authorizedBy = authorizedBy;
	}
	public Date getAuthorizedDate() {
		return authorizedDate;
	}
	public void setAuthorizedDate(Date authorizedDate) {
		this.authorizedDate = authorizedDate;
	}
	public String getUpdateddBy() {
		return updateddBy;
	}
	public void setUpdateddBy(String updateddBy) {
		this.updateddBy = updateddBy;
	}
	public Date getUpdatedDate() {
		return updatedDate;
	}
	public void setUpdatedDate(Date updatedDate) {
		this.updatedDate = updatedDate;
	}
	public String getCreatedBy() {
		return createdBy;
	}
	public void setCreatedBy(String createdBy) {
		this.createdBy = createdBy;
	}
	public Date getCreatedDate() {
		return createdDate;
	}
	public void setCreatedDate(Date createdDate) {
		this.createdDate = createdDate;
	}
	public Integer getNoApplications() {
		return noApplications;
	}
	public void setNoApplications(Integer noApplications) {
		this.noApplications = noApplications;
	}
	public Integer getRerun() {
		return rerun;
	}
	public void setRerun(Integer rerun) {
		this.rerun = rerun;
	}

}
