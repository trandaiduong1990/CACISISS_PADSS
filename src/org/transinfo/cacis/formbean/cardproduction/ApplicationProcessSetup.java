package org.transinfo.cacis.formbean.cardproduction;

import java.util.Date;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;

import org.apache.commons.beanutils.BeanUtils;
import org.apache.struts.validator.ValidatorForm;
import org.transinfo.cacis.dataacess.DAOFactory;
import org.transinfo.cacis.dataacess.dao.cardproduction.ApplicationProcessDAO;
import org.transinfo.cacis.dto.common.AddressDto;
import org.transinfo.cacis.formbean.common.AddressForm;
import org.transinfo.cacis.util.DateUtil;

@SuppressWarnings({ "unchecked", "serial" })
public class ApplicationProcessSetup extends ValidatorForm {

	/*
	 * private String applicationId="1"; private String issuerId="Issuer1";
	 * private String strOpenDate ="12/04/2006"; private String strCloseDate
	 * ="12/04/2006"; private String applicationType= "1"; private String
	 * applicationStatus = "1"; private String branchId ="1"; private String
	 * customerName ="satyam"; private String surName="ddla"; private String
	 * embossingName ="reddy"; private String customerTypeId ="1"; private
	 * String strDob ="12/04/2006"; private String pob ="nizamabad"; private
	 * String gender ="M"; private String maritalStatus= "S"; private String
	 * dependents = "5"; private String idNumber ="111"; private String
	 * strIdDate ="12/04/2006"; private String strExpDate="12/04/2006"; private
	 * String idPlace ="Hyderabad"; private String nationality ="Indian";
	 * private String education ="MC"; private String email
	 * ="Satyam_java@yahoo.com"; private String billingTo ="H"; private String
	 * jobStatus ="Gd"; private String jobStatusOthers ="NA"; private String
	 * jobType ="Sw"; private String jobTypeOthers ="NA"; private String income
	 * ="3000"; private String referenceName ="YT"; private String
	 * referencePhone ="92811125"; private String referenceCompany ="JST";
	 * private String referenceFax ="92811126"; private String
	 * savingAccount="123456789"; private String checkingAccount ="987654321";
	 * private boolean atmLink =true; private boolean supplCardRequired=false;
	 * private String supplCustomerName ="narsa"; private String
	 * supplSurName="ddl" ; private String supplEmbossingName ="reddy"; private
	 * String strSupplDob="12/04/2006"; private String supplPob ="Armoor";
	 * private String supplGender ="M"; private String supplMaritalStatus ="M";
	 * private String supplIdNumber="222" ; private String
	 * strSupplIdDate="12/04/2006"; private String
	 * strSupplIdExpire="12/04/2006"; private String supplIdPlace ="Chennai" ;
	 * private String supplNationality ="Indian" ; private String supplEmail
	 * ="Narsa_teacher@yahoo.com"; private String supplIncome ="5000"; private
	 * String remarks="Lower Income"; private String userId; private Date
	 * updatedDate;
	 */
	// general
	private String applicationId;
	private String issuerId;
	private String branchId;
	private String userId;
	private String strOpenDate;
	private String strCloseDate;
	private String applicationType;
	private String applicationStatus;

	// applicant details

	private String csn;
	private String customerInitial;
	
	private String customerName;
	private String surName;
	private String embossingName;
	private String customerTypeId;
	private String strDob;
	private String pob;
	private String idNumber;
	private String strIdDate;
	private String strExpDate;
	private String idPlace;
	private String nationality;
	private String billingTo;
	// Remarks
	private String remarks;
	// Employment Details
	private String jobStatus;
	private String jobStatusOthers;
	private String jobType;
	private String jobTypeOthers;
	private String income;
	private String companyName;
	// Personal Details
	private String gender;
	private String maritalStatus;
	private String dependents;
	private String education;
	private String email;
	// Family Details
	private String referenceName;
	private String referencePhone;
	private String referenceCompany;
	private String referenceFax;
	// ATM Link
	private boolean atmLink;
	private char checkedAtmLink;
	private String savingAccount;
	private String checkingAccount;
	// Supp. Applicant Details
	private char checkedSupplCardRequired;
	private boolean supplCardRequired;
	private String supplCustomerName;
	private String supplSurName;
	private String supplEmbossingName;
	private String strSupplDob;
	private String supplPob;
	private String supplGender;
	private String supplMaritalStatus;
	private String supplIdNumber;
	private String strSupplIdDate;
	private String strSupplIdExpire;
	private String supplIdPlace;
	private String supplNationality;
	private String supplEmail;
	private String supplIncome;
	private String relationShip;

	private String passport;

	// for letters table
	private String dispatchId;

	// this for cusomer_Limits table
	private String amtPerTranx;
	private String dailyLimitCount;
	private String dailyLimitAmt;
	private String monthlyLimitCount;
	private String monthlyLimitAmt;
	// this for cusomerAccount table
	private String creditLimit;
	private String cashLimit;
	private String cycleNo;
	private String currencyCode;
	private String editRemark;

	// for cards
	private String cardNumber;
	private String cardExpDate;

	// String to Date conversion
	private Date openDate;
	private Date closeDate;
	private Date dob;
	private Date idDate;
	private Date expDate;
	private Date supplDob;
	private Date supplIdDate;
	private Date supplIdExpire;

	// Address Data
	private AddressForm homeAddress = new AddressForm();
	private AddressForm companyAddress = new AddressForm();
	private AddressForm supplementaryAddress = new AddressForm();
	private Set applicationAddress = new HashSet();

	// ApplicationcardProducts
	private Map appCardProductList;// = new TreeMap();
	// private String[] selectedAppCardProducts = {};
	private String selectedAppCardProducts;
	// for Document proofs
	private Map appDocProofList;// = new TreeMap();;
	private String[] selectedAppDocuments = {};

	// prelistdata
	private Map countryList;
	private Map currencyList;
	private Map branchList;
	private Map customerTypeList;
	private Map maritalStatusList;
	private Map genderList;
	private Map relationshipList;
	private Map educationList;
	private Map jobTypeList;
	private Map jobStatusList;
	private Map cycleNoList;

	private Map stateList;
	private Map cityList;
	private Map townshipList;

	private Map cstateList;
	private Map ccityList;
	private Map ctownshipList;

	private Map sstateList;
	private Map scityList;
	private Map stownshipList;
	
	// for default account
	private String defaultAccount = "S";

	private String collectoralAmt;
	private String collectoralAccount;
	private String autoPayAccount;
	private boolean autoPayAccountOn;
	private char checkedautoPayAccountOn;
	
	private String immeidateProcess;

	public ApplicationProcessSetup() {

	}

	public String getApplicationId() {
		return applicationId;
	}

	public void setApplicationId(String applicationId) {
		this.applicationId = applicationId;
	}

	public String getApplicationStatus() {
		return applicationStatus;
	}

	public void setApplicationStatus(String applicationStatus) {
		this.applicationStatus = applicationStatus;
	}

	public String getApplicationType() {
		return applicationType;
	}

	public void setApplicationType(String applicationType) {
		this.applicationType = applicationType;
	}

	public boolean isAtmLink() {
		return atmLink;
	}

	public void setAtmLink(boolean atmLink) {
		this.atmLink = atmLink;
	}

	public String getBillingTo() {
		return billingTo;
	}

	public void setBillingTo(String billingTo) {
		this.billingTo = billingTo;
	}

	public String getBranchId() {
		return branchId;
	}

	public void setBranchId(String branchId) {
		this.branchId = branchId;
	}

	public char getCheckedAtmLink() {

		if (isAtmLink()) {

			setCheckedAtmLink('Y');
		} else {
			setCheckedAtmLink('N');
		}
		return checkedAtmLink;
	}

	public void setCheckedAtmLink(char checkedAtmLink) {
		this.checkedAtmLink = checkedAtmLink;
		if (checkedAtmLink == 'Y') {
			this.atmLink = true;
		} else {
			this.atmLink = false;
		}
	}

	public char getCheckedSupplCardRequired() {

		if (isSupplCardRequired()) {
			setCheckedSupplCardRequired('Y');
		} else {
			setCheckedSupplCardRequired('N');
		}
		return checkedSupplCardRequired;
	}

	public void setCheckedSupplCardRequired(char checkedSupplCardRequired) {
		this.checkedSupplCardRequired = checkedSupplCardRequired;

		if (checkedSupplCardRequired == 'Y') {
			this.supplCardRequired = true;
		} else {
			this.supplCardRequired = false;
		}

	}

	public String getCheckingAccount() {
		return checkingAccount;
	}

	public void setCheckingAccount(String checkingAccount) {
		this.checkingAccount = checkingAccount;
	}

	public Date getCloseDate() {

		if (getStrCloseDate() != null) {
			this.closeDate = DateUtil
					.convertDateStringToDate(getStrCloseDate());
		}
		return closeDate;
	}

	public void setCloseDate(Date closeDate) {
		this.closeDate = closeDate;
		if (closeDate != null) {
			setStrCloseDate(DateUtil.convertDateToDateString(closeDate));

		}
	}

	public String getCustomerName() {
		return customerName;
	}

	public void setCustomerName(String customerName) {
		this.customerName = customerName;
	}

	public String getCustomerTypeId() {
		return customerTypeId;
	}

	public void setCustomerTypeId(String customerTypeId) {
		this.customerTypeId = customerTypeId;
	}

	public String getDependents() {
		return dependents;
	}

	public void setDependents(String dependents) {
		this.dependents = dependents;
	}

	public Date getDob() {

		if (getStrDob() != null) {
			this.dob = DateUtil.convertDateStringToDate(getStrDob());
		}
		return this.dob;
	}

	public void setDob(Date dob) {
		if (dob != null) {
			setStrDob(DateUtil.convertDateToDateString(dob));
		}

		this.dob = dob;
	}

	public String getEducation() {
		return education;
	}

	public void setEducation(String education) {
		this.education = education;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getEmbossingName() {
		return embossingName;
	}

	public void setEmbossingName(String embossingName) {
		this.embossingName = embossingName;
	}

	public Date getExpDate() {
		if (getStrExpDate() != null) {
			this.expDate = DateUtil.convertDateStringToDate(getStrExpDate());
		}
		return this.expDate;
	}

	public void setExpDate(Date expDate) {
		this.expDate = expDate;
		if (expDate != null) {
			setStrExpDate(DateUtil.convertDateToDateString(expDate));
		}
	}

	public String getGender() {
		return gender;
	}

	public void setGender(String gender) {
		this.gender = gender;
	}

	public Date getIdDate() {

		if (getStrIdDate() != null) {
			this.idDate = DateUtil.convertDateStringToDate(getStrIdDate());

		}
		return this.idDate;
	}

	public void setIdDate(Date idDate) {
		if (idDate != null) {
			setStrIdDate(DateUtil.convertDateToDateString(idDate));
		}
		this.idDate = idDate;
	}

	public String getIdNumber() {
		return idNumber;
	}

	public void setIdNumber(String idNumber) {
		this.idNumber = idNumber;
	}

	public String getIdPlace() {
		return idPlace;
	}

	public void setIdPlace(String idPlace) {
		this.idPlace = idPlace;
	}

	public String getIncome() {
		return income;
	}

	public void setIncome(String income) {
		this.income = income;
	}

	public String getIssuerId() {
		return issuerId;
	}

	public void setIssuerId(String issuerId) {
		this.issuerId = issuerId;
		System.out.println("**** ISSUER_ID in set=" + issuerId);
	}

	public String getJobStatus() {
		return jobStatus;
	}

	public void setJobStatus(String jobStatus) {
		this.jobStatus = jobStatus;
	}

	public String getJobStatusOthers() {
		return jobStatusOthers;
	}

	public void setJobStatusOthers(String jobStatusOthers) {
		this.jobStatusOthers = jobStatusOthers;
	}

	public String getJobType() {
		return jobType;
	}

	public void setJobType(String jobType) {
		this.jobType = jobType;
	}

	public String getJobTypeOthers() {
		return jobTypeOthers;
	}

	public void setJobTypeOthers(String jobTypeOthers) {
		this.jobTypeOthers = jobTypeOthers;
	}

	public String getMaritalStatus() {
		return maritalStatus;
	}

	public void setMaritalStatus(String maritalStatus) {
		this.maritalStatus = maritalStatus;
	}

	public String getNationality() {
		return nationality;
	}

	public void setNationality(String nationality) {
		this.nationality = nationality;
	}

	public Date getOpenDate() {
		if (getStrOpenDate() != null) {
			this.openDate = DateUtil.convertDateStringToDate(getStrOpenDate());
		}
		return openDate;
	}

	public void setOpenDate(Date openDate) {
		this.openDate = openDate;
		if (openDate != null) {
			setStrOpenDate(DateUtil.convertDateToDateString(openDate));

		}
	}

	public String getPob() {
		return pob;
	}

	public void setPob(String pob) {
		this.pob = pob;
	}

	public String getReferenceCompany() {
		return referenceCompany;
	}

	public void setReferenceCompany(String referenceCompany) {
		this.referenceCompany = referenceCompany;
	}

	public String getReferenceFax() {
		return referenceFax;
	}

	public void setReferenceFax(String referenceFax) {
		this.referenceFax = referenceFax;
	}

	public String getReferenceName() {
		return referenceName;
	}

	public void setReferenceName(String referenceName) {
		this.referenceName = referenceName;
	}

	public String getReferencePhone() {
		return referencePhone;
	}

	public void setReferencePhone(String referencePhone) {
		this.referencePhone = referencePhone;
	}

	public String getRemarks() {
		return remarks;
	}

	public void setRemarks(String remarks) {
		this.remarks = remarks;
	}

	public String getSavingAccount() {
		return savingAccount;
	}

	public void setSavingAccount(String savingAccount) {
		this.savingAccount = savingAccount;
	}

	public String getStrCloseDate() {
		return strCloseDate;
	}

	public void setStrCloseDate(String strCloseDate) {
		this.strCloseDate = strCloseDate;
	}

	public String getStrDob() {
		return strDob;
	}

	public void setStrDob(String strDob) {
		this.strDob = strDob;
	}

	public String getStrExpDate() {
		return strExpDate;
	}

	public void setStrExpDate(String strExpDate) {
		this.strExpDate = strExpDate;
	}

	public String getStrIdDate() {
		return strIdDate;
	}

	public void setStrIdDate(String strIdDate) {
		this.strIdDate = strIdDate;
	}

	public String getStrOpenDate() {
		return strOpenDate;
	}

	public void setStrOpenDate(String strOpenDate) {
		this.strOpenDate = strOpenDate;

	}

	public String getStrSupplDob() {
		return strSupplDob;
	}

	public void setStrSupplDob(String strSupplDob) {
		this.strSupplDob = strSupplDob;
	}

	public String getStrSupplIdDate() {
		return strSupplIdDate;
	}

	public void setStrSupplIdDate(String strSupplIdDate) {
		this.strSupplIdDate = strSupplIdDate;
	}

	public String getStrSupplIdExpire() {
		return strSupplIdExpire;
	}

	public void setStrSupplIdExpire(String strSupplIdExpire) {
		this.strSupplIdExpire = strSupplIdExpire;
	}

	public boolean isSupplCardRequired() {
		return supplCardRequired;
	}

	public void setSupplCardRequired(boolean supplCardRequired) {
		this.supplCardRequired = supplCardRequired;

	}

	public String getSupplCustomerName() {
		return supplCustomerName;
	}

	public void setSupplCustomerName(String supplCustomerName) {
		this.supplCustomerName = supplCustomerName;
	}

	public Date getSupplDob() {

		if (getStrSupplDob() != null) {
			this.supplDob = DateUtil.convertDateStringToDate(getStrSupplDob());
		}
		return this.supplDob;
	}

	public void setSupplDob(Date supplDob) {
		this.supplDob = supplDob;
		if (supplDob != null) {
			setStrSupplDob(DateUtil.convertDateToDateString(supplDob));
		}
	}

	public String getSupplEmail() {
		return supplEmail;
	}

	public void setSupplEmail(String supplEmail) {
		this.supplEmail = supplEmail;
	}

	public String getSupplEmbossingName() {
		return supplEmbossingName;
	}

	public void setSupplEmbossingName(String supplEmbossingName) {
		this.supplEmbossingName = supplEmbossingName;
	}

	public String getSupplGender() {
		return supplGender;
	}

	public void setSupplGender(String supplGender) {
		this.supplGender = supplGender;
	}

	public Date getSupplIdDate() {
		if (getStrSupplIdDate() != null) {
			this.supplIdDate = DateUtil
					.convertDateStringToDate(getStrSupplIdDate());
		}
		return this.supplIdDate;
	}

	public void setSupplIdDate(Date supplIdDate) {
		this.supplIdDate = supplIdDate;
		if (supplIdDate != null) {
			setStrSupplIdDate(DateUtil.convertDateToDateString(supplIdDate));
		}
	}

	public Date getSupplIdExpire() {
		if (getStrSupplIdExpire() != null) {
			this.supplIdExpire = DateUtil
					.convertDateStringToDate(getStrSupplIdExpire());
		}
		return this.supplIdExpire;
	}

	public void setSupplIdExpire(Date supplIdExpire) {
		this.supplIdExpire = supplIdExpire;
		if (supplIdExpire != null) {
			setStrSupplIdExpire(DateUtil.convertDateToDateString(supplIdExpire));
		}
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

	public String getSupplIncome() {
		return supplIncome;
	}

	public void setSupplIncome(String supplIncome) {
		this.supplIncome = supplIncome;
	}

	public String getSupplMaritalStatus() {
		return supplMaritalStatus;
	}

	public void setSupplMaritalStatus(String supplMaritalStatus) {
		this.supplMaritalStatus = supplMaritalStatus;
	}

	public String getSupplNationality() {
		return supplNationality;
	}

	public void setSupplNationality(String supplNationality) {
		this.supplNationality = supplNationality;
	}

	public String getSupplPob() {
		return supplPob;
	}

	public void setSupplPob(String supplPob) {
		this.supplPob = supplPob;
	}

	public String getSupplSurName() {
		return supplSurName;
	}

	public void setSupplSurName(String supplSurName) {
		this.supplSurName = supplSurName;
	}

	public String getSurName() {
		return surName;
	}

	public void setSurName(String surName) {
		this.surName = surName;
	}

	public Set getApplicationAddress() {
		/*
		 * CustomerAddressDto objDto= null; try{ objDto= new
		 * CustomerAddressDto(); BeanUtils.copyProperties(objDto,
		 * getHomeAddress()); this.applicationAddress.add(objDto); objDto= new
		 * CustomerAddressDto(); BeanUtils.copyProperties(objDto,
		 * getCompanyAddress()); this.applicationAddress.add(objDto); objDto=
		 * new CustomerAddressDto(); BeanUtils.copyProperties(objDto,
		 * getSupplementaryAddress()); this.applicationAddress.add(objDto);
		 * }catch(Exception e){
		 * System.out.println("Error while populating AddressData"
		 * +e.getMessage()); }
		 */
		return this.applicationAddress;
	}

	public void setApplicationAddress(Set applicationAddress) {
		this.applicationAddress = applicationAddress;
		try {

			for (Iterator it = applicationAddress.iterator(); it.hasNext();) {
				AddressDto addressDto = (AddressDto) it.next();
				if (addressDto.getAddressType() != null
						&& addressDto.getAddressType().equals("H")) {
					BeanUtils.copyProperties(this.homeAddress, addressDto);
				}
				if (addressDto.getAddressType() != null
						&& addressDto.getAddressType().equals("C")) {
					BeanUtils.copyProperties(this.companyAddress, addressDto);
				}
				if (addressDto.getAddressType() != null
						&& addressDto.getAddressType().equals("S")) {
					BeanUtils.copyProperties(this.supplementaryAddress,
							addressDto);
				}
			}
			/*
			 * for(Iterator it = applicationAddress.iterator();it.hasNext();) {
			 * CustomerAddressDto addressDto = (CustomerAddressDto) it.next();
			 * if(addressDto.getAddressType()!=null &&
			 * addressDto.getAddressType().equals("H")){
			 * BeanUtils.copyProperties(this.homeAddress, addressDto); }
			 * if(addressDto.getAddressType()!=null &&
			 * addressDto.getAddressType().equals("C")){
			 * BeanUtils.copyProperties(this.companyAddress, addressDto); }
			 * if(addressDto.getAddressType()!=null &&
			 * addressDto.getAddressType().equals("S")){
			 * BeanUtils.copyProperties(this.supplementaryAddress, addressDto);
			 * }
			 * 
			 * }
			 */

		} catch (Exception e) {
			System.out.println("Error while Retriving Address Data"
					+ e.getMessage());
		}
	}

	public AddressForm getCompanyAddress() {
		// this has to come from Address_Type table
		companyAddress.setAddressType("C");
		return companyAddress;
	}

	public void setCompanyAddress(AddressForm companyAddress) {
		this.companyAddress = companyAddress;
	}

	public AddressForm getHomeAddress() {
		// this has to come from Address_Type table
		homeAddress.setAddressType("H");
		return homeAddress;
	}

	public void setHomeAddress(AddressForm homeAddress) {
		this.homeAddress = homeAddress;
	}

	public AddressForm getSupplementaryAddress() {
		supplementaryAddress.setAddressType("S");
		return supplementaryAddress;
	}

	public void setSupplementaryAddress(AddressForm supplementaryAddress) {
		this.supplementaryAddress = supplementaryAddress;
	}

	/*
	 * public String[] getSelectedAppCardProducts() { return
	 * selectedAppCardProducts; }
	 * 
	 * public void setSelectedAppCardProducts(String[] selectedAppCardProducts)
	 * { this.selectedAppCardProducts = selectedAppCardProducts; }
	 */

	public Map getAppCardProductList() {
		return appCardProductList;
	}

	public void setAppCardProductList(Map appCardProductList) {
		this.appCardProductList = appCardProductList;
	}

	public Map getAppDocProofList() {
		return appDocProofList;
	}

	public void setAppDocProofList(Map appDocProofList) {
		this.appDocProofList = appDocProofList;
	}

	public String getDispatchId() {
		return dispatchId;
	}

	public void setDispatchId(String dispatchId) {
		this.dispatchId = dispatchId;
	}

	public String[] getSelectedAppDocuments() {
		return selectedAppDocuments;
	}

	public void setSelectedAppDocuments(String[] selectedAppDocuments) {
		this.selectedAppDocuments = selectedAppDocuments;
	}

	public String getCashLimit() {
		return cashLimit;
	}

	public void setCashLimit(String cashLimit) {
		this.cashLimit = cashLimit;
	}

	public String getCreditLimit() {
		if(creditLimit==null||"0.0".equals(creditLimit)||"".equals(creditLimit)||"0".equals(creditLimit)){
			this.creditLimit="0";
		}
		else{
		float f=new Float(creditLimit);
		creditLimit=String.valueOf((int)(f));
		}
		return creditLimit;
	}

	public void setCreditLimit(String creditLimit) {
		this.creditLimit = creditLimit;
	}

	public String getCurrencyCode() {
		return currencyCode;
	}

	public void setCurrencyCode(String currencyCode) {
		this.currencyCode = currencyCode;
		System.out.println("in form CurrencyCode" + currencyCode);
	}

	public String getCycleNo() {
		return cycleNo;
	}

	public void setCycleNo(String cycleNo) {
		this.cycleNo = cycleNo;
	}

	public String getCardExpDate() {
		return cardExpDate;
	}

	public void setCardExpDate(String cardExpDate) {
		this.cardExpDate = cardExpDate;
	}

	public String getCardNumber() {
		return cardNumber;
	}

	public void setCardNumber(String cardNumber) {
		this.cardNumber = cardNumber;
	}

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public String getAmtPerTranx() {
		return amtPerTranx;
	}

	public void setAmtPerTranx(String amtPerTranx) {
		this.amtPerTranx = amtPerTranx;
	}

	public String getDailyLimitAmt() {
		return dailyLimitAmt;
	}

	public void setDailyLimitAmt(String dailyLimitAmt) {
		this.dailyLimitAmt = dailyLimitAmt;
	}

	public String getDailyLimitCount() {
		return dailyLimitCount;
	}

	public void setDailyLimitCount(String dailyLimitCount) {
		this.dailyLimitCount = dailyLimitCount;
	}

	public String getMonthlyLimitAmt() {
		return monthlyLimitAmt;
	}

	public void setMonthlyLimitAmt(String monthlyLimitAmt) {
		this.monthlyLimitAmt = monthlyLimitAmt;
	}

	public String getMonthlyLimitCount() {
		return monthlyLimitCount;
	}

	public void setMonthlyLimitCount(String monthlyLimitCount) {
		this.monthlyLimitCount = monthlyLimitCount;
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
			ApplicationProcessDAO objDAO = DAOFactory.getInstance()
					.getApplicationProcessDAO();
			if (countryList == null) {
				countryList = objDAO.countryListData();
			}
			if (branchList == null) {
				branchList = objDAO.branchListData(issuerId);
			}
			if (customerTypeList == null) {
				customerTypeList = objDAO.customerTypeListData(issuerId);
			}

			if (maritalStatusList == null) {
				maritalStatusList = objDAO.maritalListData();
			}
			if (relationshipList == null) {
				relationshipList = objDAO.relationshipListData();
			}
			if (genderList == null) {
				genderList = objDAO.genderListData();
			}
			if (educationList == null) {
				educationList = objDAO.educationListData();
			}

			if (jobTypeList == null) {
				jobTypeList = objDAO.jobTypeListData();
			}

			if (jobStatusList == null) {
				jobStatusList = objDAO.jobStatusListData();
			}

			if (relationshipList == null) {
				relationshipList = objDAO.relationshipListData();
			}

			if (appCardProductList == null) {
				appCardProductList = objDAO.cardProductListData(issuerId);
			}
			if (appDocProofList == null) {
				appDocProofList = objDAO.documentProofList(issuerId);
			}

			if (currencyList == null) {
				currencyList = objDAO.issuerCurrencyListData();
			}
			
			if (cycleNoList == null) {
				cycleNoList = objDAO.cycleNoListData();
			}
		} catch (Exception e) {
			System.out
					.println("Error while getting  PreListData in ApplicationProcessSetup:"
							+ e.getMessage());
		}

	}

	public Map getBranchList() {
		return branchList;
	}

	public void setCustomerTypeList(Map customerTypeList) {
		this.customerTypeList = customerTypeList;
	}

	public Map getCustomerTypeList() {
		return customerTypeList;
	}

	public void setCurrencyList(Map currencyList) {
		this.currencyList = currencyList;
	}

	public Map getCurrencyList() {
		return currencyList;
	}

	public Map getEducationList() {
		return educationList;
	}

	public void setEducationList(Map educationList) {
		this.educationList = educationList;
	}

	public Map getGenderList() {
		return genderList;
	}

	public void setGenderList(Map genderList) {
		this.genderList = genderList;
	}

	public Map getJobStatusList() {
		return jobStatusList;
	}

	public void setJobStatusList(Map jobStatusList) {
		this.jobStatusList = jobStatusList;
	}

	public Map getJobTypeList() {
		return jobTypeList;
	}

	public void setJobTypeList(Map jobTypeList) {
		this.jobTypeList = jobTypeList;
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

	public String getRelationShip() {
		return relationShip;
	}

	public void setRelationShip(String relationShip) {
		this.relationShip = relationShip;
	}

	public String getCompanyName() {
		return companyName;
	}

	public void setCompanyName(String companyName) {
		this.companyName = companyName;
	}

	public String getSelectedAppCardProducts() {
		return selectedAppCardProducts;
	}

	public void setSelectedAppCardProducts(String selectedAppCardProducts) {
		this.selectedAppCardProducts = selectedAppCardProducts;
	}

	public String getEditRemark() {
		return editRemark;
	}

	public void setEditRemark(String editRemark) {
		this.editRemark = editRemark;
	}

	public Map getCycleNoList() {
		return cycleNoList;
	}

	public void setCycleNoList(Map cycleNoList) {
		this.cycleNoList = cycleNoList;
	}

	public String getDefaultAccount() {
		return defaultAccount;
	}

	public void setDefaultAccount(String defaultAccount) {
		this.defaultAccount = defaultAccount;
	}

	public String getPassport() {
		return passport;
	}

	public void setPassport(String passport) {
		this.passport = passport;
	}

	public Map getStateList() {
		return stateList;
	}

	public void setStateList(Map stateList) {
		this.stateList = stateList;
	}

	public Map getCityList() {
		return cityList;
	}

	public void setCityList(Map cityList) {
		this.cityList = cityList;
	}

	public Map getTownshipList() {
		return townshipList;
	}

	public void setTownshipList(Map townshipList) {
		this.townshipList = townshipList;
	}

	public Map getCstateList() {
		return cstateList;
	}

	public void setCstateList(Map cstateList) {
		this.cstateList = cstateList;
	}

	public Map getCcityList() {
		return ccityList;
	}

	public void setCcityList(Map ccityList) {
		this.ccityList = ccityList;
	}

	public Map getCtownshipList() {
		return ctownshipList;
	}

	public void setCtownshipList(Map ctownshipList) {
		this.ctownshipList = ctownshipList;
	}

	public Map getSstateList() {
		return sstateList;
	}

	public void setSstateList(Map sstateList) {
		this.sstateList = sstateList;
	}

	public Map getScityList() {
		return scityList;
	}

	public void setScityList(Map scityList) {
		this.scityList = scityList;
	}

	public Map getStownshipList() {
		return stownshipList;
	}

	public void setStownshipList(Map stownshipList) {
		this.stownshipList = stownshipList;
	}

	public String getCollectoralAmt() {
		if(collectoralAmt==null||"0.0".equals(collectoralAmt)||"".equals(collectoralAmt)||"0".equals(collectoralAmt)){
			this.collectoralAmt="0";
		}
		else{
		float f=new Float(collectoralAmt);
		collectoralAmt=String.valueOf((int)(f));
		}
		//System.out.println("++++++++++++collectoralAmt+++++++get++Aftr++++++++"+collectoralAmt);
		return collectoralAmt;
	}

	public void setCollectoralAmt(String collectoralAmt) {
		this.collectoralAmt = collectoralAmt;
	}

	public String getCollectoralAccount() {
		return collectoralAccount;
	}

	public void setCollectoralAccount(String collectoralAccount) {
		this.collectoralAccount = collectoralAccount;
	}

	public String getAutoPayAccount() {
		return autoPayAccount;
	}

	public void setAutoPayAccount(String autoPayAccount) {
		this.autoPayAccount = autoPayAccount;
	}

	public String getCsn() {
		return csn;
	}

	public void setCsn(String csn) {
		this.csn = csn;
	}

	public String getCustomerInitial() {
		return customerInitial;
	}

	public void setCustomerInitial(String customerInitial) {
		this.customerInitial = customerInitial;
	}

	public String getImmeidateProcess() {
		return immeidateProcess;
	}

	public void setImmeidateProcess(String immeidateProcess) {
		this.immeidateProcess = immeidateProcess;
	}

	public char getCheckedautoPayAccountOn() {
		
		if (isAutoPayAccountOn()) {
			setCheckedautoPayAccountOn('Y');
		} else {
			setCheckedautoPayAccountOn('N');
		}
		
		return checkedautoPayAccountOn;
	}

	public void setCheckedautoPayAccountOn(char checkedautoPayAccountOn) {
		this.checkedautoPayAccountOn = checkedautoPayAccountOn;
		if (checkedautoPayAccountOn == 'Y') {
			this.autoPayAccountOn = true;
		} else {
			this.autoPayAccountOn = false;
		}
	}

	public boolean isAutoPayAccountOn() {
		return autoPayAccountOn;
	}

	public void setAutoPayAccountOn(boolean autoPayAccountOn) {
		this.autoPayAccountOn = autoPayAccountOn;
	}

}
