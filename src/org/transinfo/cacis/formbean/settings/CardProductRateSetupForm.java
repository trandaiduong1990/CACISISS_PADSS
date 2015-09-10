package org.transinfo.cacis.formbean.settings;

import java.util.Map;

import org.apache.struts.validator.ValidatorForm;
import org.transinfo.cacis.dataacess.DAOFactory;
import org.transinfo.cacis.dataacess.dao.settings.CardProductRateDAO;
import org.transinfo.cacis.util.DateUtil;

@SuppressWarnings( { "unchecked", "serial" })
public class CardProductRateSetupForm extends ValidatorForm {

	private String id;
	private String issuerId;
	private String cardProductId;
	private String creditFinanceCharge;
	private String latePaymentFee;
	private String minRepaymentPercent;
	private String minRepaymentAmt;
	private String cashAdvanceCharge;
	private String cashFinaceCharge;
	private String cashChargeAmt;
	private String maxCashTranx;
	private String maxCashDueTranx;
	private String rateStartDate;
	private String rateEndDate;
	java.util.Date startDt;
	java.util.Date endDt;
	private String userId;
	
	private String overLimitCharge;
	private String gracePeriod;
	private String minAmtCalMethod = "PER";
	private String tranxFee;
	private String latePaymentFeePer;

	private Map cardProductList;

	public CardProductRateSetupForm() {
		// getPreListData();
	}

	public String getCardProductId() {
		return cardProductId;
	}

	public void setCardProductId(String cardProductId) {
		this.cardProductId = cardProductId;
	}

	public String getCashAdvanceCharge() {
		return cashAdvanceCharge;
	}

	public void setCashAdvanceCharge(String cashAdvanceCharge) {
		this.cashAdvanceCharge = cashAdvanceCharge;
	}

	public String getCashChargeAmt() {
		return cashChargeAmt;
	}

	public void setCashChargeAmt(String cashChargeAmt) {
		this.cashChargeAmt = cashChargeAmt;
	}

	public String getCashFinaceCharge() {
		return cashFinaceCharge;
	}

	public void setCashFinaceCharge(String cashFinaceCharge) {
		this.cashFinaceCharge = cashFinaceCharge;
	}

	public String getCreditFinanceCharge() {
		return creditFinanceCharge;
	}

	public void setCreditFinanceCharge(String creditFinanceCharge) {
		this.creditFinanceCharge = creditFinanceCharge;
	}

	public String getLatePaymentFee() {
		return latePaymentFee;
	}

	public void setLatePaymentFee(String latePaymentFee) {
		this.latePaymentFee = latePaymentFee;
	}

	public String getMaxCashDueTranx() {
		return maxCashDueTranx;
	}

	public void setMaxCashDueTranx(String maxCashDueTranx) {
		this.maxCashDueTranx = maxCashDueTranx;
	}

	public String getMaxCashTranx() {
		return maxCashTranx;
	}

	public void setMaxCashTranx(String maxCashTranx) {
		this.maxCashTranx = maxCashTranx;
	}

	public String getMinRepaymentAmt() {
		return minRepaymentAmt;
	}

	public void setMinRepaymentAmt(String minRepaymentAmt) {
		this.minRepaymentAmt = minRepaymentAmt;
	}

	public String getMinRepaymentPercent() {
		return minRepaymentPercent;
	}

	public void setMinRepaymentPercent(String minRepaymentPercent) {
		this.minRepaymentPercent = minRepaymentPercent;
	}

	public String getRateEndDate() {
		return rateEndDate;
	}

	public void setRateEndDate(String rateEndDate) {
		this.rateEndDate = rateEndDate;
	}

	public String getRateStartDate() {
		return rateStartDate;
	}

	public void setRateStartDate(String rateStartDate) {
		this.rateStartDate = rateStartDate;
	}

	public java.util.Date getEndDt() {

		if (getRateEndDate() != null) {
			this.endDt = DateUtil.convertDateStringToDate(getRateEndDate());
		}
		return this.endDt;
	}

	public void setEndDt(java.util.Date endDt) {
		this.endDt = endDt;
		if (endDt != null) {
			setRateEndDate(DateUtil.convertDateToDateString(endDt));
		}
	}

	public java.util.Date getStartDt() {

		if (getRateStartDate() != null) {
			this.startDt = DateUtil.convertDateStringToDate(getRateStartDate());
		}

		return this.startDt;
	}

	public void setStartDt(java.util.Date startDt) {
		this.startDt = startDt;

		if (startDt != null) {
			setRateStartDate(DateUtil.convertDateToDateString(startDt));
		}
	}

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public Map getCardProductList() {
		return cardProductList;
	}

	public void setCardProductList(Map cardProductList) {
		this.cardProductList = cardProductList;
	}

	public String getIssuerId() {
		return issuerId;
	}

	public void setIssuerId(String issuerId) {
		this.issuerId = issuerId;
	}

	public void getPreListData() {

		try {
			CardProductRateDAO objDAO = DAOFactory.getInstance()
					.getCardProductRateDAO();
			if (cardProductList == null) {
				cardProductList = objDAO.cardProductListData(issuerId);
			} else {
				System.out.println("cardProductList alredy existed");
			}
		} catch (Exception e) {
			System.out
					.println("Error while getting  cardProductList   in CardProductRateSetupForm:"
							+ e.getMessage());
		}
	}

	public String getOverLimitCharge() {
		return overLimitCharge;
	}

	public void setOverLimitCharge(String overLimitCharge) {
		this.overLimitCharge = overLimitCharge;
	}

	public String getGracePeriod() {
		return gracePeriod;
	}

	public void setGracePeriod(String gracePeriod) {
		this.gracePeriod = gracePeriod;
	}

	public String getMinAmtCalMethod() {
		return minAmtCalMethod;
	}

	public void setMinAmtCalMethod(String minAmtCalMethod) {
		this.minAmtCalMethod = minAmtCalMethod;
	}

	public String getTranxFee() {
		return tranxFee;
	}

	public void setTranxFee(String tranxFee) {
		this.tranxFee = tranxFee;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getLatePaymentFeePer() {
		return latePaymentFeePer;
	}

	public void setLatePaymentFeePer(String latePaymentFeePer) {
		this.latePaymentFeePer = latePaymentFeePer;
	}

}
