package org.transinfo.cacis.dto.customerservice;

import java.io.Serializable;
import java.util.Date;

import org.transinfo.cacis.dto.cardproduction.CardsDto;
import org.transinfo.cacis.dto.csr.CustomerInfoDto;

@SuppressWarnings("serial")
public class CardChangeDto implements Serializable {
	
	private long cardNo;
	private CardsDto card;
	private String nric;
	private String customerName;
	private String existCardProduct;
	private String changeCardProduct;
	private char existCardStatus;
	private int status;
	private Date updatedDate = new Date();
	private String userId;
	private CustomerInfoDto customer;

	private String branchId;
	private String issuerId;
	
	public long getCardNo() {
		return cardNo;
	}
	public void setCardNo(long cardNo) {
		this.cardNo = cardNo;
	}
	public CardsDto getCard() {
		return card;
	}
	public void setCard(CardsDto card) {
		this.card = card;
	}
	public String getNric() {
		return nric;
	}
	public void setNric(String nric) {
		this.nric = nric;
	}
	public String getCustomerName() {
		return customerName;
	}
	public void setCustomerName(String customerName) {
		this.customerName = customerName;
	}
	public String getExistCardProduct() {
		return existCardProduct;
	}
	public void setExistCardProduct(String existCardProduct) {
		this.existCardProduct = existCardProduct;
	}
	public String getChangeCardProduct() {
		return changeCardProduct;
	}
	public void setChangeCardProduct(String changeCardProduct) {
		this.changeCardProduct = changeCardProduct;
	}
	public char getExistCardStatus() {
		return existCardStatus;
	}
	public void setExistCardStatus(char existCardStatus) {
		this.existCardStatus = existCardStatus;
	}
	public int getStatus() {
		return status;
	}
	public void setStatus(int status) {
		this.status = status;
	}
	public Date getUpdatedDate() {
		return updatedDate;
	}
	public void setUpdatedDate(Date updatedDate) {
		this.updatedDate = updatedDate;
	}
	public String getUserId() {
		return userId;
	}
	public void setUserId(String userId) {
		this.userId = userId;
	}
	public CustomerInfoDto getCustomer() {
		return customer;
	}
	public void setCustomer(CustomerInfoDto customer) {
		this.customer = customer;
	}
	public String getBranchId() {
		return branchId;
	}
	public void setBranchId(String branchId) {
		this.branchId = branchId;
	}
	public String getIssuerId() {
		return issuerId;
	}
	public void setIssuerId(String issuerId) {
		this.issuerId = issuerId;
	}
	
}
