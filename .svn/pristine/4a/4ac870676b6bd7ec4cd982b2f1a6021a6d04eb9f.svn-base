package org.transinfo.cacis.dto.applicationforms;

import java.io.Serializable;
import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;

import org.transinfo.cacis.dto.cardproduction.ApplicationDocProofDto;
import org.transinfo.cacis.dto.cardproduction.CustomerAccountDto;

public class CardGradeFormDto  implements Serializable {
	
	private String applicationId;
	private long cardNumber;
	private String issuerId;
	private Date openDate=new Date();
    private Date closeDate;
	
	private int applicationType;
	private int applicationStatus;
	private String temp;
	private String  branchId;
	private String customerName;
	private String surName;
	private String embossingName ;
	private String customerTypeId ;
	private Date dob;
	private String pob ;
	private String gender ;
	private String maritalStatus;
	private int dependents;
	private String idNumber;
	private Date	idDate ;
	private Date expDate ;
	private String idPlace ;
	private String nationality ;
	private String education ;
	private String 	email;
	private String billingTo;
	private String jobStatus ;
	private String jobStatusOthers ;
	private String jobType;
	private String jobTypeOthers ;
	private float income;
	private String referenceName;
	private String referencePhone ;
	private String referenceCompany ;
	private String referenceFax ;
	private String savingAccount;
	private String checkingAccount ;
	private String remarks;
	private String userId="Admin";
	private Date updatedDate = new Date();
	
    //for cards table		
	private String customerId;
	//To insert cards table from constants(CardStatus)
	private int cardStatusId;
   //to insert cards_embosing table from constants(CardProcess Status)
	private int cardProcessStatus;
   //new Card Effective Date	
	private  Date effectiveDate = new Date();
	private int    cardHolderType;
	
	//this for when application creation time to use split or cancel
	private int currCardCreditLimitPer;
	private int newCardCreditLimitPer;
	private String selectedCancelOrSplit;
	//this is for if choose cacel to cacel the all cards
	private String cardsToCancel[]={};
	
	//this for Address
	private Set applicationAddress = new HashSet();
	
	//this for ApplicaionCardProducts
	//private Map appCardProducts = new HashMap();
   // private String[] selectedAppCardProducts = {};
    private String selectedCardProductId;
	 
	//this is for ApplicaionRequiredDocuments
	private Map appDocProofs = new HashMap();
	private String[] selectedAppDocuments = {};
	    
	//this for Cards to CustomerAccount many-to-one
	 private CustomerAccountDto customerAccountDto;
	
	public CardGradeFormDto(){
		
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

	
	

	public Set getApplicationAddress() {
		 return applicationAddress;
	
	}

	public void setApplicationAddress(Set applicationAddress) {
		 
		this.applicationAddress = applicationAddress;
		
			
	}



	public Map getAppDocProofs() {
		return appDocProofs;
	}

	public void setAppDocProofs(Map appDocProofs) {
		this.appDocProofs = appDocProofs;
	}

	/*	public Map getAppCardProducts() {
	return appCardProducts;
   }

   public void setAppCardProducts(Map appCardProducts) {
	this.appCardProducts = appCardProducts;
    }
	public String[] getSelectedAppCardProducts() {
		
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
	}*/

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


	public long getCardNumber() {
		return cardNumber;
	}


	public void setCardNumber(long cardNumber) {
		this.cardNumber = cardNumber;
	}


	public String getUserId() {
		return userId;
	}


	public void setUserId(String userId) {
		this.userId = userId;
	}


	public String[] getCardsToCancel() {
		return cardsToCancel;
	}


	public void setCardsToCancel(String[] cardsToCancel) {
		this.cardsToCancel = cardsToCancel;
	}


	public int getCardHolderType() {
		return cardHolderType;
	}


	public void setCardHolderType(int cardHolderType) {
		this.cardHolderType = cardHolderType;
	}




	public String getCustomerId() {
		return customerId;
	}


	public void setCustomerId(String customerId) {
		this.customerId = customerId;
	}


	public CustomerAccountDto getCustomerAccountDto() {
		return customerAccountDto;
	}


	public void setCustomerAccountDto(CustomerAccountDto customerAccountDto) {
		this.customerAccountDto = customerAccountDto;
	}


	public String getSelectedCancelOrSplit() {
		return selectedCancelOrSplit;
	}


	public void setSelectedCancelOrSplit(String selectedCancelOrSplit) {
		this.selectedCancelOrSplit = selectedCancelOrSplit;
	}


	public int getCurrCardCreditLimitPer() {
		return currCardCreditLimitPer;
	}


	public void setCurrCardCreditLimitPer(int currCardCreditLimitPer) {
		this.currCardCreditLimitPer = currCardCreditLimitPer;
	}


	public int getNewCardCreditLimitPer() {
		return newCardCreditLimitPer;
	}


	public void setNewCardCreditLimitPer(int newCardCreditLimitPer) {
		this.newCardCreditLimitPer = newCardCreditLimitPer;
	}


	public String getSelectedCardProductId() {
		return selectedCardProductId;
	}


	public void setSelectedCardProductId(String selectedCardProductId) {
		this.selectedCardProductId = selectedCardProductId;
	}




	public int getCardStatusId() {
		return cardStatusId;
	}


	public void setCardStatusId(int cardStatusId) {
		this.cardStatusId = cardStatusId;
	}


	public int getCardProcessStatus() {
		return cardProcessStatus;
	}


	public void setCardProcessStatus(int cardProcessStatus) {
		this.cardProcessStatus = cardProcessStatus;
	}


	public Date getEffectiveDate() {
		return effectiveDate;
	}


	public void setEffectiveDate(Date effectiveDate) {
		this.effectiveDate = effectiveDate;
	}


	


	



	 
	
}
