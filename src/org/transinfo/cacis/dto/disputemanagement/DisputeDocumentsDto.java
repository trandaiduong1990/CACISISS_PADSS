package org.transinfo.cacis.dto.disputemanagement;

import java.io.Serializable;

public class DisputeDocumentsDto implements Cloneable, Serializable {

	private String documentName;

	private char documentType;

	public static class Id implements Serializable {

		private String issuerId;

		private String documentId;

		private int reasonCode;

		public Id() {
		}

		public Id(String issuerId, String documentId, int reasonCode) {
			this.issuerId = issuerId;
			this.documentId = documentId;
			this.reasonCode = reasonCode;
		}

		public String getDocumentId() {
			return documentId;
		}

		public void setDocumentId(String documentId) {
			this.documentId = documentId;
		}

		public String getIssuerId() {
			return issuerId;
		}

		public void setIssuerId(String issuerId) {
			this.issuerId = issuerId;
		}

		public int getReasonCode() {
			return reasonCode;
		}

		public void setReasonCode(int reasonCode) {
			this.reasonCode = reasonCode;
		}

	}

	private Id id = new Id();

	public Id getId() {
		return id;
	}

	public void setId(Id id) {
		this.id = id;
	}

	public String getDocumentName() {
		return documentName;
	}

	public void setDocumentName(String documentName) {
		this.documentName = documentName;
	}

	public char getDocumentType() {
		return documentType;
	}

	public void setDocumentType(char documentType) {
		this.documentType = documentType;
	}

}
