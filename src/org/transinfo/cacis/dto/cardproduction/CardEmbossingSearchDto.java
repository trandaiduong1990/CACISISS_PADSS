package org.transinfo.cacis.dto.cardproduction;

import java.io.Serializable;
import java.util.Date;

@SuppressWarnings("serial")
public class CardEmbossingSearchDto implements Serializable{
	
	private String fromCardNumber;
	private String toCardNumber; 
	private int cardEmbossingStaus;
	private String selectedCards[] ={};
	private String selectedEmbossSerialNos[] = {};
	private String lastUpdatedBy ="ASPSUPERADMIN";
	private Date updatedDate =new Date();

	private String cardNumber;
	private String startDate;
	private String endDate;
	
//	to excell
	private String referenceId;
	
	private String branchId;
	private String issuerId;
	
	private String cardproduct;
	
	public CardEmbossingSearchDto(){}

	public int getCardEmbossingStaus() {
		return cardEmbossingStaus;
	}

	public void setCardEmbossingStaus(int cardEmbossingStaus) {
		this.cardEmbossingStaus = cardEmbossingStaus;
	}

	public String getFromCardNumber() {
		return fromCardNumber;
	}

	public void setFromCardNumber(String fromCardNumber) {
		this.fromCardNumber = fromCardNumber;
	}

	public String getToCardNumber() {
		return toCardNumber;
	}

	public void setToCardNumber(String toCardNumber) {
		this.toCardNumber = toCardNumber;
	}

	public String[] getSelectedCards() {
		return selectedCards;
	}

	public void setSelectedCards(String[] selectedCards) {
		this.selectedCards = selectedCards;
	}

	public String getLastUpdatedBy() {
		return lastUpdatedBy;
	}

	public void setLastUpdatedBy(String lastUpdatedBy) {
		this.lastUpdatedBy = lastUpdatedBy;
	}

	public Date getUpdatedDate() {
		return updatedDate;
	}

	public void setUpdatedDate(Date updatedDate) {
		this.updatedDate = updatedDate;
	}

	public String getReferenceId() {
		return referenceId;
	}

	public void setReferenceId(String referenceId) {
		this.referenceId = referenceId;
	}

	public String getStartDate() {
		return startDate;
	}

	public void setStartDate(String startDate) {
		this.startDate = startDate;
	}

	public String getEndDate() {
		return endDate;
	}

	public void setEndDate(String endDate) {
		this.endDate = endDate;
	}

	public String getCardNumber() {
		return cardNumber;
	}

	public void setCardNumber(String cardNumber) {
		this.cardNumber = cardNumber;
	}

	public String[] getSelectedEmbossSerialNos() {
		return selectedEmbossSerialNos;
	}

	public void setSelectedEmbossSerialNos(String[] selectedEmbossSerialNos) {
		this.selectedEmbossSerialNos = selectedEmbossSerialNos;
	}

	public String getBranchId() {
		return branchId;
	}

	public void setBranchId(String branchId) {
		this.branchId = branchId;
	}

	public String getIssuerId() {
		return issuerId;
	}

	public void setIssuerId(String issuerId) {
		this.issuerId = issuerId;
	}

	public String getCardproduct() {
		return cardproduct;
	}

	public void setCardproduct(String cardproduct) {
		this.cardproduct = cardproduct;
	}

	
}
