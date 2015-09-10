package org.transinfo.cacis.formbean.disputemanagement;

import java.util.Map;

import org.apache.struts.validator.ValidatorForm;
import org.transinfo.cacis.dataacess.DAOFactory;
import org.transinfo.cacis.dataacess.dao.disputemanagement.DisputeClaimFormDAO;

public class DisputeClaimSetupForm extends ValidatorForm {
	
	
	private static final long serialVersionUID = 1L;
	private String settlementId;
	private String claimNumber;
	private String issuerId;
	private String claimTypeId;
	private String claimReasonCode;
	private String transactionDone;
	private String cardNumber;
	private String customerName;
	private String status;
	private String address1;
	private String address2;
	private String city;
	private String state;
	private String country;
	private String postalCode;
	private String phoneNumber;
	private String faxNumber;
	private String originalDisputeNo;
	private String parentDisputeNo;
	private String remarks;
	private String userId;
	
	
	//for customer details
	private String nricNo;
	private String dateOfBirth;
	private String nricExpire;
	//for tranx details
	private String merchantName;
	private String tranxDate;
	private String referenceNo;
	private String tranxAmt;
	private String tranxCurr;
	private String settlementAmt;
	private String settlementCurr;
  
	//Documents Submitted to Claim 
    private Map docsMandatoryList; 
    private String[] selectedMandatoryDocs = {};
    private Map docsNonMandatoryList; 
    private String[] selectedNonMandatoryDocs = {};
    private String nubmerOfDocs;
    //prelistdata
	private Map countryList;
	private Map claimTypeList;
	private Map claimReasonList;
	
	public DisputeClaimSetupForm(){}

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

	public String getCardNumber() {
		return cardNumber;
	}

	public void setCardNumber(String cardNumber) {
		this.cardNumber = cardNumber;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public String getClaimNumber() {
		return claimNumber;
	}

	public void setClaimNumber(String claimNumber) {
		this.claimNumber = claimNumber;
	}

	public String getClaimTypeId() {
		return claimTypeId;
	}

	public void setClaimTypeId(String claimTypeId) {
		this.claimTypeId = claimTypeId;
	}

	public String getCountry() {
		return country;
	}

	public void setCountry(String country) {
		this.country = country;
	}

	public String getCustomerName() {
		return customerName;
	}

	public void setCustomerName(String customerName) {
		this.customerName = customerName;
	}

	public String getFaxNumber() {
		return faxNumber;
	}

	public void setFaxNumber(String faxNumber) {
		this.faxNumber = faxNumber;
	}

	public String getIssuerId() {
		return issuerId;
	}

	public void setIssuerId(String issuerId) {
		this.issuerId = issuerId;
	}

	public String getOriginalDisputeNo() {
		return originalDisputeNo;
	}

	public void setOriginalDisputeNo(String originalDisputeNo) {
		this.originalDisputeNo = originalDisputeNo;
	}

	public String getParentDisputeNo() {
		return parentDisputeNo;
	}

	public void setParentDisputeNo(String parentDisputeNo) {
		this.parentDisputeNo = parentDisputeNo;
	}

	public String getPhoneNumber() {
		return phoneNumber;
	}

	public void setPhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
	}

	public String getPostalCode() {
		return postalCode;
	}

	public void setPostalCode(String postalCode) {
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

	public String getTransactionDone() {
		return transactionDone;
	}

	public void setTransactionDone(String transactionDone) {
		this.transactionDone = transactionDone;
	}

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public Map getClaimReasonList() {
		return claimReasonList;
	}

	public void setClaimReasonList(Map claimReasonList) {
		this.claimReasonList = claimReasonList;
	}

	public Map getClaimTypeList() {
		return claimTypeList;
	}

	public void setClaimTypeList(Map claimTypeList) {
		this.claimTypeList = claimTypeList;
	}

	public Map getCountryList() {
		return countryList;
	}

	public void setCountryList(Map countryList) {
		this.countryList = countryList;
	}
	

	public String getClaimReasonCode() {
		return claimReasonCode;
	}

	public void setClaimReasonCode(String claimReasonCode) {
		this.claimReasonCode = claimReasonCode;
	}

	

	public String[] getSelectedMandatoryDocs() {
		return selectedMandatoryDocs;
	}

	public void setSelectedMandatoryDocs(String[] selectedMandatoryDocs) {
		this.selectedMandatoryDocs = selectedMandatoryDocs;
	}

	public String[] getSelectedNonMandatoryDocs() {
		return selectedNonMandatoryDocs;
	}

	public void setSelectedNonMandatoryDocs(String[] selectedNonMandatoryDocs) {
		this.selectedNonMandatoryDocs = selectedNonMandatoryDocs;
	}

	public String getSettlementId() {
		return settlementId;
	}

	public void setSettlementId(String settlementId) {
		this.settlementId = settlementId;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getDateOfBirth() {
		return dateOfBirth;
	}

	public void setDateOfBirth(String dateOfBirth) {
		this.dateOfBirth = dateOfBirth;
	}

	public String getMerchantName() {
		return merchantName;
	}

	public void setMerchantName(String merchantName) {
		this.merchantName = merchantName;
	}

	public String getNricExpire() {
		return nricExpire;
	}

	public void setNricExpire(String nricExpire) {
		this.nricExpire = nricExpire;
	}

	public String getNricNo() {
		return nricNo;
	}

	public void setNricNo(String nricNo) {
		this.nricNo = nricNo;
	}

	public String getReferenceNo() {
		return referenceNo;
	}

	public void setReferenceNo(String referenceNo) {
		this.referenceNo = referenceNo;
	}

	public String getSettlementAmt() {
		return settlementAmt;
	}

	public void setSettlementAmt(String settlementAmt) {
		this.settlementAmt = settlementAmt;
	}

	public String getSettlementCurr() {
		return settlementCurr;
	}

	public void setSettlementCurr(String settlementCurr) {
		this.settlementCurr = settlementCurr;
	}

	public String getTranxAmt() {
		return tranxAmt;
	}

	public void setTranxAmt(String tranxAmt) {
		this.tranxAmt = tranxAmt;
	}

	public String getTranxCurr() {
		return tranxCurr;
	}

	public void setTranxCurr(String tranxCurr) {
		this.tranxCurr = tranxCurr;
	}

	public String getTranxDate() {
		return tranxDate;
	}

	public void setTranxDate(String tranxDate) {
		this.tranxDate = tranxDate;
	}

	public void getPreListData(){
		 
		try{
			DisputeClaimFormDAO objDAO= DAOFactory.getInstance().getDisputeClaimFormDAO();
			
	     		if(countryList==null)  {
	     			countryList= objDAO.countryListData();
			    }
	     		if(claimTypeList==null)  {
	     			claimTypeList= objDAO.claimTypeListData();
			    }
	     		if(claimReasonList==null)  {
	     			claimReasonList= objDAO.reasonCodeListData();
			    }
	       
	    	 }catch(Exception e){
			    System.out.println("Error while getting  PreListData in DisputeClaimFormSetup:"+e.getMessage());
			}
			
		}
//this to get the PreList when select reason code
	public void getDocPreListData(){
		 
		try{
			DisputeClaimFormDAO objDAO= DAOFactory.getInstance().getDisputeClaimFormDAO();
			
	     	 if(docsMandatoryList==null)  {
	     			docsMandatoryList= objDAO.mandatoryDocumentsListData(issuerId,Integer.parseInt(claimReasonCode));
			    }
			  if(docsNonMandatoryList==null)  {
			    	docsNonMandatoryList= objDAO.nonMandatoryDocumentsListData(issuerId,Integer.parseInt(claimReasonCode));
			    }
			//this to show the number of documnets  
			 this.nubmerOfDocs = String.valueOf(docsMandatoryList.size() + docsNonMandatoryList.size());
	    	 
		  }catch(Exception e){
			    System.out.println("Error while getting  getDocPreListData in DisputeClaimFormSetup:"+e.getMessage());
			}
			
		}

	public Map getDocsMandatoryList() {
		return docsMandatoryList;
	}

	public void setDocsMandatoryList(Map docsMandatoryList) {
		this.docsMandatoryList = docsMandatoryList;
	}

	public Map getDocsNonMandatoryList() {
		return docsNonMandatoryList;
	}

	public void setDocsNonMandatoryList(Map docsNonMandatoryList) {
		this.docsNonMandatoryList = docsNonMandatoryList;
	}

	public String getNubmerOfDocs() {
		return nubmerOfDocs;
	}

	public void setNubmerOfDocs(String nubmerOfDocs) {
		this.nubmerOfDocs = nubmerOfDocs;
	}

	

}
