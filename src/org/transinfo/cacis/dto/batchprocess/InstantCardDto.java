package org.transinfo.cacis.dto.batchprocess;

import java.io.Serializable;
import java.util.Date;

@SuppressWarnings("serial")
public class InstantCardDto implements Serializable {
	private String issuerId;
	private String batchId;
	private String batchName;
	private String cardProductId;
	private String branchId;
	private Integer noOfCard;
	private Integer noOfCardsGenerated;
	private String status;
	private String errorMsg;
	private String authorizedBy;
	private Date authorizedDate;
	private String updatedBy;
	private Date updatedDate;
	private String createdBy;
	private Date createdDate;
	
	public String getBranchId() {
		return branchId;
	}

	public void setBranchId(String branchId) {
		this.branchId = branchId;
	}

	public String getBatchId() {
		return batchId;
	}

	public void setBatchId(String batchId) {
		this.batchId = batchId;
	}

	public String getBatchName() {
		return batchName;
	}

	public void setBatchName(String batchName) {
		this.batchName = batchName;
	}

	public String getCardProductId() {
		return cardProductId;
	}

	public void setCardProductId(String cardProductId) {
		this.cardProductId = cardProductId;
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

	public String getUpdatedBy() {
		return updatedBy;
	}

	public void setUpdatedBy(String updatedBy) {
		this.updatedBy = updatedBy;
	}

	public Date getUpdatedDate() {
		return updatedDate;
	}

	public void setUpdatedDate(Date updatedDate) {
		this.updatedDate = updatedDate;
	}

	public String getIssuerId() {
		return issuerId;
	}

	public void setIssuerId(String issuerId) {
		this.issuerId = issuerId;
	}

	public Integer getNoOfCard() {
		return noOfCard;
	}

	public void setNoOfCard(Integer noOfCard) {
		this.noOfCard = noOfCard;
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
	
}
