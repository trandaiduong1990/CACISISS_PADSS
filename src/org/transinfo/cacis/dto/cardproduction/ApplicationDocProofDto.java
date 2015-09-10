package org.transinfo.cacis.dto.cardproduction;

import java.io.Serializable;
import java.util.Date;

public class ApplicationDocProofDto implements Serializable {
	
	private  String     issuerId;
    private  String     appDocumentId;
    private  String     appDocumentType;     
	private  Date       updatedDate;
	private  String     lastUpdatedBy;
	
	
	public ApplicationDocProofDto(){}

	public ApplicationDocProofDto(Date updatedDate, String lastUpdatedBy)
	{

		this.updatedDate = updatedDate;
		this.lastUpdatedBy = "ASPSUPERADMIN";

	}


	public String getLastUpdatedBy() {
		return lastUpdatedBy;
	}
	public void setLastUpdatedBy(String lastUpdatedBy) {
		this.lastUpdatedBy = lastUpdatedBy;
	}
	public Date getUpdatedDate() {
		return updatedDate;
	}
	public void setUpdatedDate(Date updatedDate) {
		this.updatedDate = updatedDate;
	}

	public String getAppDocumentId() {
		return appDocumentId;
	}

	public void setAppDocumentId(String appDocumentId) {
		this.appDocumentId = appDocumentId;
	}

	public String getAppDocumentType() {
		return appDocumentType;
	}

	public void setAppDocumentType(String appDocumentType) {
		this.appDocumentType = appDocumentType;
	}

	public String getIssuerId() {
		return issuerId;
	}

	public void setIssuerId(String issuerId) {
		this.issuerId = issuerId;
	}
	

}
