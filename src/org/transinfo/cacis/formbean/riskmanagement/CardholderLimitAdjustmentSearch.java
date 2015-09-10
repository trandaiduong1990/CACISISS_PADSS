package org.transinfo.cacis.formbean.riskmanagement;

import java.io.Serializable;

import org.apache.struts.validator.ValidatorForm;
import org.transinfo.cacis.formbean.common.DateForm;

public class CardholderLimitAdjustmentSearch extends ValidatorForm implements Serializable {

	private String cardNumber;

	private String creditLimit;
	private String cashLimit;
	DateForm fromDateForm = new DateForm();
	DateForm toDateForm = new DateForm();
	private String amountPerTranx;
	private String dailyLimitCount;
	private String dailyLimitAmount;
	private String monthlyLimitCount;
	private String monthlyLimitAmount;
	private String currCode;

	public String getAmountPerTranx() {
		return amountPerTranx;
	}

	public void setAmountPerTranx(String amountPerTranx) {
		this.amountPerTranx = amountPerTranx;
	}

	public String getCashLimit() {
		return cashLimit;
	}

	public void setCashLimit(String cashLimit) {
		this.cashLimit = cashLimit;
	}

	public String getCreditLimit() {
		return creditLimit;
	}

	public void setCreditLimit(String creditLimit) {
		this.creditLimit = creditLimit;
	}

	public String getCurrCode() {
		return currCode;
	}

	public void setCurrCode(String currCode) {
		this.currCode = currCode;
	}

	public String getDailyLimitAmount() {
		return dailyLimitAmount;
	}

	public void setDailyLimitAmount(String dailyLimitAmount) {
		this.dailyLimitAmount = dailyLimitAmount;
	}

	public String getDailyLimitCount() {
		return dailyLimitCount;
	}

	public void setDailyLimitCount(String dailyLimitCount) {
		this.dailyLimitCount = dailyLimitCount;
	}

	public DateForm getFromDateForm() {
		return fromDateForm;
	}

	public void setFromDateForm(DateForm fromDateForm) {
		this.fromDateForm = fromDateForm;
	}

	public String getMonthlyLimitAmount() {
		return monthlyLimitAmount;
	}

	public void setMonthlyLimitAmount(String monthlyLimitAmount) {
		this.monthlyLimitAmount = monthlyLimitAmount;
	}

	public String getMonthlyLimitCount() {
		return monthlyLimitCount;
	}

	public void setMonthlyLimitCount(String monthlyLimitCount) {
		this.monthlyLimitCount = monthlyLimitCount;
	}

	public DateForm getToDateForm() {
		return toDateForm;
	}

	public void setToDateForm(DateForm toDateForm) {
		this.toDateForm = toDateForm;
	}

	public String getCardNumber() {
		System.out.println("****get"+cardNumber+"***");
		return cardNumber;
	}

	public void setCardNumber(String cardNumber) {
		this.cardNumber = cardNumber;
		System.out.println("****set"+cardNumber+"***");
	}

	public CardholderLimitAdjustmentSearch(){}

}
