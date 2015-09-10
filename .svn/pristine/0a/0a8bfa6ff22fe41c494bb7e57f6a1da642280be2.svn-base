package org.transinfo.cacis.dto.cardproduction;

import java.io.Serializable;
import java.lang.reflect.Field;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import org.apache.log4j.Logger;

@SuppressWarnings( { "unchecked", "serial" })
public class CustomerAccountDto implements Serializable {

	/**
	 * 
	 */
	private String accountId;
	private String accountName;
	private float creditLimit;
	private float cashLimit;
	private float previousBalance = 0;
	private float amountCredited = 0;
	private float amountDebited = 0;
	private int currencyCode;
	private int cycleNo;
	private Date lastStatementDate = new Date();
	private Date lastStatementDueDate = new Date();
	private float minDueLastStatementDate = 0;
	private float paymentfromLastStatement = 0;
	private float currentMinPaymentDue = 0;
	private float limitUsed = 0;
	private float authorizationAmt = 0;
	private String savingAccount;
	private String checkingAccount;

	private Float previousPurhcaseBalance;
	private Float previousCashBalance;

	private String orgLimit;
	private String orgBackDate;

	private Double cashUsed=0.0;
	private Double saleUsed=0.0;
	
	private Integer acctStatus = 0;
	
	/*
	 * private String accountId; private String accountName; private float
	 * creditLimit; private float cashLimit; private float previousBalance;
	 * private float amountCredited; private float amountDebited; private int
	 * currencyCode; private int cycleNo; private Date lastStatementDate;
	 * private Date lastStatementDueDate; private float minDueLastStatementDate;
	 * private float paymentfromLastStatement; private float
	 * currentMinPaymentDue; private String savingAccount; private String
	 * checkingAccount;
	 */

	// this for CustomerAccounts to Cards one-to-many
	private Set customerCards = new HashSet();

	// this for CustomerAccounts to Cusomer many-to-one
	private ApplicationProcessDto appProcessDto;

	public CustomerAccountDto() {
	}

	public CustomerAccountDto(ApplicationProcessDto appProcessDto) {
		this.appProcessDto = appProcessDto;
		appProcessDto.getCustomerAccount().add(this);
	}

	public String getAccountId() {
		return accountId;
	}

	public void setAccountId(String accountId) {
		this.accountId = accountId;
	}

	public String getAccountName() {
		return accountName;
	}

	public void setAccountName(String accountName) {
		this.accountName = accountName;
	}

	public float getAmountCredited() {
		return amountCredited;
	}

	public void setAmountCredited(float amountCredited) {
		this.amountCredited = amountCredited;
	}

	public float getAmountDebited() {
		return amountDebited;
	}

	public void setAmountDebited(float amountDebited) {
		this.amountDebited = amountDebited;
	}

	public float getCashLimit() {
		return cashLimit;
	}

	public void setCashLimit(float cashLimit) {
		this.cashLimit = cashLimit;
	}

	public String getCheckingAccount() {
		return checkingAccount;
	}

	public void setCheckingAccount(String checkingAccount) {
		this.checkingAccount = checkingAccount;
	}

	public float getCreditLimit() {
		return creditLimit;
	}

	public void setCreditLimit(float creditLimit) {
		this.creditLimit = creditLimit;
	}

	public int getCurrencyCode() {
		return currencyCode;
	}

	public void setCurrencyCode(int currencyCode) {
		this.currencyCode = currencyCode;
	}

	public float getCurrentMinPaymentDue() {
		return currentMinPaymentDue;
	}

	public void setCurrentMinPaymentDue(float currentMinPaymentDue) {
		this.currentMinPaymentDue = currentMinPaymentDue;
	}

	public int getCycleNo() {
		return cycleNo;
	}

	public void setCycleNo(int cycleNo) {
		this.cycleNo = cycleNo;
	}

	public Date getLastStatementDate() {
		return lastStatementDate;
	}

	public void setLastStatementDate(Date lastStatementDate) {
		this.lastStatementDate = lastStatementDate;
	}

	public Date getLastStatementDueDate() {
		return lastStatementDueDate;
	}

	public void setLastStatementDueDate(Date lastStatementDueDate) {
		this.lastStatementDueDate = lastStatementDueDate;
	}

	public float getMinDueLastStatementDate() {
		return minDueLastStatementDate;
	}

	public void setMinDueLastStatementDate(float minDueLastStatementDate) {
		this.minDueLastStatementDate = minDueLastStatementDate;
	}

	public float getPaymentfromLastStatement() {
		return paymentfromLastStatement;
	}

	public void setPaymentfromLastStatement(float paymentfromLastStatement) {
		this.paymentfromLastStatement = paymentfromLastStatement;
	}

	public float getPreviousBalance() {
		return previousBalance;
	}

	public void setPreviousBalance(float previousBalance) {
		this.previousBalance = previousBalance;
	}

	public String getSavingAccount() {
		return savingAccount;
	}

	public void setSavingAccount(String savingAccount) {
		this.savingAccount = savingAccount;
	}

	public Set getCustomerCards() {
		return customerCards;
	}

	public void setCustomerCards(Set customerCards) {
		this.customerCards = customerCards;
	}

	public ApplicationProcessDto getAppProcessDto() {
		return appProcessDto;
	}

	public void setAppProcessDto(ApplicationProcessDto appProcessDto) {
		this.appProcessDto = appProcessDto;
	}

	public float getAuthorizationAmt() {
		return authorizationAmt;
	}

	public void setAuthorizationAmt(float authorizationAmt) {
		this.authorizationAmt = authorizationAmt;
	}

	public float getLimitUsed() {
		return limitUsed;
	}

	public void setLimitUsed(float limitUsed) {
		this.limitUsed = limitUsed;
	}

	public Float getPreviousPurhcaseBalance() {
		return previousPurhcaseBalance;
	}

	public void setPreviousPurhcaseBalance(Float previousPurhcaseBalance) {
		this.previousPurhcaseBalance = previousPurhcaseBalance;
	}

	public Float getPreviousCashBalance() {
		return previousCashBalance;
	}

	public void setPreviousCashBalance(Float previousCashBalance) {
		this.previousCashBalance = previousCashBalance;
	}
	
	public void printToLog(Logger billLog){
		StringBuffer buff = new StringBuffer();
		try {
			Field[] fields = this.getClass().getDeclaredFields();
			for (int i = 0; i < fields.length; i++)
			{
				buff.append(fields[i].getName()).append("\t=>\t").append(fields[i].get(this)).append(", ");
			}
			billLog.info(buff.toString());
		} catch (IllegalArgumentException e) {
			e.printStackTrace();
			billLog.info(e);
		} catch (IllegalAccessException e) {
			e.printStackTrace();
			billLog.info(e);
		}
	}

	public String getOrgLimit() {
		return orgLimit;
	}

	public void setOrgLimit(String orgLimit) {
		this.orgLimit = orgLimit;
	}

	public String getOrgBackDate() {
		return orgBackDate;
	}

	public void setOrgBackDate(String orgBackDate) {
		this.orgBackDate = orgBackDate;
	}

	public Double getCashUsed() {
		return cashUsed;
	}

	public void setCashUsed(Double cashUsed) {
		this.cashUsed = cashUsed;
	}

	public Double getSaleUsed() {
		return saleUsed;
	}

	public void setSaleUsed(Double saleUsed) {
		this.saleUsed = saleUsed;
	}

	public Integer getAcctStatus() {
		return acctStatus;
	}

	public void setAcctStatus(Integer acctStatus) {
		this.acctStatus = acctStatus;
	}

}
