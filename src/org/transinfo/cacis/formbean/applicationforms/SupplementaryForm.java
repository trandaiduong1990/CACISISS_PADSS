package org.transinfo.cacis.formbean.applicationforms;

import java.util.Map;

import org.apache.struts.validator.ValidatorForm;
import org.transinfo.cacis.dataacess.DAOFactory;
import org.transinfo.cacis.dataacess.dao.applicationforms.SupplFormDAO;
import org.transinfo.cacis.formbean.common.AddressForm;
import org.transinfo.cacis.formbean.common.DateForm;

/**
 * SupplemntaryFormDto Value Object. This class is value object representing
 * database table SUPPLEMENTARY_FORMS This class is intented to be used together
 * with associated Dao object.
 */

@SuppressWarnings( { "unchecked", "serial" })
public class SupplementaryForm extends ValidatorForm {

	/**
	 * Persistent Instance variables. This data is directly mapped to the
	 * columns of database table.
	 */
	private String applicationId;
	private String issuerId;
	private String userId;
	private String applType;
	private String applStatus;
	private String branchId;
	private String cardProductId;
	private String principleChSurname;
	private String principleChName;
	private String principleChCardNo;
	private String principleMobileNo;
	private String principleHomeNo;
	private String principleOfficeNo;
	private String principleNRIC;
	private String customerName;
	private String surnameName;
	private String embossingName;
	private String dob;
	private String pob;
	private String gender;
	private String maritalStatus;
	private String relationship;
	private String idNumber;
	private String nationality;
	private String idPlace;
	private String email;
	private String income;
	private String remarks;

	// private String action;

	AddressForm addressForm = new AddressForm();

	DateForm opendate = new DateForm();
	DateForm closedate = new DateForm();
	DateForm update = new DateForm();
	DateForm birthdate = new DateForm();
	DateForm iddate = new DateForm();
	DateForm idexpiry = new DateForm();

	private Map countryList;
	private Map branchList;
	private Map cardProductList;

	private Map relationshipList;
	private Map maritalStatusList;
	private Map yearList;
	private Map monthList;
	private Map dayList;
	private Map genderList;
	private Map expiryYearList;

	/**
	 * Constructors. The first one takes no arguments and provides the most
	 * simple way to create object instance. The another one takes one argument,
	 * which is the primary key of the corresponding table.
	 */

	public SupplementaryForm() {
		// getPreListData();
	}

	/**
	 * Get- and Set-methods for persistent variables. The default behaviour does
	 * not make any checks against malformed data, so these might require some
	 * manual additions.
	 */

	/*
	 * public String getAction() { System.out.println("Action Get method");
	 * return this.action; } public void setAction(String action) {
	 * System.out.println("Action Set method"+action); this.action = action; }
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

	public DateForm getOpenDateForm() {
		return opendate;
	}

	public void setOpenDateForm(DateForm opendate) {
		this.opendate = opendate;
	}

	public DateForm getCloseDateForm() {
		return closedate;
	}

	public void setCloseDateForm(DateForm closedate) {
		this.closedate = closedate;
	}

	public String getApplType() {
		return this.applType;
	}

	public void setApplType(String applTypeIn) {
		this.applType = applTypeIn;
	}

	public String getApplStatus() {
		return this.applStatus;
	}

	public void setApplStatus(String applStatusIn) {
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

	public String getPrincipleChCardNo() {
		return this.principleChCardNo;
	}

	public void setPrincipleChCardNo(String principleChCardNoIn) {
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

	public DateForm getDobForm() {
		return birthdate;
	}

	public void setDobForm(DateForm birthdate) {
		System.out.print("setDobForm => " + birthdate);
		this.birthdate = birthdate;
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

	public String getRelationship() {
		return this.relationship;
	}

	public void setRelationship(String relationship) {
		this.relationship = relationship;
	}

	public String getIdNumber() {
		return this.idNumber;
	}

	public void setIdNumber(String idNumberIn) {
		this.idNumber = idNumberIn;
	}

	public DateForm getIdDateForm() {
		return iddate;
	}

	public void setIdDateForm(DateForm iddate) {
		this.iddate = iddate;
	}

	public DateForm getIdExpireForm() {
		return idexpiry;
	}

	public void setIdExpireForm(DateForm idexpiry) {
		this.idexpiry = idexpiry;
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

	public String getIncome() {
		return this.income;
	}

	public void setIncome(String incomeIn) {
		this.income = incomeIn;
	}

	public String getRemarks() {
		return this.remarks;
	}

	public void setRemarks(String remarksIn) {
		this.remarks = remarksIn;
	}

	public DateForm getLastUpdatedDateForm() {
		return update;
	}

	public void setLastUpdatedDateForm(DateForm update) {
		this.update = update;
	}

	public AddressForm getAddressForm() {
		return addressForm;
	}

	public void setAddressForm(AddressForm addressForm) {
		this.addressForm = addressForm;
	}

	public DateForm getBirthdate() {
		return birthdate;
	}

	public void setBirthdate(DateForm birthdate) {
		this.birthdate = birthdate;
	}

	public Map getCardProductList() {
		return cardProductList;
	}

	public void setCardProductList(Map cardProductList) {
		this.cardProductList = cardProductList;
	}

	public Map getBranchList() {
		return branchList;
	}

	public Map getMaritalStatusList() {
		return maritalStatusList;
	}

	public void setMaritalStatusList(Map maritalStatusList) {
		this.maritalStatusList = maritalStatusList;
	}

	public Map getRelationshipList() {
		return relationshipList;
	}

	public void setRelationshipList(Map relationshipList) {
		this.relationshipList = relationshipList;
	}

	public Map getYearList() {
		return yearList;
	}

	public void setYearList(Map yearList) {
		this.yearList = yearList;
	}

	public Map getMonthList() {
		return monthList;
	}

	public void setMonthList(Map monthList) {
		this.monthList = monthList;
	}

	public Map getDayList() {
		return dayList;
	}

	public void setDayList(Map dayList) {
		this.dayList = dayList;
	}

	public Map getGenderList() {
		return genderList;
	}

	public void setGenderList(Map genderList) {
		this.genderList = genderList;
	}

	public void setCountryList(Map countryList) {
		this.countryList = countryList;
	}

	public Map getCountryList() {
		return countryList;
	}

	public void setBranchList(Map branchList) {
		this.branchList = branchList;
	}

	public void getPreListData() {
		try {
			SupplFormDAO objSupplFormDAO = DAOFactory.getInstance()
					.getSupplFormDAO();
			if (countryList == null) {
				countryList = objSupplFormDAO.countryListData();
			}
			if (branchList == null) {
				branchList = objSupplFormDAO.branchListData(issuerId);
			}
			/*
			 * if(cardProductList==null) { cardProductList =
			 * objSupplFormDAO.cardProductListData(issuerId); }
			 */
			if (maritalStatusList == null) {
				maritalStatusList = objSupplFormDAO.maritalListData();
			}
			if (relationshipList == null) {
				relationshipList = objSupplFormDAO.relationshipListData();
			}

			if (yearList == null) {
				yearList = objSupplFormDAO.yearListData();
			}
			if (monthList == null) {
				monthList = objSupplFormDAO.monthListData();
			}
			if (dayList == null) {
				dayList = objSupplFormDAO.dateListData();
			}
			if (genderList == null) {
				genderList = objSupplFormDAO.genderListData();
			}
			if (expiryYearList == null) {
				expiryYearList = objSupplFormDAO.expiryYearListData();
			}

		} catch (Exception e) {
			System.out
					.println("Error while getting  PreListData in SullmentaryForm:"
							+ e);
		}
	}

	public Map getExpiryYearList() {
		return expiryYearList;
	}

	public void setExpiryYearList(Map expiryYearList) {
		this.expiryYearList = expiryYearList;
	}

}
