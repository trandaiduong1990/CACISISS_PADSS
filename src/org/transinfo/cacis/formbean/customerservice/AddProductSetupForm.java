package org.transinfo.cacis.formbean.customerservice;

import java.util.Map;

import org.apache.struts.validator.ValidatorForm;
import org.transinfo.cacis.formbean.disputemanagement.TransactionLogForm;

@SuppressWarnings("serial")
public class AddProductSetupForm extends ValidatorForm {

	private String customerName;
	private String nric;
	private String accountType;
	private String accountId;
	private String customerId;
	private String cardProductId;
	private Map cardProductList;
	private Map accountIdAndCardProductIdList;
	private String creditLimit;
	
	public AddProductSetupForm() {
		this.accountType = "N";
	}
	public String getCustomerName() {
		return customerName;
	}
	public void setCustomerName(String customerName) {
		this.customerName = customerName;
	}
	public String getNric() {
		return nric;
	}
	public void setNric(String nric) {
		this.nric = nric;
	}
	public String getAccountType() {
		return accountType;
	}
	public void setAccountType(String accountType) {
		this.accountType = accountType;
	}
	public String getAccountId() {
		return accountId;
	}
	public void setAccountId(String accountId) {
		this.accountId = accountId;
	}
	public String getCustomerId() {
		return customerId;
	}
	public void setCustomerId(String customerId) {
		this.customerId = customerId;
	}
	public String getCardProductId() {
		return cardProductId;
	}
	public void setCardProductId(String cardProductId) {
		this.cardProductId = cardProductId;
	}
	public Map getCardProductList() {
		return cardProductList;
	}
	public void setCardProductList(Map cardProductList) {
		this.cardProductList = cardProductList;
	}
	public Map getAccountIdAndCardProductIdList() {
		return accountIdAndCardProductIdList;
	}
	public void setAccountIdAndCardProductIdList(Map accountIdAndCardProductIdList) {
		this.accountIdAndCardProductIdList = accountIdAndCardProductIdList;
	}
	public String getCreditLimit() {
		return creditLimit;
	}
	public void setCreditLimit(String creditLimit) {
		this.creditLimit = creditLimit;
	}
	
}
