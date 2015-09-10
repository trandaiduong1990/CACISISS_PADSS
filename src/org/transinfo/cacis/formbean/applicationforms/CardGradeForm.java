package org.transinfo.cacis.formbean.applicationforms;

import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.beanutils.BeanUtils;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.validator.ValidatorActionForm;
import org.transinfo.cacis.dataacess.DAOFactory;
import org.transinfo.cacis.dataacess.dao.applicationforms.CardGradeFormDAO;
import org.transinfo.cacis.dto.cardproduction.CustomerAddressDto;
import org.transinfo.cacis.dto.common.AddressDto;
import org.transinfo.cacis.formbean.common.AddressForm;
import org.transinfo.cacis.util.DateUtil;

public class CardGradeForm extends ValidatorActionForm{
	
	
	private static final long serialVersionUID = 1L;
	private String applicationId;
	private String cardNumber;
	private String issuerId;
	private String strOpenDate ;
	private String strCloseDate;
	private String applicationType;
	private String applicationStatus ;
	private String  branchId ;
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
	private String strIdDate;
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
	private String remarks;
	private String userId;
	
		
	private Date openDate;
	private Date closeDate;
	private Date dob ;
	private Date idDate; 
	private Date expDate;
	
	
	private String   customerId;
	private String   cardHolderType;
	
	//these are for cards to split or cacel
	private String[] cardsCancelOrSplit= {};
	private String selectedCancelOrSplit;
	private String currCardCreditLimitPer;
	private String newCardCreditLimitPer;
		
	
	//Address Data
    private AddressForm homeAddress = new AddressForm();
    private AddressForm companyAddress = new AddressForm();
    private Set applicationAddress = new HashSet();
    
    //ApplicationcardProducts
     private Map appCardProductList; 
     private String[] selectedAppCardProducts = {};
     private String selectedCardProductId;
   //for Document proofs
     private Map appDocProofList; 
     private String[] selectedAppDocuments = {};
     
     //prelistdata
    private Map countryList;
 	private Map branchList;
 	private Map customerTypeList;
 	private HashMap currencyList;
    private Map genderList;
    private Map maritalStatusList;
    
 	public CardGradeForm(){
   		
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
	
	
	
	public String getSurName() {
		return surName;
	}
	public void setSurName(String surName) {
		this.surName = surName;
	}
	

   public Set getApplicationAddress() {
	   AddressDto objDto= null;
	   try{ 
		  
		   this.applicationAddress =new HashSet();
		   objDto= new AddressDto();
	   BeanUtils.copyProperties(objDto, getHomeAddress());
	       this.applicationAddress.add(objDto);
	       objDto= new AddressDto(); 
	  BeanUtils.copyProperties(objDto, getCompanyAddress());
		   this.applicationAddress.add(objDto);
		  
	   }catch(Exception e){
		   System.out.println("Error while populating AddressData in CardGradeForm"+e.getMessage());
	   }
	   
		return  this.applicationAddress;
	}


	public void setApplicationAddress(Set applicationAddress) {
		 this.applicationAddress = applicationAddress;   	
	 try{
		 for(Iterator it = applicationAddress.iterator();it.hasNext();) {
			 CustomerAddressDto addressDto = (CustomerAddressDto) it.next();
			
		  if(addressDto.getAddressType()!=null && addressDto.getAddressType().equals("H")){
			 BeanUtils.copyProperties(this.homeAddress, addressDto);
			  }
		  if(addressDto.getAddressType()!=null && addressDto.getAddressType().equals("C")){
				 BeanUtils.copyProperties(this.companyAddress, addressDto);
			  } 
				  
		 }
		 
		 }catch(Exception e){
			  System.out.println("Error while Retriving Address Data" +e.getMessage());
		 }
	}

public AddressForm getCompanyAddress() {
	//this has to come from Address_Type table
	companyAddress.setAddressType("C");
		return companyAddress;
	}


	public void setCompanyAddress(AddressForm companyAddress) {
		this.companyAddress = companyAddress;
	}


	public AddressForm getHomeAddress() {
//		this has to come from Address_Type table
		  homeAddress.setAddressType("H");
		return homeAddress;
	}


	public void setHomeAddress(AddressForm homeAddress) {
		this.homeAddress = homeAddress;
	}
	

	
	public String[] getSelectedAppCardProducts() {
         
		//this is to set the selected  value to selectedAppCardProducts[](multibox in form)
		 if(this.selectedAppCardProducts.length<=1){//this for validations in Dispatch Action Add method to select only one card
		if(getSelectedCardProductId()!=null){
			String arry[] = {getSelectedCardProductId()};
			 this.selectedAppCardProducts = arry;
		    
			}
		 }
		return selectedAppCardProducts;
	}

	public void setSelectedAppCardProducts(String[] selectedAppCardProducts) {
		this.selectedAppCardProducts = selectedAppCardProducts;
		System.out.println("TheSize form Bean is" +selectedAppCardProducts.length);
//	this is to set the selected  value to SelectedCardProductId field from selectedAppCardProducts[](multibox in form)	
		if(this.selectedAppCardProducts!=null){
		     setSelectedCardProductId(selectedAppCardProducts[0]);
			}
	}

	

	public String[] getSelectedAppDocuments() {
		return selectedAppDocuments;
	}

	public void setSelectedAppDocuments(String[] selectedAppDocuments) {
		this.selectedAppDocuments = selectedAppDocuments;
	}


	public HashMap getCurrencyList() {
		return currencyList;
	}


	public void setCurrencyList(HashMap currencyList) {
		this.currencyList = currencyList;
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

	public String getCardHolderType() {
		return cardHolderType;
	}


	public void setCardHolderType(String cardHolderType) {
		this.cardHolderType = cardHolderType;
	}


	public String getCustomerId() {
		return customerId;
	}


	public void setCustomerId(String customerId) {
		this.customerId = customerId;
	}


	public String[] getCardsCancelOrSplit() {
	
		//this is to set the selected  value to cardsCancelOrSplit[](multibox in form)
		 if( this.cardsCancelOrSplit.length<=1){//this for validations in Dispatch Action Add method to select only one
		if(getSelectedCancelOrSplit()!=null){
			String arry[] = {getSelectedCancelOrSplit()};
			 this.cardsCancelOrSplit = arry;
		    
			}
		 }
		  return cardsCancelOrSplit;
	}


	public void setCardsCancelOrSplit(String[] cardsCancelOrSplit) {
		this.cardsCancelOrSplit = cardsCancelOrSplit;
   
		//this is to set the selected  value to SelectedCancelOrSplit field from cardsCancelOrSplit[](multibox in form)
		if(this.cardsCancelOrSplit!=null){
	     setSelectedCancelOrSplit(cardsCancelOrSplit[0]);
		}
      //	 System.out.print("the assigning value from selectedCancelOrSplit  to cardsCancelOrSplit[] in CardGradeForm " + this.cardsCancelOrSplit[0]);
	}


	public String getCurrCardCreditLimitPer() {
		return currCardCreditLimitPer;
	}
	public void setCurrCardCreditLimitPer(String currCardCreditLimitPer) {
		this.currCardCreditLimitPer = currCardCreditLimitPer;
	}


	public String getNewCardCreditLimitPer() {
		return newCardCreditLimitPer;
	}


	public void setNewCardCreditLimitPer(String newCardCreditLimitPer) {
		this.newCardCreditLimitPer = newCardCreditLimitPer;
	}


	public String getSelectedCancelOrSplit() {
		return selectedCancelOrSplit;
	}


	public void setSelectedCancelOrSplit(String selectedCancelOrSplit) {
		this.selectedCancelOrSplit = selectedCancelOrSplit;
	}


	public String getSelectedCardProductId() {
		return selectedCardProductId;
	}


	public void setSelectedCardProductId(String selectedCardProductId) {
		this.selectedCardProductId = selectedCardProductId;
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

	public void getPreListData(){
		 
		try{
		   	CardGradeFormDAO objDAO =DAOFactory.getInstance().getCardGradeFormDAO();
		      		
		    if(countryList==null)  {
		    	countryList= objDAO.countryListData();
	     		   }
	     	   if(branchList==null){
	     		  branchList =objDAO.branchListData(issuerId);
			     }
	     	   if(customerTypeList==null){
	     		  customerTypeList= objDAO.customerTypeListData(issuerId);
			     }
	     	   if(appCardProductList==null)  {
	     		  appCardProductList = objDAO.cardProductListData(issuerId);
							  
				 }
	     	   if(appDocProofList==null)  {
	     		   appDocProofList = objDAO.documentProofList(issuerId);
				 
				    }
	     	   if(currencyList==null){
	     			this.setCountryList(objDAO.countryListData());
	     		  }
	     	   
	     	  if(genderList==null)  {
	               genderList = objDAO.genderListData();
	            }
	            if(maritalStatusList==null)  {
	                maritalStatusList = objDAO.maritalListData();
	            }
	    	 }catch(Exception e){
			  System.out.println("Error while getting  PreListData:"+e.getMessage());
			}
			
		}


	public Map getBranchList() {
		return branchList;
	}


	public void setCustomerTypeList(Map customerTypeList) {
		this.customerTypeList = customerTypeList;
	}
//	 Reset form fields.
	  public void reset(ActionMapping mapping, HttpServletRequest request)
		  {
		    cardNumber = null;
		   
		  }


	public Map getGenderList() {
		return genderList;
	}


	public void setGenderList(Map genderList) {
		this.genderList = genderList;
	}


	public Map getMaritalStatusList() {
		return maritalStatusList;
	}


	public void setMaritalStatusList(Map maritalStatusList) {
		this.maritalStatusList = maritalStatusList;
	}


	public Map getCustomerTypeList() {
		return customerTypeList;
	}


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
	
}
