package org.transinfo.cacis.formbean.customerservice;

import java.util.HashMap;
import java.util.Map;

import org.apache.struts.validator.ValidatorForm;
import org.transinfo.cacis.formbean.settings.CardProductLimitSetupForm;

@SuppressWarnings( { "serial" })
public class CardLevelLimitSetupForm extends ValidatorForm {

	private String cardNo;
	private String amtPerTranx;
	private String cashPerTranx;
	private String dailyLimitCount;
	private String dailyLimitAmt;
	private String monthlyLimitCount;
	private String monthlyLimitAmt;
	private String currencyCode;
	private String issuerId; 

	private String dailyCashCount;
	private String dailyCashAmt;
	private String cashLimit;
	private String purchaseLimit;

	private String eComEnable = "N";
	private String eComAmt;

	private String cardHolderType;

	private CardProductLimitSetupForm objCardProductLimitSetupForm;
	private String cardProducteComEnable;

	private String amtPerTranxInt;
	private String cashPerTranxInt;
	private String dailyLimitCountInt;
	private String dailyLimitAmtInt;

	private String dailyCashCountInt;
	private String dailyCashAmtInt;
	private String cashLimitInt;
	private String purchaseLimitInt;

	private String eComAmtInt;

	private String eComAmtPerDay;
	private String eComAmtPerDayInt;

	private String eComTranxPerDay;
	private String eComTranxPerDayInt;
	
	private String  status;

	private Map statusList;

	public CardLevelLimitSetupForm() {
		// getPreListData();
	}

	public String getAmtPerTranx() {
		return amtPerTranx;
	}

	public void setAmtPerTranx(String amtPerTranx) {
		this.amtPerTranx = amtPerTranx;
	}

	public String getDailyLimitCount() {
		return dailyLimitCount;
	}

	public void setDailyLimitCount(String dailyLimitCount) {
		this.dailyLimitCount = dailyLimitCount;
	}

	public String getDailyLimitAmt() {
		return dailyLimitAmt;
	}

	public void setDailyLimitAmt(String dailyLimitAmt) {
		this.dailyLimitAmt = dailyLimitAmt;
	}

	public String getMonthlyLimitCount() {
		return monthlyLimitCount;
	}

	public void setMonthlyLimitCount(String monthlyLimitCount) {
		this.monthlyLimitCount = monthlyLimitCount;
	}

	public String getMonthlyLimitAmt() {
		return monthlyLimitAmt;
	}

	public void setMonthlyLimitAmt(String monthlyLimitAmt) {
		this.monthlyLimitAmt = monthlyLimitAmt;
	}

	public String getCurrencyCode() {
		return currencyCode;
	}

	public void setCurrencyCode(String currencyCode) {
		this.currencyCode = currencyCode;
	}

	public String getIssuerId() {
		return issuerId;
	}

	public void setIssuerId(String issuerId) {
		this.issuerId = issuerId;
	}

	public String getDailyCashCount() {
		return dailyCashCount;
	}

	public void setDailyCashCount(String dailyCashCount) {
		this.dailyCashCount = dailyCashCount;
	}

	public String getDailyCashAmt() {
		return dailyCashAmt;
	}

	public void setDailyCashAmt(String dailyCashAmt) {
		this.dailyCashAmt = dailyCashAmt;
	}

	public String getCashLimit() {
		return cashLimit;
	}

	public void setCashLimit(String cashLimit) {
		this.cashLimit = cashLimit;
	}

	public String getPurchaseLimit() {
		return purchaseLimit;
	}

	public void setPurchaseLimit(String purchaseLimit) {
		this.purchaseLimit = purchaseLimit;
	}

	public String geteComEnable() {
		return eComEnable;
	}

	public void seteComEnable(String eComEnable) {
		this.eComEnable = eComEnable;
	}

	public String getCardNo() {
		return cardNo;
	}

	public void setCardNo(String cardNo) {
		this.cardNo = cardNo;
	}

	public String getCardHolderType() {
		return cardHolderType;
	}

	public void setCardHolderType(String cardHolderType) {
		this.cardHolderType = cardHolderType;
	}

	public CardProductLimitSetupForm getObjCardProductLimitSetupForm() {
		return objCardProductLimitSetupForm;
	}

	public void setObjCardProductLimitSetupForm(
			CardProductLimitSetupForm objCardProductLimitSetupForm) {
		this.objCardProductLimitSetupForm = objCardProductLimitSetupForm;
	}

	public String getCardProducteComEnable() {
		return cardProducteComEnable;
	}

	public void setCardProducteComEnable(String cardProducteComEnable) {
		this.cardProducteComEnable = cardProducteComEnable;
	}

	public String getCashPerTranx() {
		return cashPerTranx;
	}

	public void setCashPerTranx(String cashPerTranx) {
		this.cashPerTranx = cashPerTranx;
	}

	public String geteComAmt() {
		return eComAmt;
	}

	public void seteComAmt(String eComAmt) {
		this.eComAmt = eComAmt;
	}

	public String getAmtPerTranxInt() {
		return amtPerTranxInt;
	}

	public void setAmtPerTranxInt(String amtPerTranxInt) {
		this.amtPerTranxInt = amtPerTranxInt;
	}

	public String getCashPerTranxInt() {
		return cashPerTranxInt;
	}

	public void setCashPerTranxInt(String cashPerTranxInt) {
		this.cashPerTranxInt = cashPerTranxInt;
	}

	public String getDailyLimitCountInt() {
		return dailyLimitCountInt;
	}

	public void setDailyLimitCountInt(String dailyLimitCountInt) {
		this.dailyLimitCountInt = dailyLimitCountInt;
	}

	public String getDailyLimitAmtInt() {
		return dailyLimitAmtInt;
	}

	public void setDailyLimitAmtInt(String dailyLimitAmtInt) {
		this.dailyLimitAmtInt = dailyLimitAmtInt;
	}

	public String getDailyCashCountInt() {
		return dailyCashCountInt;
	}

	public void setDailyCashCountInt(String dailyCashCountInt) {
		this.dailyCashCountInt = dailyCashCountInt;
	}

	public String getDailyCashAmtInt() {
		return dailyCashAmtInt;
	}

	public void setDailyCashAmtInt(String dailyCashAmtInt) {
		this.dailyCashAmtInt = dailyCashAmtInt;
	}

	public String getCashLimitInt() {
		return cashLimitInt;
	}

	public void setCashLimitInt(String cashLimitInt) {
		this.cashLimitInt = cashLimitInt;
	}

	public String getPurchaseLimitInt() {
		return purchaseLimitInt;
	}

	public void setPurchaseLimitInt(String purchaseLimitInt) {
		this.purchaseLimitInt = purchaseLimitInt;
	}

	public String geteComAmtInt() {
		return eComAmtInt;
	}

	public void seteComAmtInt(String eComAmtInt) {
		this.eComAmtInt = eComAmtInt;
	}

	public String geteComAmtPerDay() {
		return eComAmtPerDay;
	}

	public void seteComAmtPerDay(String eComAmtPerDay) {
		this.eComAmtPerDay = eComAmtPerDay;
	}

	public String geteComAmtPerDayInt() {
		return eComAmtPerDayInt;
	}

	public void seteComAmtPerDayInt(String eComAmtPerDayInt) {
		this.eComAmtPerDayInt = eComAmtPerDayInt;
	}

	public String geteComTranxPerDay() {
		return eComTranxPerDay;
	}

	public void seteComTranxPerDay(String eComTranxPerDay) {
		this.eComTranxPerDay = eComTranxPerDay;
	}

	public String geteComTranxPerDayInt() {
		return eComTranxPerDayInt;
	}

	public void seteComTranxPerDayInt(String eComTranxPerDayInt) {
		this.eComTranxPerDayInt = eComTranxPerDayInt;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public void getPreListData() {

		try {

			if (statusList == null) {
				statusList = new HashMap();
				statusList.put("A", "Enable");
				statusList.put("I", "Disable");
			}

		} catch (Exception e) {
			System.out.println("Error while getting  PreListData in CardDeliverSearch:" + e.getMessage());
		}

	}

	public Map getStatusList() {
		return statusList;
	}

	public void setStatusList(Map statusList) {
		this.statusList = statusList;
	}

}
