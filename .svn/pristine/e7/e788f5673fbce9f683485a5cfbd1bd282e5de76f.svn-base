package org.transinfo.cacis.dto.cardproduction;

import java.io.Serializable;
import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;

@SuppressWarnings( { "unchecked", "serial", "unused" })
public class ApplicationProcessDto implements Serializable {

	private String applicationId;
	private String issuerId;
	private Date openDate;
	private Date closeDate;

	private int applicationType;
	private int applicationStatus;
	private String temp;
	private String branchId;
	private String customerName;
	private String surName;
	private String embossingName;
	private String customerTypeId;
	private Date dob;
	private String pob;
	private String gender;
	private String maritalStatus;
	private int dependents;
	private String idNumber;
	private Date idDate;
	private Date expDate;
	private String idPlace;
	private String nationality;
	private String education;
	private String email;
	private String billingTo;
	private String jobStatus;
	private String jobStatusOthers;
	private String jobType;
	private String jobTypeOthers;
	private float income;
	private String companyName;

	private String referenceName;
	private String referencePhone;
	private String referenceCompany;
	private String referenceFax;
	private String savingAccount;
	private String checkingAccount;
	private char checkedAtmLink;
	private char checkedSupplCardRequired;
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
	private int relationShip;

	private String passport;

	private String remarks;
	private String userId;
	private Date updatedDate = new Date();
	private String parenetCustomerId;

	// this for Address
	private Set applicationAddress = new HashSet();

	// this for ApplicaionCardProducts
	private Map appCardProducts = new HashMap();
	// private String[] selectedAppCardProducts = {};
	private String selectedAppCardProducts;

	// this is for ApplicaionRequiredDocuments
	private Map appDocProofs = new HashMap();
	private String[] selectedAppDocuments = {};

	// this for cusomer_Limits table
	private long amtPerTranx;
	private long dailyLimitCount;
	private long dailyLimitAmt;
	private long monthlyLimitCount;
	private long monthlyLimitAmt;
	// this for cusomerAccount table
	private String customerId;
	private int customerStatus;
	private float creditLimit;
	private float cashLimit;
	private int currencyCode;
	private int cycleNo;
	private String editRemark;

	// this for Cards table CustomeId to insert SupplementaryCardHolder Id in
	// Cards Table
	private String supplmenatryId;

	// this for customer to supplementaryCardHolder Address one-to-many
	private Set customerSuppAddress = new HashSet();

	// this for customer to CustomerAccount one-to-many
	private Set customerAccount = new HashSet();

	// this for customer to CustomerCards one-to-many
	private Set customerCards = new HashSet();

	// for default account
	private Character defaultAccount;

	private Float collectoralAmt;
	private String collectoralAccount;
	private String autoPayAccount;

	private String csn;
	private String customerInitial;
	
	private String immeidateProcess;
	
	private Character checkedautoPayAccountOn;

	public ApplicationProcessDto() {

	}

	public String getCustomerId() {
		return customerId;
	}

	public void setCustomerId(String customerId) {
		this.customerId = customerId;
	}

	public Set getCustomerSuppAddress() {
		return customerSuppAddress;
	}

	public void setCustomerSuppAddress(Set customerSuppAddress) {

		this.customerSuppAddress = customerSuppAddress;

	}

	public Set getCustomerAccount() {
		return customerAccount;
	}

	public void setCustomerAccount(Set customerAccount) {
		this.customerAccount = customerAccount;
	}

	public Set getCustomerCards() {
		return customerCards;
	}

	public void setCustomerCards(Set customerCards) {
		this.customerCards = customerCards;
	}

	public float getCashLimit() {
		return cashLimit;
	}

	public void setCashLimit(float cashLimit) {
		this.cashLimit = cashLimit;
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

	public int getCycleNo() {
		return cycleNo;
	}

	public void setCycleNo(int cycleNo) {
		this.cycleNo = cycleNo;
	}

	public int getCustomerStatus() {
		return customerStatus;
	}

	public void setCustomerStatus(int customerStatus) {
		this.customerStatus = customerStatus;
	}

	public String getApplicationId() {
		return applicationId;
	}

	public void setApplicationId(String applicationId) {
		this.applicationId = applicationId;
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

	public String getCheckingAccount() {
		return checkingAccount;
	}

	public void setCheckingAccount(String checkingAccount) {
		this.checkingAccount = checkingAccount;
	}

	public Date getCloseDate() {
		return closeDate;
	}

	public void setCloseDate(Date closeDate) {
		this.closeDate = closeDate;
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

	public int getDependents() {
		return dependents;
	}

	public void setDependents(int dependents) {
		this.dependents = dependents;
	}

	public Date getDob() {
		return dob;
	}

	public void setDob(Date dob) {
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
		return expDate;
	}

	public void setExpDate(Date expDate) {
		this.expDate = expDate;
	}

	public String getGender() {
		return gender;
	}

	public void setGender(String gender) {
		this.gender = gender;
	}

	public Date getIdDate() {
		return idDate;
	}

	public void setIdDate(Date idDate) {
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

	public float getIncome() {
		return income;
	}

	public void setIncome(float income) {
		this.income = income;
	}

	public String getIssuerId() {
		return issuerId;
	}

	public void setIssuerId(String issuerId) {
		this.issuerId = issuerId;
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
		return openDate;
	}

	public void setOpenDate(Date openDate) {
		this.openDate = openDate;
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

	public String getSurName() {
		return surName;
	}

	public void setSurName(String surName) {
		this.surName = surName;
	}

	public String getTemp() {
		return temp;
	}

	public void setTemp(String temp) {
		this.temp = temp;
	}

	public Date getUpdatedDate() {
		return updatedDate;
	}

	public void setUpdatedDate(Date updatedDate) {
		this.updatedDate = updatedDate;
	}

	public char getCheckedAtmLink() {
		return checkedAtmLink;
	}

	public void setCheckedAtmLink(char checkedAtmLink) {
		this.checkedAtmLink = checkedAtmLink;
	}

	public char getCheckedSupplCardRequired() {
		return checkedSupplCardRequired;
	}

	public void setCheckedSupplCardRequired(char checkedSupplCardRequired) {
		this.checkedSupplCardRequired = checkedSupplCardRequired;
	}

	public String getSupplCustomerName() {
		return supplCustomerName;
	}

	public void setSupplCustomerName(String supplCustomerName) {
		this.supplCustomerName = supplCustomerName;
	}

	public Date getSupplDob() {
		return supplDob;
	}

	public void setSupplDob(Date supplDob) {
		this.supplDob = supplDob;
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

	public float getSupplIncome() {
		return supplIncome;
	}

	public void setSupplIncome(float supplIncome) {
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

	public Set getApplicationAddress() {
		return applicationAddress;

	}

	public void setApplicationAddress(Set applicationAddress) {
		this.applicationAddress = applicationAddress;

	}

	public Map getAppCardProducts() {
		return appCardProducts;
	}

	public void setAppCardProducts(Map appCardProducts) {
		this.appCardProducts = appCardProducts;
	}

	public Map getAppDocProofs() {
		return appDocProofs;
	}

	public void setAppDocProofs(Map appDocProofs) {
		this.appDocProofs = appDocProofs;
	}

	/*
	 * public String[] getSelectedAppCardProducts() {
	 * 
	 * String appcards[]= new String[getAppCardProducts().size()]; Set prodKeys
	 * = getAppCardProducts().keySet(); Iterator it = prodKeys.iterator(); int
	 * appcardsSize =0; while(it.hasNext()){ String cardprodId =
	 * (String)it.next(); appcards[appcardsSize]=cardprodId; appcardsSize++; }
	 * 
	 * return this.selectedAppCardProducts= appcards; }
	 * 
	 * public void setSelectedAppCardProducts(String[] selectedAppCardProducts)
	 * { this.selectedAppCardProducts = selectedAppCardProducts; for(int i=0;
	 * i<selectedAppCardProducts.length;i++) {
	 * getAppCardProducts().put(selectedAppCardProducts[i],new
	 * ApplicationCardProductsDto(getUpdatedDate(),getUserId())); } }
	 */

	public String[] getSelectedAppDocuments() {

		String appdocs[] = new String[getAppDocProofs().size()];
		Set dockeys = getAppDocProofs().keySet();
		Iterator it = dockeys.iterator();
		int appdocSize = 0;
		// for(Iterator it = ss.iterator();it.hasNext();){
		while (it.hasNext()) {
			String docId = (String) it.next();
			appdocs[appdocSize] = docId;
			appdocSize++;
		}

		return this.selectedAppDocuments = appdocs;
	}

	public void setSelectedAppDocuments(String[] selectedAppDocuments) {
		this.selectedAppDocuments = selectedAppDocuments;

		for (int i = 0; i < selectedAppDocuments.length; i++) {
			getAppDocProofs().put(selectedAppDocuments[i],
					new ApplicationDocProofDto(getUpdatedDate(), getUserId()));
		}
	}

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public long getAmtPerTranx() {
		return amtPerTranx;
	}

	public void setAmtPerTranx(long amtPerTranx) {
		this.amtPerTranx = amtPerTranx;
	}

	public long getDailyLimitAmt() {
		return dailyLimitAmt;
	}

	public void setDailyLimitAmt(long dailyLimitAmt) {
		this.dailyLimitAmt = dailyLimitAmt;
	}

	public long getDailyLimitCount() {
		return dailyLimitCount;
	}

	public void setDailyLimitCount(long dailyLimitCount) {
		this.dailyLimitCount = dailyLimitCount;
	}

	public long getMonthlyLimitAmt() {
		return monthlyLimitAmt;
	}

	public void setMonthlyLimitAmt(long monthlyLimitAmt) {
		this.monthlyLimitAmt = monthlyLimitAmt;
	}

	public long getMonthlyLimitCount() {
		return monthlyLimitCount;
	}

	public void setMonthlyLimitCount(long monthlyLimitCount) {
		this.monthlyLimitCount = monthlyLimitCount;
	}

	public String getCompanyName() {
		return companyName;
	}

	public void setCompanyName(String companyName) {
		this.companyName = companyName;
	}

	public int getRelationShip() {
		return relationShip;
	}

	public void setRelationShip(int relationShip) {
		this.relationShip = relationShip;
	}

	public String getSupplmenatryId() {
		return supplmenatryId;
	}

	public void setSupplmenatryId(String supplmenatryId) {
		this.supplmenatryId = supplmenatryId;
	}

	public String getSelectedAppCardProducts() {

		String cardprodId = "";
		Set prodKeys = getAppCardProducts().keySet();
		Iterator it = prodKeys.iterator();

		while (it.hasNext()) {
			cardprodId = (String) it.next();
		}

		return this.selectedAppCardProducts = cardprodId;
	}

	public void setSelectedAppCardProducts(String selectedAppCardProducts) {
		this.selectedAppCardProducts = selectedAppCardProducts;
		getAppCardProducts().put(selectedAppCardProducts,
				new ApplicationCardProductsDto(getUpdatedDate(), getUserId()));
	}

	public String getEditRemark() {
		return editRemark;
	}

	public void setEditRemark(String editRemark) {
		this.editRemark = editRemark;
	}

	public Character getDefaultAccount() {
		return defaultAccount;
	}

	public void setDefaultAccount(Character defaultAccount) {
		this.defaultAccount = defaultAccount;
	}

	public String getParenetCustomerId() {
		return parenetCustomerId;
	}

	public void setParenetCustomerId(String parenetCustomerId) {
		this.parenetCustomerId = parenetCustomerId;
	}

	public String getPassport() {
		return passport;
	}

	public void setPassport(String passport) {
		this.passport = passport;
	}

	public Float getCollectoralAmt() {
		return collectoralAmt;
	}

	public void setCollectoralAmt(Float collectoralAmt) {
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

	public String getImmeidateProcess() {
		return immeidateProcess;
	}

	public void setImmeidateProcess(String immeidateProcess) {
		this.immeidateProcess = immeidateProcess;
	}

	public String getCustomerInitial() {
		return customerInitial;
	}

	public void setCustomerInitial(String customerInitial) {
		this.customerInitial = customerInitial;
	}

	public Character getCheckedautoPayAccountOn() {
		return checkedautoPayAccountOn;
	}

	public void setCheckedautoPayAccountOn(Character checkedautoPayAccountOn) {
		this.checkedautoPayAccountOn = checkedautoPayAccountOn;
	}

}
