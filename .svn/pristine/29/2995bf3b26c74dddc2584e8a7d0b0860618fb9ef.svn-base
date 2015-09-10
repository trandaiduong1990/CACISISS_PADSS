package org.transinfo.cacis.dto.customerservice;

import java.io.Serializable;
import java.util.Date;

import org.transinfo.cacis.dto.cardproduction.CustomerAccountDto;
import org.transinfo.cacis.dto.cardproduction.CustomerAddressDto;

public class CardCloseDto implements Serializable {

	private String applicationId;
	private String issuerId;
	private String idNumber;
	private long cardNumber;
	private int applicationType;
	private int applicationStatus;
	private String remarks;
	private String userId;
	private Date updatedDate = new Date();
	// this for stoping the supplementary cards when the cardtostop is
	// primarycard
	private String[] cardsToStop;
	
	private String customerId;
	private String branchId;

	/* Setting CustomerAddressDto to CardCloseDto */
	CustomerAddressDto customerAddDto = new CustomerAddressDto();

	// this for Cards to CustomerAccount many-to-one
	private CustomerAccountDto customerAccountDto;

	public CardCloseDto() {
	}

	public String getApplicationId() {
		return applicationId;
	}

	public void setApplicationId(String applicationId) {
		this.applicationId = applicationId;
	}

	public long getCardNumber() {
		return new Long(cardNumber).longValue();
	}

	public void setCardNumber(long cardNumber) {
		this.cardNumber = cardNumber;
	}

	public String getIssuerId() {
		return issuerId;
	}

	public void setIssuerId(String issuerId) {
		this.issuerId = issuerId;
	}

	public String getRemarks() {
		return remarks;
	}

	public void setRemarks(String remarks) {
		this.remarks = remarks;
	}

	public Date getUpdatedDate() {
		return updatedDate;
	}

	public void setUpdatedDate(Date updatedDate) {
		this.updatedDate = updatedDate;
	}

	public int getApplicationStatus() {
		return applicationStatus;
	}

	public void setApplicationStatus(int applicationStatus) {
		this.applicationStatus = applicationStatus;
	}

	public int getApplicationType() {
		return applicationType;
	}

	public void setApplicationType(int applicationType) {
		this.applicationType = applicationType;
	}

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public CustomerAccountDto getCustomerAccountDto() {
		return customerAccountDto;
	}

	public void setCustomerAccountDto(CustomerAccountDto customerAccountDto) {
		this.customerAccountDto = customerAccountDto;
	}

	public CustomerAddressDto getCustomerAddDto() {
		return customerAddDto;
	}

	public void setCustomerAddDto(CustomerAddressDto customerAddDto) {
		this.customerAddDto = customerAddDto;
	}

	public String getIdNumber() {
		return idNumber;
	}

	public void setIdNumber(String idNumber) {
		this.idNumber = idNumber;
	}

	public String[] getCardsToStop() {
		return cardsToStop;
	}

	public void setCardsToStop(String[] cardsToStop) {
		this.cardsToStop = cardsToStop;
	}

	public String getCustomerId() {
		return customerId;
	}

	public void setCustomerId(String customerId) {
		this.customerId = customerId;
	}

	public String getBranchId() {
		return branchId;
	}

	public void setBranchId(String branchId) {
		this.branchId = branchId;
	}

}
