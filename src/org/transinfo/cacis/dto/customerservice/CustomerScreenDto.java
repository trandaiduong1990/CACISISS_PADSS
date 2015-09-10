package org.transinfo.cacis.dto.customerservice;

import java.io.Serializable;
import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;

import org.transinfo.cacis.dto.cardproduction.ApplicationCardProductsDto;
import org.transinfo.cacis.dto.cardproduction.ApplicationDocProofDto;

@SuppressWarnings("unchecked")
public class CustomerScreenDto  implements Serializable {
	
	
	private static final long serialVersionUID = 1L;
	
	private String applicationId;
	private String issuerId;
	private String userId;
	private Date updatedDate = new Date();
	private String  branchId;
	private String customerTypeId ;
	
	private  Date openDate;
	private Date closeDate;	
	private int applicationType;
	private int applicationStatus;
	private String temp;

	//	applicant details
	private String customerName;
	private String surName;
	private String embossingName ;
	private Date dob;
	private String pob ;
	private String idNumber;
	private Date	idDate ;
	private Date expDate ;
	private String idPlace ;
	private String nationality ;
	private String billingTo;
	//remarks
	private String remarks;
//	Employment Details 
	private String companyName;
	private String jobStatus ;
	private String jobStatusOthers ;
	private String jobType;
	private String jobTypeOthers ;
	private float income;
//	Personal Details 
	private String gender ;
	private String maritalStatus;
	private int dependents;
	private String education ;
	private String 	email;
//	Family Details 
	private String referenceName;
	private String referencePhone ;
	private String referenceCompany ;
	private String referenceFax ;
//	ATM Link 
	private char checkedAtmLink;
	private String savingAccount;
	private String checkingAccount ;
//	Supp. Applicant Details
	private char checkedSupplCardRequired;
	private String supplCustomerName;
	private String supplSurName ;
	private String supplEmbossingName;
	private Date supplDob ;
	private String  supplPob;
	private String  supplGender;
	private String  supplMaritalStatus;
	private String supplIdNumber ;
	private Date supplIdDate;
	private Date supplIdExpire ;
	private String supplIdPlace  ;
	private String supplNationality  ;
	private String supplEmail;
	private float supplIncome;
    private int relationShip;

	private Date dateTime = new Date();
	//this for Address
	private Set applicationAddress = new HashSet();
	
	//this for ApplicaionCardProducts
	private Map appCardProducts = new HashMap();
    //private String[] selectedAppCardProducts = {};

    private String selectedAppCardProducts;
	 
	//this is for ApplicaionRequiredDocuments
	private Map appDocProofs = new HashMap();
	private String[] selectedAppDocuments = {};
	
	//for Excell sheet
	private String referenceId;
	
	
	public String getReferenceId() {
		return referenceId;
	}

	public void setReferenceId(String referenceId) {
		this.referenceId = referenceId;
	}

	public CustomerScreenDto(){}

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

/*	public String[] getSelectedAppCardProducts() {
		
		   String appcards[]= new String[getAppCardProducts().size()];
		   Set prodKeys = getAppCardProducts().keySet();
		   Iterator it = prodKeys.iterator();
		   int appcardsSize =0;
		   while(it.hasNext()){
		   String cardprodId = (String)it.next();
		   appcards[appcardsSize]=cardprodId;
		   appcardsSize++;
		   } 
		
		return this.selectedAppCardProducts= appcards;
	}

	public void setSelectedAppCardProducts(String[] selectedAppCardProducts) {
		this.selectedAppCardProducts = selectedAppCardProducts;
		for(int i=0; i<selectedAppCardProducts.length;i++)
			
	     {
			  	getAppCardProducts().put(selectedAppCardProducts[i],new ApplicationCardProductsDto(getUpdatedDate(),getUserId()));
		}
	}
*/
	public String[] getSelectedAppDocuments() {
		 
		String appdocs[]= new String[getAppDocProofs().size()];
		   Set dockeys = getAppDocProofs().keySet();
		   Iterator it = dockeys.iterator();
		   int appdocSize =0;
		//for(Iterator it = ss.iterator();it.hasNext();){
		   while(it.hasNext()){
		   String docId = (String)it.next();
		   appdocs[appdocSize]=docId;
		    appdocSize++;
		   } 
		
		return this.selectedAppDocuments =appdocs;
	}

	public void setSelectedAppDocuments(String[] selectedAppDocuments) {
		this.selectedAppDocuments = selectedAppDocuments;
		
		for(int i=0; i<selectedAppDocuments.length;i++)
	      {
	     	getAppDocProofs().put(selectedAppDocuments[i],new ApplicationDocProofDto(getUpdatedDate(),getUserId()));
			}
	}



	public String getUserId() {
		return this.userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
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

	public String getSelectedAppCardProducts() {
		
		String cardprodId = "";
		Set prodKeys = getAppCardProducts().keySet();
		Iterator it = prodKeys.iterator();

		while(it.hasNext()){
			cardprodId = (String)it.next();
		} 
	
		return this.selectedAppCardProducts= cardprodId;
	}

	public void setSelectedAppCardProducts(String selectedAppCardProducts) {
		this.selectedAppCardProducts = selectedAppCardProducts;
		getAppCardProducts().put(selectedAppCardProducts,new ApplicationCardProductsDto(getUpdatedDate(),getUserId()));
	}

	public Date getDateTime() {
		return dateTime;
	}

	public void setDateTime(Date dateTime) {
		this.dateTime = dateTime;
	}

	

	

	
	

}
