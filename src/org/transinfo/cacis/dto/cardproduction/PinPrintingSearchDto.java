package org.transinfo.cacis.dto.cardproduction;

import java.io.Serializable;
import java.util.Date;

@SuppressWarnings("serial")
public class PinPrintingSearchDto implements Serializable{
	
	private String issuerId;
	private String fromCardNumber;
	private String toCardNumber; 
	private int cardPrintingStatus;
	private String selectedCards[] ={};
	private String selectedPinPrintSerialNo[] = {};
	private String lastUpdatedBy ="ASPSUPERADMIN";
	private Date updatedDate =new Date();
//	to excell
	private String referenceId;

	private String cardNumber;
	private String startDate;
	private String endDate;

	private String branchId;
	
	public String getReferenceId() {
		return referenceId;
	}


	public void setReferenceId(String referenceId) {
		this.referenceId = referenceId;
	}


	public PinPrintingSearchDto(){}


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


	public int getCardPrintingStatus() {
		return cardPrintingStatus;
	}


	public void setCardPrintingStatus(int cardPrintingStatus) {
		this.cardPrintingStatus = cardPrintingStatus;
	}


	public String getIssuerId() {
		return issuerId;
	}


	public void setIssuerId(String issuerId) {
		this.issuerId = issuerId;
	}


	public String getCardNumber() {
		return cardNumber;
	}


	public void setCardNumber(String cardNumber) {
		this.cardNumber = cardNumber;
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


	public String[] getSelectedPinPrintSerialNo() {
		return selectedPinPrintSerialNo;
	}


	public void setSelectedPinPrintSerialNo(String[] selectedPinPrintSerialNo) {
		this.selectedPinPrintSerialNo = selectedPinPrintSerialNo;
	}


	public String getBranchId() {
		return branchId;
	}


	public void setBranchId(String branchId) {
		this.branchId = branchId;
	}


	

	
}
