package org.transinfo.cacis.formbean.settings;

import java.util.Map;

import org.apache.struts.validator.ValidatorForm;
import org.transinfo.cacis.dataacess.DAOFactory;
import org.transinfo.cacis.dataacess.dao.settings.CardProductFeeDAO;
import org.transinfo.cacis.util.DateUtil;

@SuppressWarnings( { "serial", "unchecked" })
public class CardProductFeeSetupForm extends ValidatorForm {

	private String id;
	private String issuerId;
	private String cardProductId;
	private String annualFeePrimary;
	private String annualFeeSecondary;
	private String issuerFee;
	private String replacementFee;
	private String cancelationFee;
	private String currConversionFee;
	private String adminstrationFee;
	private String startDate;
	private String endDate;
	java.util.Date startDt;
	java.util.Date endDt;
	private String userId;
	
	private String joinFeePrimary;
	private String joinFeeSecondary;
	private String creditAdjFeePrimary;
	private String creditAdjFeeSecondary;
	private String urgentFeePrimary;
	private String urgentFeeSecondary;
	private String repinFeePrimary;
	private String repinFeeSecondary;

	private Map cardProductList;

	public CardProductFeeSetupForm() {
		// getPreListData();
	}

	public String getAdminstrationFee() {
		return adminstrationFee;
	}

	public void setAdminstrationFee(String adminstrationFee) {
		this.adminstrationFee = adminstrationFee;
	}

	public String getAnnualFeePrimary() {
		return annualFeePrimary;
	}

	public void setAnnualFeePrimary(String annualFeePrimary) {
		this.annualFeePrimary = annualFeePrimary;
	}

	public String getAnnualFeeSecondary() {
		return annualFeeSecondary;
	}

	public void setAnnualFeeSecondary(String annualFeeSecondary) {
		this.annualFeeSecondary = annualFeeSecondary;
	}

	public String getCancelationFee() {
		return cancelationFee;
	}

	public void setCancelationFee(String cancelationFee) {
		this.cancelationFee = cancelationFee;
	}

	public String getCardProductId() {
		return cardProductId;
	}

	public void setCardProductId(String cardProductId) {
		this.cardProductId = cardProductId;
	}

	public String getCurrConversionFee() {
		return currConversionFee;
	}

	public void setCurrConversionFee(String currConversionFee) {
		this.currConversionFee = currConversionFee;
	}

	public String getIssuerFee() {
		return issuerFee;
	}

	public void setIssuerFee(String issuerFee) {
		this.issuerFee = issuerFee;
	}

	public String getReplacementFee() {
		return replacementFee;
	}

	public void setReplacementFee(String replacementFee) {
		this.replacementFee = replacementFee;
	}

	public String getEndDate() {
		return endDate;
	}

	public void setEndDate(String endDate) {
		this.endDate = endDate;
	}

	public String getStartDate() {
		return startDate;
	}

	public void setStartDate(String startDate) {
		this.startDate = startDate;
	}

	public java.util.Date getEndDt() {

		if (getEndDate() != null) {
			this.endDt = DateUtil.convertDateStringToDate(getEndDate());
		}
		return this.endDt;
	}

	public void setEndDt(java.util.Date endDt) {
		this.endDt = endDt;
		if (endDt != null) {
			setEndDate(DateUtil.convertDateToDateString(endDt));
		}
	}

	public java.util.Date getStartDt() {

		if (getStartDate() != null) {
			this.startDt = DateUtil.convertDateStringToDate(getStartDate());
		}

		return this.startDt;
	}

	public void setStartDt(java.util.Date startDt) {
		this.startDt = startDt;

		if (startDt != null) {
			setStartDate(DateUtil.convertDateToDateString(startDt));
		}
	}

	public Map getCardProductList() {
		return cardProductList;
	}

	public void setCardProductList(Map cardProductList) {
		this.cardProductList = cardProductList;
	}

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public String getIssuerId() {
		return issuerId;
	}

	public void setIssuerId(String issuerId) {
		this.issuerId = issuerId;
	}

	public void getPreListData() {

		try {
			CardProductFeeDAO objDAO = DAOFactory.getInstance()
					.getCardProductFeeDAO();
			if (cardProductList == null) {
				cardProductList = objDAO.cardProductListData(issuerId);
			} else {
				System.out.println("cardProductList alredy existed");
			}
		} catch (Exception e) {
			System.out
					.println("Error while getting  cardProductList   in CardProductFeeSetupForm:"
							+ e.getMessage());
		}
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getJoinFeePrimary() {
		return joinFeePrimary;
	}

	public void setJoinFeePrimary(String joinFeePrimary) {
		this.joinFeePrimary = joinFeePrimary;
	}

	public String getJoinFeeSecondary() {
		return joinFeeSecondary;
	}

	public void setJoinFeeSecondary(String joinFeeSecondary) {
		this.joinFeeSecondary = joinFeeSecondary;
	}

	public String getCreditAdjFeePrimary() {
		return creditAdjFeePrimary;
	}

	public void setCreditAdjFeePrimary(String creditAdjFeePrimary) {
		this.creditAdjFeePrimary = creditAdjFeePrimary;
	}

	public String getCreditAdjFeeSecondary() {
		return creditAdjFeeSecondary;
	}

	public void setCreditAdjFeeSecondary(String creditAdjFeeSecondary) {
		this.creditAdjFeeSecondary = creditAdjFeeSecondary;
	}

	public String getUrgentFeePrimary() {
		return urgentFeePrimary;
	}

	public void setUrgentFeePrimary(String urgentFeePrimary) {
		this.urgentFeePrimary = urgentFeePrimary;
	}

	public String getUrgentFeeSecondary() {
		return urgentFeeSecondary;
	}

	public void setUrgentFeeSecondary(String urgentFeeSecondary) {
		this.urgentFeeSecondary = urgentFeeSecondary;
	}

	public String getRepinFeePrimary() {
		return repinFeePrimary;
	}

	public void setRepinFeePrimary(String repinFeePrimary) {
		this.repinFeePrimary = repinFeePrimary;
	}

	public String getRepinFeeSecondary() {
		return repinFeeSecondary;
	}

	public void setRepinFeeSecondary(String repinFeeSecondary) {
		this.repinFeeSecondary = repinFeeSecondary;
	}

}
