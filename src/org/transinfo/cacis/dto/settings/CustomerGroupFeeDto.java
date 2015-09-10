package org.transinfo.cacis.dto.settings;

import java.io.Serializable;
import java.util.Date;

@SuppressWarnings("serial")
public class CustomerGroupFeeDto implements Serializable {

	private String id;
	private CardProductDto cardProduct;
	private CustomerTypeDto customerType;
	private Float annualFee;
	private Float joinFee;
	private String userId;
	private Date updatedDate = new Date();
	private Float annualFeeSup;
	private Float joinFeeSup;

	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public CardProductDto getCardProduct() {
		return cardProduct;
	}
	public void setCardProduct(CardProductDto cardProduct) {
		this.cardProduct = cardProduct;
	}
	public CustomerTypeDto getCustomerType() {
		return customerType;
	}
	public void setCustomerType(CustomerTypeDto customerType) {
		this.customerType = customerType;
	}
	public String getUserId() {
		return userId;
	}
	public void setUserId(String userId) {
		this.userId = userId;
	}
	public Date getUpdatedDate() {
		return updatedDate;
	}
	public void setUpdatedDate(Date updatedDate) {
		this.updatedDate = updatedDate;
	}
	public Float getAnnualFee() {
		return annualFee;
	}
	public void setAnnualFee(Float annualFee) {
		this.annualFee = annualFee;
	}
	public Float getJoinFee() {
		return joinFee;
	}
	public void setJoinFee(Float joinFee) {
		this.joinFee = joinFee;
	}
	public Float getAnnualFeeSup() {
		return annualFeeSup;
	}
	public void setAnnualFeeSup(Float annualFeeSup) {
		this.annualFeeSup = annualFeeSup;
	}
	public Float getJoinFeeSup() {
		return joinFeeSup;
	}
	public void setJoinFeeSup(Float joinFeeSup) {
		this.joinFeeSup = joinFeeSup;
	}

}
