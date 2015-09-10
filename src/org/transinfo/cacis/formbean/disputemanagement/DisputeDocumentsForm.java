package org.transinfo.cacis.formbean.disputemanagement;

import java.util.Map;

import org.apache.struts.validator.ValidatorForm;
import org.transinfo.cacis.dataacess.DAOFactory;
import org.transinfo.cacis.dataacess.dao.disputemanagement.DisputeDocumentsDAO;

public class DisputeDocumentsForm extends ValidatorForm {

	private String issuerId;

	private String documentId;

	private String reasonCode;

	private String documentName;

	private String documentType;
	
	private String mandatoryDocumentName;
	
	private String nonMandatoryDocumentName;
	
	private String[] arlNonMandatoryDocuments;
	
	private String[] arlMandatoryDocuments;

	private Map nonMandatoryDocumentsList;

	private Map mandatoryDocumentsList;
	
	private Map reasonCodeList;

	public void getPreListData() {
		try {
			DisputeDocumentsDAO objDisputeDocumentsDAO = DAOFactory
					.getInstance().getDisputeDocumentsDAO();
			if (nonMandatoryDocumentsList == null) {
				nonMandatoryDocumentsList = objDisputeDocumentsDAO
						.nonMandatoryDocumentsListData(issuerId, Integer.parseInt(reasonCode));
			}
			if (mandatoryDocumentsList == null) {
				mandatoryDocumentsList = objDisputeDocumentsDAO
						.mandatoryDocumentsListData(issuerId, Integer.parseInt(reasonCode));
			}
			if (reasonCodeList == null) {
				reasonCodeList = objDisputeDocumentsDAO
						.reasonCodeListData();
			}
		} catch (Exception e) {
			System.out
					.println("Error while getting DisputeDocumentsForm formbean PreListData:"
							+ e);
		}
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

	public String getDocumentType() {
		return documentType;
	}

	public void setDocumentType(String documentType) {
		this.documentType = documentType;
	}

	public String getIssuerId() {
		return issuerId;
	}

	public void setIssuerId(String issuerId) {
		this.issuerId = issuerId;
	}

	public String getReasonCode() {
		return reasonCode;
	}

	public void setReasonCode(String reasonCode) {
		this.reasonCode = reasonCode;
	}

	public String getMandatoryDocumentName() {
		return mandatoryDocumentName;
	}

	public void setMandatoryDocumentName(String mandatoryDocumentName) {
		this.mandatoryDocumentName = mandatoryDocumentName;
	}

	public String getNonMandatoryDocumentName() {
		return nonMandatoryDocumentName;
	}

	public void setNonMandatoryDocumentName(String nonMandatoryDocumentName) {
		this.nonMandatoryDocumentName = nonMandatoryDocumentName;
	}

	public String[] getArlMandatoryDocuments() {
		return arlMandatoryDocuments;
	}

	public void setArlMandatoryDocuments(String[] arlMandatoryDocuments) {
		this.arlMandatoryDocuments = arlMandatoryDocuments;
	}

	public String[] getArlNonMandatoryDocuments() {
		return arlNonMandatoryDocuments;
	}

	public void setArlNonMandatoryDocuments(String[] arlNonMandatoryDocuments) {
		this.arlNonMandatoryDocuments = arlNonMandatoryDocuments;
	}

	public Map getMandatoryDocumentsList() {
		return mandatoryDocumentsList;
	}

	public void setMandatoryDocumentsList(Map mandatoryDocumentsList) {
		this.mandatoryDocumentsList = mandatoryDocumentsList;
	}

	public Map getNonMandatoryDocumentsList() {
		return nonMandatoryDocumentsList;
	}

	public void setNonMandatoryDocumentsList(Map nonMandatoryDocumentsList) {
		this.nonMandatoryDocumentsList = nonMandatoryDocumentsList;
	}

}