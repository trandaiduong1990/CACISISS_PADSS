package org.transinfo.cacis.dto.settings;

import java.io.Serializable;
import java.util.Date;

@SuppressWarnings("serial")
public class BranchDto implements Serializable{

	
	 private String  branchId;
	 private String  issuerId;
	 private String  branchName;
	 private String  address1;
	 private String  address2;
	 private String  city;
	 private String  state;
	 private String  country;
	 private Long    postalCode;
	 private String  contactPhone;
	 private String  contactFax ;
	 private String  contactPerson;
	 private String  contactEmail;
	 private String  status;
	 private String  userId;
	 private Date  updatedDate = new Date();
	 private String accessAllBranch;
	 
	 public BranchDto(){}
	 
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
	public String getBranchId() {
		return branchId;
	}
	public void setBranchId(String branchId) {
		this.branchId = branchId;
	}
	public String getBranchName() {
		return branchName;
	}
	public void setBranchName(String branchName) {
		this.branchName = branchName;
	}
	public String getCity() {
		return city;
	}
	public void setCity(String city) {
		this.city = city;
	}
	public String getContactEmail() {
		return contactEmail;
	}
	public void setContactEmail(String contactEmail) {
		this.contactEmail = contactEmail;
	}
	
	public String getContactPerson() {
		return contactPerson;
	}
	public void setContactPerson(String contactPerson) {
		this.contactPerson = contactPerson;
	}
	
	public String getCountry() {
		return country;
	}
	public void setCountry(String country) {
		this.country = country;
	}
	public String getIssuerId() {
		return issuerId;
	}
	public void setIssuerId(String issuerId) {
		this.issuerId = issuerId;
	}
	
		
	public String getState() {
		return state;
	}
	public void setState(String state) {
		this.state = state;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public void setContactFax(String contactFax) {
		this.contactFax = contactFax;
	}
	public void setContactPhone(String contactPhone) {
		this.contactPhone = contactPhone;
	}
	public String getContactFax() {
		return contactFax;
	}
	public String getContactPhone() {
		return contactPhone;
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

	public Long getPostalCode() {
		return postalCode;
	}

	public void setPostalCode(Long postalCode) {
		this.postalCode = postalCode;
	}

	public String getAccessAllBranch() {
		return accessAllBranch;
	}

	public void setAccessAllBranch(String accessAllBranch) {
		this.accessAllBranch = accessAllBranch;
	}
	

}
