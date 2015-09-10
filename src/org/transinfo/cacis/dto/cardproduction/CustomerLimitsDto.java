package org.transinfo.cacis.dto.cardproduction;

import java.io.Serializable;
import java.util.Date;

@SuppressWarnings("serial")
public class CustomerLimitsDto implements Serializable {

	private long    cardNo;
	private Double    amtPerTranx;
	private Double    cashPerTranx;
	private Long    dailyLimitCount;
	private Double    dailyLimitAmt;
	private Long    monthlyLimitCount;
	private Double    monthlyLimitAmt;
	private String  currencyCode;
	private String  issuerId; 

	private Long    dailyCashCount;
	private Double    dailyCashAmt;
	private Double    cashLimit;
	private Double    purchaseLimit;

	private Double  eComAmt;

	private Date lastUpdatedDate = new Date();
	private String  lastUpdatedBy;

	//private String  eComEnable;

	private Double    amtPerTranxInt;
	private Double    cashPerTranxInt;
	private Long    dailyLimitCountInt;
	private Double    dailyLimitAmtInt;

	private Long    dailyCashCountInt;
	private Double    dailyCashAmtInt;
	private Double    cashLimitInt;
	private Double    purchaseLimitInt;

	private Double  eComAmtInt;

	private Double eComAmtPerDay;
	private Double eComAmtPerDayInt;

	private Long eComTranxPerDay;
	private Long eComTranxPerDayInt;

	private String  status;

	public long getCardNo() {
		return cardNo;
	}
	public void setCardNo(long cardNo) {
		this.cardNo = cardNo;
	}
	public Double getAmtPerTranx() {
		return amtPerTranx;
	}
	public void setAmtPerTranx(Double amtPerTranx) {
		this.amtPerTranx = amtPerTranx;
	}
	public Double getCashPerTranx() {
		return cashPerTranx;
	}
	public void setCashPerTranx(Double cashPerTranx) {
		this.cashPerTranx = cashPerTranx;
	}
	public Long getDailyLimitCount() {
		return dailyLimitCount;
	}
	public void setDailyLimitCount(Long dailyLimitCount) {
		this.dailyLimitCount = dailyLimitCount;
	}
	public Double getDailyLimitAmt() {
		return dailyLimitAmt;
	}
	public void setDailyLimitAmt(Double dailyLimitAmt) {
		this.dailyLimitAmt = dailyLimitAmt;
	}
	public Long getMonthlyLimitCount() {
		return monthlyLimitCount;
	}
	public void setMonthlyLimitCount(Long monthlyLimitCount) {
		this.monthlyLimitCount = monthlyLimitCount;
	}
	public Double getMonthlyLimitAmt() {
		return monthlyLimitAmt;
	}
	public void setMonthlyLimitAmt(Double monthlyLimitAmt) {
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
	public Long getDailyCashCount() {
		return dailyCashCount;
	}
	public void setDailyCashCount(Long dailyCashCount) {
		this.dailyCashCount = dailyCashCount;
	}
	public Double getDailyCashAmt() {
		return dailyCashAmt;
	}
	public void setDailyCashAmt(Double dailyCashAmt) {
		this.dailyCashAmt = dailyCashAmt;
	}
	public Double getCashLimit() {
		return cashLimit;
	}
	public void setCashLimit(Double cashLimit) {
		this.cashLimit = cashLimit;
	}
	public Double getPurchaseLimit() {
		return purchaseLimit;
	}
	public void setPurchaseLimit(Double purchaseLimit) {
		this.purchaseLimit = purchaseLimit;
	}
	public Double geteComAmt() {
		return eComAmt;
	}
	public void seteComAmt(Double eComAmt) {
		this.eComAmt = eComAmt;
	}
	public Date getLastUpdatedDate() {
		return lastUpdatedDate;
	}
	public void setLastUpdatedDate(Date lastUpdatedDate) {
		this.lastUpdatedDate = lastUpdatedDate;
	}
	public String getLastUpdatedBy() {
		return lastUpdatedBy;
	}
	public void setLastUpdatedBy(String lastUpdatedBy) {
		this.lastUpdatedBy = lastUpdatedBy;
	}
	public Double getAmtPerTranxInt() {
		return amtPerTranxInt;
	}
	public void setAmtPerTranxInt(Double amtPerTranxInt) {
		this.amtPerTranxInt = amtPerTranxInt;
	}
	public Double getCashPerTranxInt() {
		return cashPerTranxInt;
	}
	public void setCashPerTranxInt(Double cashPerTranxInt) {
		this.cashPerTranxInt = cashPerTranxInt;
	}
	public Long getDailyLimitCountInt() {
		return dailyLimitCountInt;
	}
	public void setDailyLimitCountInt(Long dailyLimitCountInt) {
		this.dailyLimitCountInt = dailyLimitCountInt;
	}
	public Double getDailyLimitAmtInt() {
		return dailyLimitAmtInt;
	}
	public void setDailyLimitAmtInt(Double dailyLimitAmtInt) {
		this.dailyLimitAmtInt = dailyLimitAmtInt;
	}
	public Long getDailyCashCountInt() {
		return dailyCashCountInt;
	}
	public void setDailyCashCountInt(Long dailyCashCountInt) {
		this.dailyCashCountInt = dailyCashCountInt;
	}
	public Double getDailyCashAmtInt() {
		return dailyCashAmtInt;
	}
	public void setDailyCashAmtInt(Double dailyCashAmtInt) {
		this.dailyCashAmtInt = dailyCashAmtInt;
	}
	public Double getCashLimitInt() {
		return cashLimitInt;
	}
	public void setCashLimitInt(Double cashLimitInt) {
		this.cashLimitInt = cashLimitInt;
	}
	public Double getPurchaseLimitInt() {
		return purchaseLimitInt;
	}
	public void setPurchaseLimitInt(Double purchaseLimitInt) {
		this.purchaseLimitInt = purchaseLimitInt;
	}
	public Double geteComAmtInt() {
		return eComAmtInt;
	}
	public void seteComAmtInt(Double eComAmtInt) {
		this.eComAmtInt = eComAmtInt;
	}
	public Double geteComAmtPerDay() {
		return eComAmtPerDay;
	}
	public void seteComAmtPerDay(Double eComAmtPerDay) {
		this.eComAmtPerDay = eComAmtPerDay;
	}
	public Double geteComAmtPerDayInt() {
		return eComAmtPerDayInt;
	}
	public void seteComAmtPerDayInt(Double eComAmtPerDayInt) {
		this.eComAmtPerDayInt = eComAmtPerDayInt;
	}
	public Long geteComTranxPerDay() {
		return eComTranxPerDay;
	}
	public void seteComTranxPerDay(Long eComTranxPerDay) {
		this.eComTranxPerDay = eComTranxPerDay;
	}
	public Long geteComTranxPerDayInt() {
		return eComTranxPerDayInt;
	}
	public void seteComTranxPerDayInt(Long eComTranxPerDayInt) {
		this.eComTranxPerDayInt = eComTranxPerDayInt;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}

}
