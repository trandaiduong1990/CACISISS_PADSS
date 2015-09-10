package org.transinfo.cacis.dto.cardproduction;

import java.io.Serializable;
import java.util.Date;

@SuppressWarnings({ "unchecked", "serial" })
public class SupplementaryCardHolderDto implements Serializable {
 
	private String customerId;
	private String applicationId;
	private String supplementaryId ;
	private String issuerId;
	private Date openDate;
	private Date closeDate;
	private int status;
	private String  branchId ;
	/*private String customerName ;
	private String surName;
	private String embossingName;
	private Date dob;
	private String pob ;
	private String gender;
	private String maritalStatus;
	private String idNumber;
	private Date   idDate;
	private Date   expDate ;
	private String idPlace;
	private String nationality;
	private String 	email ;
	private float income;*/
	private String supplCustomerName;
	private String supplSurName;
	private String supplEmbossingName;
	private Date supplDob;
	private String supplPob;
	private String supplGender;
	private String supplMaritalStatus;
	private String supplIdNumber;
	private Date supplIdDate;
	private Date supplIdExpire;
	private String supplIdPlace;
	private String supplNationality;
	private String supplEmail;
	private float supplIncome;
	private String remarks;
	private String lastUpdatedBy ="ADMIN";
	private Date updatedDate= new Date();;
	
	//for supplyAddress
	
	private String addressType;
	private String address1;
	private String address2 ;
	private String city ;
	private String state ;
	private String country ;
	private long postalCode ;
	private String phone;
	private String fax;
	
	private ApplicationProcessDto appProcessDto;
	
	public  SupplementaryCardHolderDto(){}
	
	public  SupplementaryCardHolderDto(ApplicationProcessDto appProcessDto){
		this.appProcessDto = appProcessDto;
		appProcessDto.getCustomerSuppAddress().add(this);
	}
	

	public String getAddress1() {
		return address1;
	}

	public void setAddress1(String address1) {
		this.address1 = address1;
	}

	public String getAddress2() {
		return address2;
	}

	public void setAddress2(String address2) {
		this.address2 = address2;
	}

	public String getAddressType() {
		return addressType;
	}

	public void setAddressType(String addressType) {
		this.addressType = addressType;
	}

	public String getApplicationId() {
		return applicationId;
	}

	public void setApplicationId(String applicationId) {
		this.applicationId = applicationId;
	}

	public String getBranchId() {
		return branchId;
	}

	public void setBranchId(String branchId) {
		this.branchId = branchId;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public Date getCloseDate() {
		return closeDate;
	}

	public void setCloseDate(Date closeDate) {
		this.closeDate = closeDate;
	}

	public String getCountry() {
		return country;
	}

	public void setCountry(String country) {
		this.country = country;
	}

	public String getCustomerId() {
		return customerId;
	}

	public void setCustomerId(String customerId) {
		this.customerId = customerId;
	}

	public String getFax() {
		return fax;
	}

	public void setFax(String fax) {
		this.fax = fax;
	}

	public String getIssuerId() {
		return issuerId;
	}

	public void setIssuerId(String issuerId) {
		this.issuerId = issuerId;
	}

	public String getLastUpdatedBy() {
		return lastUpdatedBy;
	}

	public void setLastUpdatedBy(String lastUpdatedBy) {
		this.lastUpdatedBy = lastUpdatedBy;
	}

	public Date getOpenDate() {
		return openDate;
	}

	public void setOpenDate(Date openDate) {
		this.openDate = openDate;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public long getPostalCode() {
		return postalCode;
	}

	public void setPostalCode(long postalCode) {
		this.postalCode = postalCode;
	}

	public String getRemarks() {
		return remarks;
	}

	public void setRemarks(String remarks) {
		this.remarks = remarks;
	}

	public String getState() {
		return state;
	}

	public void setState(String state) {
		this.state = state;
	}

	public int getStatus() {
		return status;
	}

	public void setStatus(int status) {
		this.status = status;
	}

	public String getSupplementaryId() {
		return supplementaryId ;//IdsGenartion.GenerateSupplementaryId();
	}

	public void setSupplementaryId(String supplementaryId) {
		this.supplementaryId = supplementaryId;
	}

	public Date getUpdatedDate() {
		return updatedDate;
	}

	public void setUpdatedDate(Date updatedDate) {
		this.updatedDate = updatedDate;
	}

	public ApplicationProcessDto getAppProcessDto() {
		return appProcessDto;
	}

	public void setAppProcessDto(ApplicationProcessDto appProcessDto) {
		this.appProcessDto = appProcessDto;
	}

	public String getSupplCustomerName() {
		return supplCustomerName;
	}

	public void setSupplCustomerName(String supplCustomerName) {
		this.supplCustomerName = supplCustomerName;
	}

	public String getSupplSurName() {
		return supplSurName;
	}

	public void setSupplSurName(String supplSurName) {
		this.supplSurName = supplSurName;
	}

	public String getSupplEmbossingName() {
		return supplEmbossingName;
	}

	public void setSupplEmbossingName(String supplEmbossingName) {
		this.supplEmbossingName = supplEmbossingName;
	}

	public String getSupplPob() {
		return supplPob;
	}

	public void setSupplPob(String supplPob) {
		this.supplPob = supplPob;
	}

	public String getSupplGender() {
		return supplGender;
	}

	public void setSupplGender(String supplGender) {
		this.supplGender = supplGender;
	}

	public String getSupplMaritalStatus() {
		return supplMaritalStatus;
	}

	public void setSupplMaritalStatus(String supplMaritalStatus) {
		this.supplMaritalStatus = supplMaritalStatus;
	}

	public String getSupplIdNumber() {
		return supplIdNumber;
	}

	public void setSupplIdNumber(String supplIdNumber) {
		this.supplIdNumber = supplIdNumber;
	}

	public String getSupplIdPlace() {
		return supplIdPlace;
	}

	public void setSupplIdPlace(String supplIdPlace) {
		this.supplIdPlace = supplIdPlace;
	}

	public String getSupplNationality() {
		return supplNationality;
	}

	public void setSupplNationality(String supplNationality) {
		this.supplNationality = supplNationality;
	}

	public String getSupplEmail() {
		return supplEmail;
	}

	public void setSupplEmail(String supplEmail) {
		this.supplEmail = supplEmail;
	}

	public float getSupplIncome() {
		return supplIncome;
	}

	public void setSupplIncome(float supplIncome) {
		this.supplIncome = supplIncome;
	}

	public Date getSupplDob() {
		return supplDob;
	}

	public void setSupplDob(Date supplDob) {
		this.supplDob = supplDob;
	}

	public Date getSupplIdDate() {
		return supplIdDate;
	}

	public void setSupplIdDate(Date supplIdDate) {
		this.supplIdDate = supplIdDate;
	}

	public Date getSupplIdExpire() {
		return supplIdExpire;
	}

	public void setSupplIdExpire(Date supplIdExpire) {
		this.supplIdExpire = supplIdExpire;
	}

}
