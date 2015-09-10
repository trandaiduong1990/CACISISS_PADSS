package org.transinfo.cacis.dto.settings;

import java.io.Serializable;
import java.util.Date;

@SuppressWarnings("serial")
public class CardProductFeeDto implements Serializable {

	private int id;
	private String cardProductId;
	private int annualFeePrimary;
	private int annualFeeSecondary;
	private int issuerFee;
	private int replacementFee;
	private int cancelationFee;
	private int currConversionFee;
	private int adminstrationFee;
	private Date startDt;
	private Date endDt;
	private String displayStatus;
	private Date updatedDate = new Date();;
	private String userId;
	
	private Integer joinFeePrimary;
	private Integer joinFeeSecondary;
	private Integer creditAdjFeePrimary;
	private Integer creditAdjFeeSecondary;
	private Integer urgentFeePrimary;
	private Integer urgentFeeSecondary;
	private Integer repinFeePrimary;
	private Integer repinFeeSecondary;

	private CardProductDto cardProduct;

	public CardProductFeeDto() {
	}

	/*
	 * public CardProductFeeDto(CardProductDto cardproduct){
	 * 
	 * this.cardProduct = cardproduct;
	 * cardProduct.getCardProductFee().add(this); }
	 */

	public int getAdminstrationFee() {
		return adminstrationFee;
	}

	public void setAdminstrationFee(int adminstrationFee) {
		this.adminstrationFee = adminstrationFee;
	}

	public int getAnnualFeePrimary() {
		return annualFeePrimary;
	}

	public void setAnnualFeePrimary(int annualFeePrimary) {
		this.annualFeePrimary = annualFeePrimary;
	}

	public int getAnnualFeeSecondary() {
		return annualFeeSecondary;
	}

	public void setAnnualFeeSecondary(int annualFeeSecondary) {
		this.annualFeeSecondary = annualFeeSecondary;
	}

	public int getCancelationFee() {
		return cancelationFee;
	}

	public void setCancelationFee(int cancelationFee) {
		this.cancelationFee = cancelationFee;
	}

	public String getCardProductId() {
		return cardProductId;
	}

	public void setCardProductId(String cardProductId) {
		this.cardProductId = cardProductId;
	}

	public int getCurrConversionFee() {
		return currConversionFee;
	}

	public void setCurrConversionFee(int currConversionFee) {
		this.currConversionFee = currConversionFee;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getIssuerFee() {
		return issuerFee;
	}

	public void setIssuerFee(int issuerFee) {
		this.issuerFee = issuerFee;
	}

	public int getReplacementFee() {
		return replacementFee;
	}

	public void setReplacementFee(int replacementFee) {
		this.replacementFee = replacementFee;
	}

	public Date getUpdatedDate() {
		return updatedDate;
	}

	public void setUpdatedDate(Date updatedDate) {
		this.updatedDate = updatedDate;
	}

	public CardProductDto getCardProduct() {
		return cardProduct;
	}

	public void setCardProduct(CardProductDto cardProduct) {
		this.cardProduct = cardProduct;
	}

	public String getDisplayStatus() {
		return displayStatus;
	}

	public void setDisplayStatus(String displayStatus) {
		this.displayStatus = displayStatus;
	}

	public Date getEndDt() {
		return endDt;
	}

	public void setEndDt(Date endDt) {
		this.endDt = endDt;
	}

	public Date getStartDt() {
		return startDt;
	}

	public void setStartDt(Date startDt) {
		this.startDt = startDt;
	}

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public Integer getJoinFeePrimary() {
		return joinFeePrimary;
	}

	public void setJoinFeePrimary(Integer joinFeePrimary) {
		this.joinFeePrimary = joinFeePrimary;
	}

	public Integer getJoinFeeSecondary() {
		return joinFeeSecondary;
	}

	public void setJoinFeeSecondary(Integer joinFeeSecondary) {
		this.joinFeeSecondary = joinFeeSecondary;
	}

	public Integer getCreditAdjFeePrimary() {
		return creditAdjFeePrimary;
	}

	public void setCreditAdjFeePrimary(Integer creditAdjFeePrimary) {
		this.creditAdjFeePrimary = creditAdjFeePrimary;
	}

	public Integer getCreditAdjFeeSecondary() {
		return creditAdjFeeSecondary;
	}

	public void setCreditAdjFeeSecondary(Integer creditAdjFeeSecondary) {
		this.creditAdjFeeSecondary = creditAdjFeeSecondary;
	}

	public Integer getUrgentFeePrimary() {
		return urgentFeePrimary;
	}

	public void setUrgentFeePrimary(Integer urgentFeePrimary) {
		this.urgentFeePrimary = urgentFeePrimary;
	}

	public Integer getUrgentFeeSecondary() {
		return urgentFeeSecondary;
	}

	public void setUrgentFeeSecondary(Integer urgentFeeSecondary) {
		this.urgentFeeSecondary = urgentFeeSecondary;
	}

	public Integer getRepinFeePrimary() {
		return repinFeePrimary;
	}

	public void setRepinFeePrimary(Integer repinFeePrimary) {
		this.repinFeePrimary = repinFeePrimary;
	}

	public Integer getRepinFeeSecondary() {
		return repinFeeSecondary;
	}

	public void setRepinFeeSecondary(Integer repinFeeSecondary) {
		this.repinFeeSecondary = repinFeeSecondary;
	}


}
