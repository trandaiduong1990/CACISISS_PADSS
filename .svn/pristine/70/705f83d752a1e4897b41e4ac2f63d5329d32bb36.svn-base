package org.transinfo.cacis.dto.disputemanagement;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import org.apache.struts.validator.ValidatorForm;
import org.transinfo.cacis.formbean.common.DateForm;

public class WorkItemDto extends ValidatorForm {
	private String issuerId;

	private String userId;

	private char docsUploaded;

	DateForm claimDateForm = new DateForm();

	DateForm lastUpdatedDateForm = new DateForm();

	private Date claimDate;

	private Date docsUploadedDate;

	private Date lastUpdatedDate;

	private String claimNumber;

	private String documentId;

	private String documentName;

	private String commType;

	private String remarks;

	private String remarksId;

	private String[] arlDocuments;

	private Map documentNameList = new HashMap();

	public String[] getArlDocuments() {
		return arlDocuments;
	}

	public void setArlDocuments(String[] arlDocuments) {
		this.arlDocuments = arlDocuments;
	}

	public String getCommType() {
		return commType;
	}

	public void setCommType(String commType) {
		this.commType = commType;
	}

	public Map getDocumentNameList() {
		return documentNameList;
	}

	public void setDocumentNameList(Map documentNameList) {
		this.documentNameList = documentNameList;
	}

	public String getRemarks() {
		return remarks;
	}

	public void setRemarks(String remarks) {
		this.remarks = remarks;
	}

	public void setDocsUploadedDate(Date docsUploadedDate) {
		this.docsUploadedDate = docsUploadedDate;
	}

	public char getDocsUploaded() {
		return docsUploaded;
	}

	public void setDocsUploaded(char docsUploaded) {
		this.docsUploaded = docsUploaded;
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

	public String getIssuerId() {
		return issuerId;
	}

	public void setIssuerId(String issuerId) {
		this.issuerId = issuerId;
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

	public String getRemarksId() {
		return remarksId;
	}

	public void setRemarksId(String remarksId) {
		this.remarksId = remarksId;
	}

}