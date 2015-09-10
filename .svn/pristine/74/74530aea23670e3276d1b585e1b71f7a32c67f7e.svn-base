package org.transinfo.cacis.dto.riskmanagement;

import java.io.Serializable;
import java.util.Date;

import org.transinfo.cacis.formbean.common.DateForm;

public class CardholderLimitAdjustmentDto implements Cloneable, Serializable {

	private long temporaryLimitId;

	private double creditLimit;

	private double cashLimit;

	private long cardNumber;

	private Date fromDate = new Date();

	private DateForm fromDateForm = new DateForm();

	private Date toDate = new Date();

	private DateForm toDateForm = new DateForm();

	private int amountPerTranx;

	private int dailyLimitCount;

	private int dailyLimitAmount;

	private int monthlyLimitCount;

	private int monthlyLimitAmount;

	private String currCode;

	private String issuerId;

	private char status;

	// private Date lastUpdatedDate = new Date();
	// private DateForm lastUpdatedDateForm = new DateForm();
	// private String lastUpdatedBy;

	public int getAmountPerTranx() {
		return amountPerTranx;
	}

	public void setAmountPerTranx(int amountPerTranx) {
		this.amountPerTranx = amountPerTranx;
	}

	public long getCardNumber() {
		return new Long(cardNumber).longValue();
	}

	public void setCardNumber(long cardNumber) {
		this.cardNumber = cardNumber;
	}

	public String getCurrCode() {
		return currCode;
	}

	public void setCurrCode(String currCode) {
		this.currCode = currCode;
	}

	public int getDailyLimitAmount() {
		return dailyLimitAmount;
	}

	public void setDailyLimitAmount(int dailyLimitAmount) {
		this.dailyLimitAmount = dailyLimitAmount;
	}

	public int getDailyLimitCount() {
		return dailyLimitCount;
	}

	public void setDailyLimitCount(int dailyLimitCount) {
		this.dailyLimitCount = dailyLimitCount;
	}

	public String getIssuerId() {
		return issuerId;
	}

	public void setIssuerId(String issuerId) {
		this.issuerId = issuerId;
	}

	/*
	 * public String getLastUpdatedBy() { return lastUpdatedBy; }
	 *
	 * public void setLastUpdatedBy(String lastUpdatedBy) { this.lastUpdatedBy =
	 * lastUpdatedBy; }
	 *
	 * public DateForm getLastUpdatedDateForm() { return lastUpdatedDateForm; }
	 *
	 * public void setLastUpdatedDateForm(DateForm lastUpdatedDateForm) {
	 * this.lastUpdatedDateForm = lastUpdatedDateForm; }
	 */

	public int getMonthlyLimitAmount() {
		return monthlyLimitAmount;
	}

	public void setMonthlyLimitAmount(int monthlyLimitAmount) {
		this.monthlyLimitAmount = monthlyLimitAmount;
	}

	public int getMonthlyLimitCount() {
		return monthlyLimitCount;
	}

	public void setMonthlyLimitCount(int monthlyLimitCount) {
		this.monthlyLimitCount = monthlyLimitCount;
	}

	public long getTemporaryLimitId() {
		return temporaryLimitId;
	}

	public void setTemporaryLimitId(long temporaryLimitId) {
		this.temporaryLimitId = temporaryLimitId;
	}

	public CardholderLimitAdjustmentDto() {
	}

	public CardholderLimitAdjustmentDto(int cardNumberIn) {
		this.cardNumber = cardNumberIn;
	}

	/*
	 * public Date getLastUpdatedDate() { return lastUpdatedDate; }
	 *
	 * public void setLastUpdatedDate(Date lastUpdatedDateIn) {
	 * lastUpdatedDate=lastUpdatedDateIn; if(lastUpdatedDate!=null) {
	 * lastUpdatedDateForm = new DateForm(lastUpdatedDate); } }
	 */

	public Date getFromDate() {
		return fromDate;
	}

	public void setFromDate(Date fromDate) {

		this.fromDate = fromDate;

		if (fromDate != null) {
			this.fromDateForm = new DateForm(fromDate);
		}
	}

	public DateForm getFromDateForm() {
		return fromDateForm;
	}

	public void setFromDateForm(DateForm fromDateForm) {
		this.fromDateForm = fromDateForm;
		fromDate = fromDateForm.toDate();
	}

	public Date getToDate() {
		return this.toDate;
	}

	public void setToDate(Date toDateIn) {
		this.toDate = toDateIn;
		if (toDate != null) {
			this.toDateForm = new DateForm(toDate);
		}
	}

	public DateForm getToDateForm() {
		return toDateForm;
	}

	public void setToDateForm(DateForm toDateForm) {
		this.toDateForm = toDateForm;
		toDate = toDateForm.toDate();
	}

	public double getCashLimit() {
		return cashLimit;
	}

	public void setCashLimit(double cashLimit) {
		this.cashLimit = cashLimit;
	}

	public double getCreditLimit() {
		return creditLimit;
	}

	public void setCreditLimit(double creditLimit) {
		this.creditLimit = creditLimit;
	}

	public char getStatus() {
		return status;
	}

	public void setStatus(char status) {
		this.status = status;
	}

}
