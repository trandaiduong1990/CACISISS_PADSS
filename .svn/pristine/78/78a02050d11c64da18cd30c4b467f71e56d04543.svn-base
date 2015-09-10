package org.transinfo.cacis.dto.disputemanagement;

import java.util.Date;

import org.apache.struts.upload.FormFile;
import org.apache.struts.validator.ValidatorForm;
import org.transinfo.cacis.formbean.common.DateForm;

public class DocumentUploadDto extends ValidatorForm {

	private String issuerId;

	private String userId;

	private long cardNumber;

	private String documentId;

	private String reasonCode;

	private String documentName;

	private String claimNumber;

	DateForm claimDateForm = new DateForm();

	private Date claimDate;

	private Date docsUploadedDate;

	DateForm docsUploadedDateForm = new DateForm();

	private Date lastUpdatedDate;

	DateForm lastUpdatedDateForm = new DateForm();

	private String strClaimDate;

	private String customerName;

	private FormFile uploadFile;

	private char docsUploaded;

	private char documentType;

	public char getDocsUploaded() {
		return docsUploaded;
	}

	public void setDocsUploaded(char docsUploaded) {
		this.docsUploaded = docsUploaded;
	}

	public char getDocumentType() {
		return documentType;
	}

	public void setDocumentType(char documentType) {
		this.documentType = documentType;
	}

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public Date getDocsUploadedDate() {
		return docsUploadedDate;
	}

	public void setDocsUploadedDate(Date docsUploadedDate) {

		this.docsUploadedDate = docsUploadedDate;

		if (docsUploadedDate != null) {
			this.docsUploadedDateForm = new DateForm(docsUploadedDate);
		}
	}

	public DateForm getDocsUploadedDateForm() {
		return docsUploadedDateForm;
	}

	public void setDocsUploadedDateForm(DateForm docsUploadedDateForm) {
		this.docsUploadedDateForm = docsUploadedDateForm;
		docsUploadedDate = docsUploadedDateForm.toDate();
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

	public DateForm getLastUpdatedDateForm() {
		return lastUpdatedDateForm;
	}

	public void setLastUpdatedDateForm(DateForm lastUpdatedDateForm) {
		this.lastUpdatedDateForm = lastUpdatedDateForm;
		lastUpdatedDate = lastUpdatedDateForm.toDate();
	}

	public String getStrClaimDate() {
		return strClaimDate;
	}

	public void setStrClaimDate(String strClaimDate) {
		this.strClaimDate = strClaimDate;
	}

	public FormFile getUploadFile() {
		return uploadFile;
	}

	public void setUploadFile(FormFile uploadFile) {
		this.uploadFile = uploadFile;
	}

	public String getIssuerId() {
		return issuerId;
	}

	public void setIssuerId(String issuerId) {
		this.issuerId = issuerId;
	}

	public long getCardNumber() {
		return new Long(cardNumber).longValue();
	}

	public void setCardNumber(long cardNumber) {
		this.cardNumber = cardNumber;
	}

	public Date getClaimDate() {
		return claimDate;
	}

	public void setClaimDate(Date claimDate) {

		this.claimDate = claimDate;

		if (claimDate != null) {
			this.claimDateForm = new DateForm(claimDate);
		}
	}

	public DateForm getClaimDateForm() {
		return claimDateForm;
	}

	public void setClaimDateForm(DateForm claimDateForm) {
		this.claimDateForm = claimDateForm;
		claimDate = claimDateForm.toDate();
	}

	public String getClaimNumber() {
		return claimNumber;
	}

	public void setClaimNumber(String claimNumber) {
		this.claimNumber = claimNumber;
	}

	public String getCustomerName() {
		return customerName;
	}

	public void setCustomerName(String customerName) {
		this.customerName = customerName;
	}

	public String getDocumentId() {
		return documentId;
	}

	public void setDocumentId(String documentId) {
		this.documentId = documentId;
	}

	public String getDocumentName() {
		return documentName;
	}

	public void setDocumentName(String documentName) {
		this.documentName = documentName;
	}

	public String getReasonCode() {
		return reasonCode;
	}

	public void setReasonCode(String reasonCode) {
		this.reasonCode = reasonCode;
	}
}