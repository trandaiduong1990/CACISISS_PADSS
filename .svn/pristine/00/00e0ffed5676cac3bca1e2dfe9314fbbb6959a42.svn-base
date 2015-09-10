package org.transinfo.cacis.formbean.customerservice;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import org.apache.struts.validator.ValidatorForm;
import org.transinfo.cacis.formbean.common.AddressForm;
import org.transinfo.cacis.util.DateUtil;

@SuppressWarnings({ "unchecked", "serial" })
public class CustomerServiceDataBean extends ValidatorForm{
	
	private String applicationId;
	private String issuerId;
	private String strOpenDate ;
	private String strCloseDate;
	private String applicationType;
	private String applicationStatus ;
	private String branchId ;
	private String customerName;
	private String surName;
	private String embossingName ;
	private String customerTypeId ;
	private String strDob ;
	private String pob ;
	private String gender;
	private String maritalStatus;
	private String dependents ;
	private String idNumber ;
	private String	strIdDate;
	private String strExpDate;
	private String idPlace;
	private String nationality ;
	private String education ;
	private String 	email;
	private String billingTo ;
	private String jobStatus ;
	private String jobStatusOthers ;
	private String jobType ;
	private String jobTypeOthers ;
	private String income ;
	private String referenceName;
	private String referencePhone ;
	private String referenceCompany ;
	private String referenceFax ;
	private String savingAccount;
	private String checkingAccount;
	private boolean atmLink;	
	private boolean supplCardRequired;
	private String supplCustomerName;
	private String supplSurName;
	private String supplEmbossingName;
	private String strSupplDob;
	private String supplPob;
	private String supplGender ;
	private String supplMaritalStatus;
	private String supplIdNumber ;
	private String strSupplIdDate;
	private String strSupplIdExpire;
	private String supplIdPlace  ;
	private String supplNationality ;
	private String supplEmail ;
	private String supplIncome;
	private String remarks;
		
	//this for cusomerAccount table
	private String amountCredited;
	private String amountDebited;
	private String accountId;
	private String creditLimit;
	private String cashLimit;
	private String cycleNo;
	private String currencyCode;
	
	//for cards
	private String customerId;
	private String cardHolderType;
	private String cardProductId;
    private String reasonCode;
    private String cardStatus;
    private String cardStatusId;
	private String cardType;
	private String cardProductName;
	private String cardNumber;
	private String cardExpDate;
	private String wrongPinCount;
	private Character pinReset;
			
	private char checkedAtmLink;	
	private char checkedSupplCardRequired;
	private Date openDate;
	private Date closeDate;
	private Date dob ;
	private Date idDate; 
	private Date expDate;
	private Date supplDob;
	private Date supplIdDate;
	private Date supplIdExpire;
	
	private String prevPayAmt;
	private String statAmt;
	private String statMinAmt;
	private String statDueDate;
	private String prevBal;

	private String outStatAmt;
	private String outStatpurchaseAmt;
	private String outStatCashAmt;
		   
  //for CustomerService request address
   AddressForm requestAddress = new AddressForm();
   private Set applicationAddress = new HashSet();
   
// this for  customer to supplementaryCardHolder Address one-to-many
	private Set customerSuppAddress = new HashSet();
	
  //this for  customer to CustomerAccount one-to-many
	private Set customerAccount = new HashSet();
	
 //this for customer to CustomerCards one-to-many
	private Set customerCards = new HashSet();
	
	private String blockReason;

	private String status;

	private String maskedCardNo;
	private String encryptedCardNo;
	
 	public CustomerServiceDataBean(){
    		
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
		
		if(isAtmLink()){
			
			setCheckedAtmLink('Y');
		}else{
			setCheckedAtmLink('N');
		}
		return checkedAtmLink;
	}
	public void setCheckedAtmLink(char checkedAtmLink) {
		this.checkedAtmLink = checkedAtmLink;
		if(checkedAtmLink=='Y'){
			this.atmLink  = true;	
			}else{
				this.atmLink = false;
			}
	}
	public char getCheckedSupplCardRequired() {
		
		if(isSupplCardRequired()){
			setCheckedSupplCardRequired('Y');
		}else{
			setCheckedSupplCardRequired('N');
		}
		return checkedSupplCardRequired;
	}
	public void setCheckedSupplCardRequired(char checkedSupplCardRequired) {
		this.checkedSupplCardRequired = checkedSupplCardRequired;
		
		if(checkedSupplCardRequired=='Y'){
			this.supplCardRequired  = true;	
			}else{
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
		
		if(getStrCloseDate()!=null){
			this.closeDate = DateUtil.convertDateStringToDate(getStrCloseDate());
		}
		return closeDate;
	}
	public void setCloseDate(Date closeDate) {
		this.closeDate = closeDate;
		if(closeDate != null){
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
		
		if(getStrDob()!= null){
			this.dob = DateUtil.convertDateStringToDate(getStrDob());
        }
		return this.dob;
	}
	public void setDob(Date dob) {
		if(dob!=null){
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
		if(getStrExpDate()!=null){
			this.expDate =DateUtil.convertDateStringToDate(getStrExpDate());
		}
		return this.expDate;
	}
	public void setExpDate(Date expDate) {
		this.expDate = expDate;
		if(expDate!=null){
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
		
		if(getStrIdDate()!=null){
			this.idDate =DateUtil.convertDateStringToDate(getStrIdDate());
		
		}
		return this.idDate;
	}
	public void setIdDate(Date idDate) {
		if(idDate!=null){
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
		System.out.println("**** ISSUER_ID in set=" +issuerId);
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
		if(openDate != null){
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
		
		if(getStrSupplDob()!=null){
			this.supplDob  = DateUtil.convertDateStringToDate(getStrSupplDob());
		}
		return this.supplDob;
	}
	public void setSupplDob(Date supplDob) {
		this.supplDob = supplDob;
		 if(supplDob!=null){
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
		if(getStrSupplIdDate()!=null){
			this.supplIdDate =DateUtil.convertDateStringToDate(getStrSupplIdDate());
		}
		return this.supplIdDate;
	}
	public void setSupplIdDate(Date supplIdDate) {
		this.supplIdDate = supplIdDate;
		if(supplIdDate!=null){
			setStrSupplIdDate(DateUtil.convertDateToDateString(supplIdDate));
		}
	}
	public Date getSupplIdExpire() {
	    if(getStrSupplIdExpire()!=null){
	    	this.supplIdExpire =DateUtil.convertDateStringToDate(getStrSupplIdExpire());
	     }
		return this.supplIdExpire;
	}
	public void setSupplIdExpire(Date supplIdExpire) {
		this.supplIdExpire = supplIdExpire;
		if(supplIdExpire!=null){
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
	

   


	public String getCashLimit() {
		return cashLimit;
	}


	public void setCashLimit(String cashLimit) {
		this.cashLimit = cashLimit;
	}


	public String getCreditLimit() {
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


	public AddressForm getRequestAddress() {
		return requestAddress;
	}


	public void setRequestAddress(AddressForm requestAddress) {
		this.requestAddress = requestAddress;
	}


	public String getCardProductName() {
		return cardProductName;
	}


	public void setCardProductName(String cardProductName) {
		this.cardProductName = cardProductName;
	}


	public String getCardType() {
		return cardType;
	}


	public void setCardType(String cardType) {
		this.cardType = cardType;
	}


	public String getReasonCode() {
		return reasonCode;
	}


	public void setReasonCode(String reasonCode) {
		this.reasonCode = reasonCode;
	}


	


	public String getCardStatus() {
		return cardStatus;
	}


	public void setCardStatus(String cardStatus) {
		this.cardStatus = cardStatus;
	}

	 public Set getApplicationAddress() {
		  /*CustomerAddressDto objDto= null;
		   try{ 
			   objDto= new CustomerAddressDto();
		   BeanUtils.copyProperties(objDto, getHomeAddress());
		       this.applicationAddress.add(objDto);
		       objDto= new CustomerAddressDto(); 
		  BeanUtils.copyProperties(objDto, getCompanyAddress());
			   this.applicationAddress.add(objDto);
		   }catch(Exception e){
			   System.out.println("Error while populating AddressData"+e.getMessage());
		   }*/
			return  this.applicationAddress;
		}


		public void setApplicationAddress(Set applicationAddress) {
			 this.applicationAddress = applicationAddress;  
			
		 	}


		public String getAccountId() {
			return accountId;
		}


		public void setAccountId(String accountId) {
			this.accountId = accountId;
		}


		public String getCardProductId() {
			return cardProductId;
		}


		public void setCardProductId(String cardProductId) {
			this.cardProductId = cardProductId;
		}


		public String getCardHolderType() {
			return cardHolderType;
		}


		public void setCardHolderType(String cardHolderType) {
			this.cardHolderType = cardHolderType;
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


		public String getCustomerId() {
			return customerId;
		}


		public void setCustomerId(String customerId) {
			this.customerId = customerId;
		}


		public String getWrongPinCount() {
			return wrongPinCount;
		}


		public void setWrongPinCount(String wrongPinCount) {
			this.wrongPinCount = wrongPinCount;
		}


		public String getCardStatusId() {
			return cardStatusId;
		}


		public void setCardStatusId(String cardStatusId) {
			this.cardStatusId = cardStatusId;
		}


		public String getAmountCredited() {
			return amountCredited;
		}


		public void setAmountCredited(String amountCredited) {
			this.amountCredited = amountCredited;
			
		}


		public String getAmountDebited() {
			return amountDebited;
		}


		public void setAmountDebited(String amountDebited) {
			this.amountDebited = amountDebited;
		}


		public String getBlockReason() {
			return blockReason;
		}


		public void setBlockReason(String blockReason) {
			this.blockReason = blockReason;
		}


		public Character getPinReset() {
			return pinReset;
		}


		public void setPinReset(Character pinReset) {
			this.pinReset = pinReset;
		}


		public String getPrevPayAmt() {
			return prevPayAmt;
		}


		public void setPrevPayAmt(String prevPayAmt) {
			this.prevPayAmt = prevPayAmt;
		}


		public String getStatAmt() {
			return statAmt;
		}


		public void setStatAmt(String statAmt) {
			this.statAmt = statAmt;
		}


		public String getStatMinAmt() {
			return statMinAmt;
		}


		public void setStatMinAmt(String statMinAmt) {
			this.statMinAmt = statMinAmt;
		}


		public String getStatDueDate() {
			return statDueDate;
		}


		public void setStatDueDate(String statDueDate) {
			this.statDueDate = statDueDate;
		}


		public String getPrevBal() {
			return prevBal;
		}


		public void setPrevBal(String prevBal) {
			this.prevBal = prevBal;
		}


		public String getOutStatAmt() {
			return outStatAmt;
		}


		public void setOutStatAmt(String outStatAmt) {
			this.outStatAmt = outStatAmt;
		}


		public String getOutStatpurchaseAmt() {
			return outStatpurchaseAmt;
		}


		public void setOutStatpurchaseAmt(String outStatpurchaseAmt) {
			this.outStatpurchaseAmt = outStatpurchaseAmt;
		}


		public String getOutStatCashAmt() {
			return outStatCashAmt;
		}


		public void setOutStatCashAmt(String outStatCashAmt) {
			this.outStatCashAmt = outStatCashAmt;
		}

		public String getStatus() {
			return status;
		}

		public void setStatus(String status) {
			this.status = status;
		}

		public String getMaskedCardNo() {
			return maskedCardNo;
		}

		public void setMaskedCardNo(String maskedCardNo) {
			this.maskedCardNo = maskedCardNo;
		}

		public String getEncryptedCardNo() {
			return encryptedCardNo;
		}

		public void setEncryptedCardNo(String encryptedCardNo) {
			this.encryptedCardNo = encryptedCardNo;
		}
		
	
}
