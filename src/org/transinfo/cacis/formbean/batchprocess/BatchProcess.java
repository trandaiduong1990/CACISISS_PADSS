package org.transinfo.cacis.formbean.batchprocess;

import java.util.ArrayList;
import java.util.List;

import org.transinfo.cacis.dto.cardproduction.ApplicationFormDto;

public class BatchProcess {
	private List<ApplicationFormDto> applList = new ArrayList<ApplicationFormDto>();
	private String batchId;
	private String noApplications;
	private String applicationType;
	private String authorizedBy;
	private String authorizedDate;
	
	public List<ApplicationFormDto> getApplList() {
		return applList;
	}
	public void setApplList(List<ApplicationFormDto> applList) {
		this.applList = applList;
	}
	public String getBatchId() {
		return batchId;
	}
	public void setBatchId(String batchId) {
		this.batchId = batchId;
	}
	public String getNoApplications() {
		return noApplications;
	}
	public void setNoApplications(String noApplications) {
		this.noApplications = noApplications;
	}
	public String getAuthorizedBy() {
		return authorizedBy;
	}
	public void setAuthorizedBy(String authorizedBy) {
		this.authorizedBy = authorizedBy;
	}
	public String getAuthorizedDate() {
		return authorizedDate;
	}
	public void setAuthorizedDate(String authorizedDate) {
		this.authorizedDate = authorizedDate;
	}
	public String getApplicationType() {
		return applicationType;
	}
	public void setApplicationType(String applicationType) {
		this.applicationType = applicationType;
	}
	
}
