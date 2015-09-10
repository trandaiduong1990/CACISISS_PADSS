package org.transinfo.cacis.dto.letters;

import java.io.Serializable;
import java.util.Date;

@SuppressWarnings("serial")
public class LetterApplMapDto implements Serializable {
	
	public Id id = new Id();
	private String applActionDesc;
	private String applModule;
	private String letterTemplate;
	private String status;
	private String lastUpdatedBy;
	private Date lastUpdatedDate;
	
	public LetterApplMapDto() {};
	
	public static class Id implements Serializable {
		
		private String issuerId;
		private String applAction;
		
		public Id() {};
		
		public Id(String issuerId, String applAction) {
			this.issuerId = issuerId;
			this.applAction = applAction;
		}

		public String getIssuerId() {
			return issuerId;
		}

		public void setIssuerId(String issuerId) {
			this.issuerId = issuerId;
		}

		public String getApplAction() {
			return applAction;
		}

		public void setApplAction(String applAction) {
			this.applAction = applAction;
		}
	}

	public Id getId() {
		return id;
	}

	public void setId(Id id) {
		this.id = id;
	}

	public String getApplActionDesc() {
		return applActionDesc;
	}

	public void setApplActionDesc(String applActionDesc) {
		this.applActionDesc = applActionDesc;
	}

	public String getApplModule() {
		return applModule;
	}

	public void setApplModule(String applModule) {
		this.applModule = applModule;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getLastUpdatedBy() {
		return lastUpdatedBy;
	}

	public void setLastUpdatedBy(String lastUpdatedBy) {
		this.lastUpdatedBy = lastUpdatedBy;
	}

	public String getLetterTemplate() {
		return letterTemplate;
	}

	public void setLetterTemplate(String letterTemplate) {
		this.letterTemplate = letterTemplate;
	}

	public Date getLastUpdatedDate() {
		return lastUpdatedDate;
	}

	public void setLastUpdatedDate(Date lastUpdatedDate) {
		this.lastUpdatedDate = lastUpdatedDate;
	}
	
}
