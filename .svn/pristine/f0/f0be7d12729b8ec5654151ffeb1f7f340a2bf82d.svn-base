package org.transinfo.cacis.formbean.disputemanagement;

import java.util.HashMap;
import java.util.Map;

import org.apache.struts.upload.FormFile;
import org.apache.struts.validator.ValidatorForm;
import org.transinfo.cacis.dataacess.DAOFactory;
import org.transinfo.cacis.dataacess.dao.disputemanagement.DocumentUploadDAO;
import org.transinfo.cacis.formbean.common.DateForm;

public class DocumentUploadForm extends ValidatorForm {
	private String issuerId;

	private String docsUploaded;

	private String userId;

	DateForm claimDateForm = new DateForm();

	DateForm lastUpdatedDateForm = new DateForm();

	DateForm docsUploadedDateForm = new DateForm();

	private String claimNumber;

	private String cardNumber;

	private String customerName;

	private String strClaimDate;

	private String strUploadedDate;

	private String documentId;

	private String documentName;

	private String reasonCode;

	private FormFile uploadFile;

	private String selectedDocumentName;

	private String documentType;

	private Map documentNameList = new HashMap();

	public FormFile getUploadFile() {
		return uploadFile;
	}

	public void setUploadFile(FormFile theFile) {
		this.uploadFile = theFile;
	}

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

	public String getDocsUploaded() {
		return docsUploaded;
	}

	public void setDocsUploaded(String docsUploaded) {
		this.docsUploaded = docsUploaded;
	}

	public DateForm getDocsUploadedDateForm() {
		return docsUploadedDateForm;
	}

	public void setDocsUploadedDateForm(DateForm docsUploadedDateForm) {
		this.docsUploadedDateForm = docsUploadedDateForm;
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

	public String getReasonCode() {
		return reasonCode;
	}

	public void setReasonCode(String reasonCode) {
		this.reasonCode = reasonCode;
	}

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public String getCardNumber() {
		return cardNumber;
	}

	public void setCardNumber(String cardNumber) {
		this.cardNumber = cardNumber;
	}

	public String getCustomerName() {
		return customerName;
	}

	public void setCustomerName(String customerName) {
		this.customerName = customerName;
	}

	public void getPreListData() {
		try {
			DocumentUploadDAO objDAO = DAOFactory.getInstance()
					.getDocumentUploadDAO();
			if (documentNameList.isEmpty()) {
				documentNameList = objDAO.requestDocumentsNameListData(issuerId,
						claimNumber); 
			}
		} catch (Exception e) {
			System.out
					.println("Error while getting DocumentUploadForm formbean PreListData:"
							+ e);
		}
	}

	public Map getDocumentNameList() {
		return documentNameList;
	}

	public void setDocumentNameList(Map documentNameList) {
		this.documentNameList = documentNameList;
	}

	public String getSelectedDocumentName() {
		return selectedDocumentName;
	}

	public void setSelectedDocumentName(String selectedDocumentName) {
		this.selectedDocumentName = selectedDocumentName;
	}

	public String getStrClaimDate() {
		return strClaimDate;
	}

	public void setStrClaimDate(String claimDate) {
		this.strClaimDate = claimDate;
	}

	public String getDocumentType() {
		return documentType;
	}

	public void setDocumentType(String documentType) {
		this.documentType = documentType;
	}

	public String getStrUploadedDate() {
		return strUploadedDate;
	}

	public void setStrUploadedDate(String strUploadedDate) {
		this.strUploadedDate = strUploadedDate;
	}

}