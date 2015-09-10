package org.transinfo.cacis.dto.applicationforms;

import java.io.Serializable;
import java.util.Date;

import org.apache.commons.beanutils.BeanUtils;
import org.transinfo.cacis.dto.cardproduction.CustomerAccountDto;
import org.transinfo.cacis.dto.common.AddressDto;
import org.transinfo.cacis.formbean.common.AddressForm;
import org.transinfo.cacis.formbean.common.DateForm;

/**
 * SupplemntaryFormDto Value Object. This class is value object representing
 * database table SUPPLEMENTARY_FORMS This class is intented to be used together
 * with associated Dao object.
 */

@SuppressWarnings("serial")
public class SupplementaryFormDto implements Cloneable, Serializable {

	/**
	 * Persistent Instance variables. This data is directly mapped to the
	 * columns of database table.
	 */
	private String applicationId;
	private String customerId;
	private String issuerId;
	private String userId;
	private Date openDate = new Date();
	private DateForm openDateForm;
	private Date closeDate;
	private DateForm closeDateForm;
	private int applType;
	private int applStatus;
	private String branchId;
	private String cardProductId;
	private String principleChSurname;
	private String principleChName;
	private long principleChCardNo;
	private String principleMobileNo;
	private String principleHomeNo;
	private String principleOfficeNo;
	private String principleNRIC;
	private String customerName;
	private String surnameName;
	private String embossingName;
	private Date dob;
	private DateForm dobForm;
	private String pob;
	private String gender;
	private String maritalStatus;
	private int relationship;
	private String idNumber;
	private Date idDate;
	private DateForm idDateForm;
	private Date idExpire;
	private DateForm idExpireForm;
	private String nationality;
	private String idPlace;
	private String email;
	private int income;
	private String address1;
	private String address2;
	private String city;
	private String state;
	private String country;
	private int postalCode;
	private String fax;
	private String tel;
	private String remarks;
	private Date lastUpdatedDate = new Date();
	private DateForm lastUpdatedDateForm;

	// to insert cards_embosing table from constants(CardProcess Status)
	private int cardProcessStatus;

	AddressDto addressDto = new AddressDto();
	AddressForm addressForm = new AddressForm();

	// this for Cards to CustomerAccount many-to-one
	private CustomerAccountDto customerAccountDto;

	/**
	 * Constructors. The first one takes no arguments and provides the most
	 * simple way to create object instance. The another one takes one argument,
	 * which is the primary key of the corresponding table.
	 */

	public SupplementaryFormDto() {

	}

	public SupplementaryFormDto(String applicationIdIn) {

		this.applicationId = applicationIdIn;

	}

	/**
	 * Get- and Set-methods for persistent variables. The default behaviour does
	 * not make any checks against malformed data, so these might require some
	 * manual additions.
	 */

	public String getApplicationId() {
		return this.applicationId;
	}

	public void setApplicationId(String applicationIdIn) {
		this.applicationId = applicationIdIn;
	}

	public String getIssuerId() {
		return this.issuerId;
	}

	public void setIssuerId(String issuerIdIn) {
		this.issuerId = issuerIdIn;
	}

	public String getUserId() {
		return this.userId;
	}

	public void setUserId(String userIdIn) {
		this.userId = userIdIn;
	}

	public Date getOpenDate() {
		return openDate;
	}

	public void setOpenDate(Date openDate) {

		this.openDate = openDate;

		if (openDate != null) {
			this.openDateForm = new DateForm(openDate);
		}
	}

	public DateForm getOpenDateForm() {
		return openDateForm;
	}

	public void setOpenDateForm(DateForm openDateForm) {
		this.openDateForm = openDateForm;
	}

	public Date getCloseDate() {
		return this.closeDate;
	}

	public void setCloseDate(Date closeDateIn) {
		this.closeDate = closeDateIn;
		if (closeDate != null) {
			this.closeDateForm = new DateForm(closeDate);
		}
	}

	public DateForm getCloseDateForm() {
		return closeDateForm;
	}

	public void setCloseDateForm(DateForm closeDateForm) {
		this.closeDateForm = closeDateForm;
	}

	public int getApplType() {
		return this.applType;
	}

	public void setApplType(int applTypeIn) {
		this.applType = applTypeIn;
	}

	public int getApplStatus() {
		return this.applStatus;
	}

	public void setApplStatus(int applStatusIn) {
		this.applStatus = applStatusIn;
	}

	public String getBranchId() {
		return this.branchId;
	}

	public void setBranchId(String branchIdIn) {
		this.branchId = branchIdIn;
	}

	public String getCardProductId() {
		return this.cardProductId;
	}

	public void setCardProductId(String cardProductIdIn) {
		this.cardProductId = cardProductIdIn;
	}

	public String getPrincipleChSurname() {
		return this.principleChSurname;
	}

	public void setPrincipleChSurname(String principleChSurnameIn) {
		this.principleChSurname = principleChSurnameIn;
	}

	public String getPrincipleChName() {
		return this.principleChName;
	}

	public void setPrincipleChName(String principleChNameIn) {
		this.principleChName = principleChNameIn;
	}

	public long getPrincipleChCardNo() {
		return this.principleChCardNo;
	}

	public void setPrincipleChCardNo(long principleChCardNoIn) {
		this.principleChCardNo = principleChCardNoIn;
	}

	public String getPrincipleMobileNo() {
		return this.principleMobileNo;
	}

	public void setPrincipleMobileNo(String principleMobileNoIn) {
		this.principleMobileNo = principleMobileNoIn;
	}

	public String getPrincipleHomeNo() {
		return this.principleHomeNo;
	}

	public void setPrincipleHomeNo(String principleHomeNoIn) {
		this.principleHomeNo = principleHomeNoIn;
	}

	public String getPrincipleOfficeNo() {
		return this.principleOfficeNo;
	}

	public void setPrincipleOfficeNo(String principleOfficeNoIn) {
		this.principleOfficeNo = principleOfficeNoIn;
	}

	public String getPrincipleNRIC() {
		return this.principleNRIC;
	}

	public void setPrincipleNRIC(String principleNRIC) {
		this.principleNRIC = principleNRIC;
	}

	public String getCustomerName() {
		return this.customerName;
	}

	public void setCustomerName(String customerNameIn) {
		this.customerName = customerNameIn;
	}

	public String getSurnameName() {
		return this.surnameName;
	}

	public void setSurnameName(String surnameNameIn) {
		this.surnameName = surnameNameIn;
	}

	public String getEmbossingName() {
		return this.embossingName;
	}

	public void setEmbossingName(String embossingNameIn) {
		this.embossingName = embossingNameIn;
	}

	public Date getDob() {
		return this.dob;
	}

	public void setDob(Date dobIn) {
		this.dob = dobIn;
		if (dob != null) {
			dobForm = new DateForm(dob);
		}
	}

	public DateForm getDobForm() {
		return dobForm;
	}

	public void setDobForm(DateForm dobForm) {
		this.dobForm = dobForm;
		dob = dobForm.toDate();
	}

	public String getPob() {
		return this.pob;
	}

	public void setPob(String pobIn) {
		this.pob = pobIn;
	}

	public String getGender() {
		return this.gender;
	}

	public void setGender(String genderIn) {
		this.gender = genderIn;
	}

	public String getMaritalStatus() {
		return this.maritalStatus;
	}

	public void setMaritalStatus(String maritalStatusIn) {
		this.maritalStatus = maritalStatusIn;
	}

	public String getIdNumber() {
		return this.idNumber;
	}

	public void setIdNumber(String idNumberIn) {
		this.idNumber = idNumberIn;
	}

	public Date getIdDate() {
		return this.idDate;
	}

	public void setIdDate(Date idDateIn) {

		this.idDate = idDateIn;
		if (idDateIn != null) {
			idDateForm = new DateForm(idDateIn);
		}
	}

	public DateForm getIdDateForm() {
		return idDateForm;
	}

	public void setIdDateForm(DateForm idDateForm) {
		this.idDateForm = idDateForm;
		idDate = idDateForm.toDate();
	}

	public Date getIdExpire() {
		return this.idExpire;
	}

	public void setIdExpire(Date idExpireIn) {
		this.idExpire = idExpireIn;
		if (idExpire != null) {
			idExpireForm = new DateForm(idExpire);
		}
	}

	public DateForm getIdExpireForm() {
		return idExpireForm;
	}

	public void setIdExpireForm(DateForm idExpiryForm) {

		this.idExpireForm = idExpiryForm;
		idExpire = idExpiryForm.toDate();
	}

	public String getNationality() {
		return this.nationality;
	}

	public void setNationality(String nationalityIn) {
		this.nationality = nationalityIn;
	}

	public String getIdPlace() {
		return this.idPlace;
	}

	public void setIdPlace(String idPlaceIn) {
		this.idPlace = idPlaceIn;
	}

	public String getEmail() {
		return this.email;
	}

	public void setEmail(String emailIn) {
		this.email = emailIn;
	}

	public int getIncome() {
		return this.income;
	}

	public void setIncome(int incomeIn) {
		this.income = incomeIn;
	}

	public String getAddress1() {
		return this.address1;
	}

	public void setAddress1(String address1In) {
		this.address1 = address1In;
	}

	public String getAddress2() {
		return this.address2;
	}

	public void setAddress2(String address2In) {
		this.address2 = address2In;
	}

	public String getCity() {
		return this.city;
	}

	public void setCity(String cityIn) {
		this.city = cityIn;
	}

	public String getState() {
		return this.state;
	}

	public void setState(String stateIn) {
		this.state = stateIn;
	}

	public String getCountry() {
		return this.country;
	}

	public void setCountry(String countryIn) {
		this.country = countryIn;
	}

	public int getPostalCode() {
		return this.postalCode;
	}

	public void setPostalCode(int postalCodeIn) {
		this.postalCode = postalCodeIn;
	}

	public String getFax() {
		return this.fax;
	}

	public void setFax(String faxIn) {
		this.fax = faxIn;
	}

	public String getTel() {
		return this.tel;
	}

	public void setTel(String telIn) {
		this.tel = telIn;
	}

	public String getRemarks() {
		return this.remarks;
	}

	public void setRemarks(String remarksIn) {
		this.remarks = remarksIn;
	}

	public Date getLastUpdatedDate() {
		return lastUpdatedDate;
	}

	public void setLastUpdatedDate(Date lastUpdatedDateIn) {
		lastUpdatedDate = lastUpdatedDateIn;
		if (lastUpdatedDate != null) {
			lastUpdatedDateForm = new DateForm(lastUpdatedDate);
		}
	}

	public DateForm getLastUpdatedByForm() {
		return lastUpdatedDateForm;
	}

	public void setLastUpdatedByForm(DateForm lastUpdatedDateForm) {

		// this.lastUpdatedDate = lastUpdatedDateForm.toDate();
	}

	public AddressDto getAddress() {
		return addressDto;
	}

	public void setAddress(AddressDto addressDto) {
		try {
			this.addressDto = addressDto;
			BeanUtils.copyProperties(addressForm, addressDto);
		} catch (Exception e) {
		}
	}

	public AddressForm getAddressForm() {
		return addressForm;

	}

	public void setAddressForm(AddressForm addressForm) {
		try {
			this.addressForm = addressForm;
			BeanUtils.copyProperties(addressDto, addressForm);
		} catch (Exception e) {
		}
	}

	public CustomerAccountDto getCustomerAccountDto() {
		return customerAccountDto;
	}

	public void setCustomerAccountDto(CustomerAccountDto customerAccountDto) {
		this.customerAccountDto = customerAccountDto;
	}

	public String getCustomerId() {
		return customerId;
	}

	public void setCustomerId(String customerId) {
		this.customerId = customerId;
	}

	public int getCardProcessStatus() {
		return cardProcessStatus;
	}

	public void setCardProcessStatus(int cardProcessStatus) {
		this.cardProcessStatus = cardProcessStatus;
	}

	public int getRelationship() {
		return relationship;
	}

	public void setRelationship(int relationship) {
		this.relationship = relationship;
	}

}
