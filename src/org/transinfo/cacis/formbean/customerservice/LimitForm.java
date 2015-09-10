package org.transinfo.cacis.formbean.customerservice;

import java.io.Serializable;

public class LimitForm implements Serializable {
	
	private String cardNumber;
	private String cardHolderType;
	private String cardStatus;
	private String customerName;

	//these are for CreditLimit
	private String currentLimit;
	private String currentRatio;
	private String newLimit;
	private String newLimitRatio;
	
	//these for CashLimit
	private String currentCashLimit;
	private String currentCashLimitRatio;
	private String newCashLimit;
	private String newCashLimitRatio;
	
	public LimitForm(){}

	public String getCardNumber() {
		return cardNumber;
	}

	public void setCardNumber(String cardNumber) {
		this.cardNumber = cardNumber;
	}

	public String getNewLimit() {
		return newLimit;
	}

	public void setNewLimit(String newLimit) {
		this.newLimit = newLimit;
	}

	public String getNewLimitRatio() {
		return newLimitRatio;
	}

	public void setNewLimitRatio(String newLimitRatio) {
		this.newLimitRatio = newLimitRatio;
	}

	public String getCardHolderType() {
		return cardHolderType;
	}

	public void setCardHolderType(String cardHolderType) {
		this.cardHolderType = cardHolderType;
	}

	public String getCardStatus() {
		return cardStatus;
	}

	public void setCardStatus(String cardStatus) {
		this.cardStatus = cardStatus;
	}

	public String getCurrentLimit() {
		return currentLimit;
	}

	public void setCurrentLimit(String currentLimit) {
		this.currentLimit = currentLimit;
	}

	public String getCurrentRatio() {
		return currentRatio;
	}

	public void setCurrentRatio(String currentRatio) {
		this.currentRatio = currentRatio;
	}

	public String getCustomerName() {
		return customerName;
	}

	public void setCustomerName(String customerName) {
		this.customerName = customerName;
	}

	public String getCurrentCashLimit() {
		return currentCashLimit;
	}

	public void setCurrentCashLimit(String currentCashLimit) {
		this.currentCashLimit = currentCashLimit;
	}

	
	public String getNewCashLimit() {
		return newCashLimit;
	}

	public void setNewCashLimit(String newCashLimit) {
		this.newCashLimit = newCashLimit;
	}

	public String getNewCashLimitRatio() {
		return newCashLimitRatio;
	}

	public void setNewCashLimitRatio(String newCashLimitRatio) {
		this.newCashLimitRatio = newCashLimitRatio;
	}

	public String getCurrentCashLimitRatio() {
		return currentCashLimitRatio;
	}

	public void setCurrentCashLimitRatio(String currentCashLimitRatio) {
		this.currentCashLimitRatio = currentCashLimitRatio;
	}



}
