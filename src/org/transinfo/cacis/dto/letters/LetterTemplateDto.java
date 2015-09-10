package org.transinfo.cacis.dto.letters;

import java.io.Serializable;
import java.util.Date;

import org.apache.struts.upload.FormFile;

@SuppressWarnings("serial")
public class LetterTemplateDto implements Serializable {
	
	private String letterId;
	private String description;
	private LetterCategoryDto category;
	private String applicationSource;
	private String signatoryName;
	private String department;
	private String issuerId;
	private Integer noOfCopies;
	private String status;
	private String manualEntry;
	private String sqlQuery;
	private String lastUpdatedBy;
	private Date lastUpdatedDate;
	
	private FormFile fileUpload; 
	
	public String getLetterId() {
		return letterId;
	}
	public void setLetterId(String letterId) {
		this.letterId = letterId;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public LetterCategoryDto getCategory() {
		return category;
	}
	public void setCategory(LetterCategoryDto category) {
		this.category = category;
	}
	public String getApplicationSource() {
		return applicationSource;
	}
	public void setApplicationSource(String applicationSource) {
		this.applicationSource = applicationSource;
	}
	public String getSignatoryName() {
		return signatoryName;
	}
	public void setSignatoryName(String signatoryName) {
		this.signatoryName = signatoryName;
	}
	public String getDepartment() {
		return department;
	}
	public void setDepartment(String department) {
		this.department = department;
	}
	public String getIssuerId() {
		return issuerId;
	}
	public void setIssuerId(String issuerId) {
		this.issuerId = issuerId;
	}
	public Integer getNoOfCopies() {
		return noOfCopies;
	}
	public void setNoOfCopies(Integer noOfCopies) {
		this.noOfCopies = noOfCopies;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public String getManualEntry() {
		return manualEntry;
	}
	public void setManualEntry(String manualEntry) {
		this.manualEntry = manualEntry;
	}
	public String getLastUpdatedBy() {
		return lastUpdatedBy;
	}
	public void setLastUpdatedBy(String lastUpdatedBy) {
		this.lastUpdatedBy = lastUpdatedBy;
	}
	public Date getLastUpdatedDate() {
		return lastUpdatedDate;
	}
	public void setLastUpdatedDate(Date lastUpdatedDate) {
		this.lastUpdatedDate = lastUpdatedDate;
	}
	public String getSqlQuery() {
		return sqlQuery;
	}
	public void setSqlQuery(String sqlQuery) {
		this.sqlQuery = sqlQuery;
	}
	public FormFile getFileUpload() {
		return fileUpload;
	}
	public void setFileUpload(FormFile fileUpload) {
		this.fileUpload = fileUpload;
	}

}
