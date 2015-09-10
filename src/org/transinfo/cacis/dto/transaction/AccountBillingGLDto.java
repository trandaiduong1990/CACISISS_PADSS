package org.transinfo.cacis.dto.transaction;

import java.io.Serializable;
import java.util.Date;

@SuppressWarnings("serial")
public class AccountBillingGLDto implements Serializable {

	private String accountBillingGLId;
	private String accountId;
	private Date billingDate;
	private Date dueDate;
	private int gracePeriod;
	private double previousPurchaseBalance;
	private double previousCashBalance;
	private double cashAdvances;
	private double lateFee;
	private double overLimitFee;
	private double financeCharges;
	private double newPurchaseBalance;
	private double newCashBalance;
	private double minimumPayment;
	
	public String getAccountBillingGLId() {
		return accountBillingGLId;
	}
	public void setAccountBillingGLId(String accountBillingGLId) {
		this.accountBillingGLId = accountBillingGLId;
	}
	public String getAccountId() {
		return accountId;
	}
	public void setAccountId(String accountId) {
		this.accountId = accountId;
	}
	public Date getBillingDate() {
		return billingDate;
	}
	public void setBillingDate(Date billingDate) {
		this.billingDate = billingDate;
	}
	public Date getDueDate() {
		return dueDate;
	}
	public void setDueDate(Date dueDate) {
		this.dueDate = dueDate;
	}
	public int getGracePeriod() {
		return gracePeriod;
	}
	public void setGracePeriod(int gracePeriod) {
		this.gracePeriod = gracePeriod;
	}
	public double getPreviousPurchaseBalance() {
		return previousPurchaseBalance;
	}
	public void setPreviousPurchaseBalance(double previousPurchaseBalance) {
		this.previousPurchaseBalance = previousPurchaseBalance;
	}
	public double getPreviousCashBalance() {
		return previousCashBalance;
	}
	public void setPreviousCashBalance(double previousCashBalance) {
		this.previousCashBalance = previousCashBalance;
	}
	public double getCashAdvances() {
		return cashAdvances;
	}
	public void setCashAdvances(double cashAdvances) {
		this.cashAdvances = cashAdvances;
	}
	public double getLateFee() {
		return lateFee;
	}
	public void setLateFee(double lateFee) {
		this.lateFee = lateFee;
	}
	public double getOverLimitFee() {
		return overLimitFee;
	}
	public void setOverLimitFee(double overLimitFee) {
		this.overLimitFee = overLimitFee;
	}
	public double getFinanceCharges() {
		return financeCharges;
	}
	public void setFinanceCharges(double financeCharges) {
		this.financeCharges = financeCharges;
	}
	public double getNewPurchaseBalance() {
		return newPurchaseBalance;
	}
	public void setNewPurchaseBalance(double newPurchaseBalance) {
		this.newPurchaseBalance = newPurchaseBalance;
	}
	public double getNewCashBalance() {
		return newCashBalance;
	}
	public void setNewCashBalance(double newCashBalance) {
		this.newCashBalance = newCashBalance;
	}
	public double getMinimumPayment() {
		return minimumPayment;
	}
	public void setMinimumPayment(double minimumPayment) {
		this.minimumPayment = minimumPayment;
	}
	
}
