package org.transinfo.cacis.formbean.disputemanagement;

import java.util.HashMap;
import java.util.Map;

import org.apache.struts.validator.ValidatorForm;
import org.transinfo.cacis.formbean.common.DateForm;

public class WorkItemForm extends ValidatorForm {

	private String issuerId;

	private String userId;

	private String docsUploaded;

	DateForm claimDateForm = new DateForm();

	DateForm lastUpdatedDateForm = new DateForm();

	private String claimNumber;

	private String documentId;

	private String documentName;

	private String commType;

	private String remarks;

	private String remarksId;

	private String[] arlDocuments;

	private static Map documentNameList = new HashMap();

	public DateForm getClaimDateForm() {
		return claimDateForm;
	}

	public void setClaimDateForm(DateForm claimDateForm) {
		this.claimDateForm = claimDateForm;
	}

	public String getClaimNumber() {
		return claimNumber;
	}

	public void setClaimNumber(String claimNumber) {
		this.claimNumber = claimNumber;
	}

	public String getCommType() {
		return commType;
	}

	public void setCommType(String commType) {
		this.commType = commType;
	}

	public String getDocsUploaded() {
		return docsUploaded;
	}

	public void setDocsUploaded(String docsUploaded) {
		this.docsUploaded = docsUploaded;
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

	public Map getDocumentNameList() {
		return documentNameList;
	}

	public void setDocumentNameList(Map documentNameList) {
		this.documentNameList = documentNameList;
	}

	public String[] getArlDocuments() {
		return arlDocuments;
	}

	public void setArlDocuments(String[] arlDocuments) {
		this.arlDocuments = arlDocuments;
	}

	public String getIssuerId() {
		return issuerId;
	}

	public void setIssuerId(String issuerId) {
		this.issuerId = issuerId;
	}

	public DateForm getLastUpdatedDateForm() {
		return lastUpdatedDateForm;
	}

	public void setLastUpdatedDateForm(DateForm lastUpdatedDateForm) {
		this.lastUpdatedDateForm = lastUpdatedDateForm;
	}

	public String getRemarks() {
		return remarks;
	}

	public void setRemarks(String remarks) {
		this.remarks = remarks;
	}

	public String getRemarksId() {
		return remarksId;
	}

	public void setRemarksId(String remarksId) {
		this.remarksId = remarksId;
	}

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	/*
	 * public void getPreListData() { try { WorkItemDAO objDAO =
	 * DAOFactory.getInstance().getWorkItemDAO(); if
	 * (documentNameList.isEmpty()) { // documentNameList = //
	 * objDAO.responseDocumentsNameListData(issuerId, claimNumber); } } catch
	 * (Exception e) { System.out .println("Error while getting WriteOffForm
	 * formbean PreListData:" + e); } }
	 */
}
