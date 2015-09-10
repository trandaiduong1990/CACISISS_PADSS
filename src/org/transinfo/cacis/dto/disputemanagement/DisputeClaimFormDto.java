package org.transinfo.cacis.dto.disputemanagement;

import java.io.Serializable;
import java.util.Date;

public class DisputeClaimFormDto  implements Serializable{
	
	
	private static final long serialVersionUID = 1L;
	private long settlementId;
	private String claimNumber;
	private String issuerId;
	private String claimTypeId;
	private int claimReasonCode;
	private String transactionDone;
	private long cardNumber;
	private String customerName;
	private char status;
	private String address1;
	private String address2;
	private String city;
	private String state;
	private String country;
	private long postalCode;
	private long phoneNumber;
	private long faxNumber;
	private String originalDisputeNo;
	private String parentDisputeNo;
	private String remarks;
	private String remarksId;
	private Date updatedDate =new Date();
	private Date claimDate = new Date();
	private String userId;
	
//	for customer details
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
	
   //to Documents table
	private char docsUploaded;
	
	//to insert into Dispute_request_documents 
    private String[] selectedMandatoryDocs = {};
    private String[] selectedNonMandatoryDocs = {};
	
	public DisputeClaimFormDto(){}

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

	public long getCardNumber() {
		return cardNumber;
	}

	public void setCardNumber(long cardNumber) {
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

	public long getFaxNumber() {
		return faxNumber;
	}

	public void setFaxNumber(long faxNumber) {
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

	public long getPhoneNumber() {
		return phoneNumber;
	}

	public void setPhoneNumber(long phoneNumber) {
		this.phoneNumber = phoneNumber;
	}

	public long getPostalCode() {
		return postalCode;
	}

	public void setPostalCode(long postalCode) {
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

	public int getClaimReasonCode() {
		return claimReasonCode;
	}

	public void setClaimReasonCode(int claimReasonCode) {
		this.claimReasonCode = claimReasonCode;
	}

	public long getSettlementId() {
		return settlementId;
	}

	public void setSettlementId(long settlementId) {
		this.settlementId = settlementId;
	}

	public char getStatus() {
		return status;
	}

	public void setStatus(char status) {
		this.status = status;
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

	public char getDocsUploaded() {
		return docsUploaded;
	}

	public void setDocsUploaded(char docsUploaded) {
		this.docsUploaded = docsUploaded;
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

	public Date getClaimDate() {
		return claimDate;
	}

	public void setClaimDate(Date claimDate) {
		this.claimDate = claimDate;
	}

	public String getRemarksId() {
		return remarksId;
	}

	public void setRemarksId(String remarksId) {
		this.remarksId = remarksId;
	}

	

	

	

}
