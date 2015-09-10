package org.transinfo.cacis.dto.disputemanagement;

import java.io.Serializable;
import java.util.Date;

@SuppressWarnings("serial")
public class DisputeRequestDocumentsDto {

	private char docsUploaded;
	private String userId;
	private Date docsUploadedDate;
	private Date updatedDate = new Date();
	private String remarksId;
	// for composite id
	private Id id = new Id();

	public DisputeRequestDocumentsDto() {
	}

	public static class Id implements Serializable {

		private String claimNumber;
		private String documentId;

		public Id() {
		}

		public Id(String claimNumber, String documentId) {
			this.claimNumber = claimNumber;
			this.documentId = documentId;

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
	}

	public Id getId() {
		return id;
	}

	public void setId(Id id) {
		this.id = id;
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

	public char getDocsUploaded() {
		return docsUploaded;
	}

	public void setDocsUploaded(char docsUploaded) {
		this.docsUploaded = docsUploaded;
	}

	public Date getDocsUploadedDate() {
		return docsUploadedDate;
	}

	public void setDocsUploadedDate(Date docsUploadedDate) {
		this.docsUploadedDate = docsUploadedDate;
	}

	public String getRemarksId() {
		return remarksId;
	}

	public void setRemarksId(String remarksId) {
		this.remarksId = remarksId;
	}

}
