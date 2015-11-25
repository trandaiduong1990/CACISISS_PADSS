package org.transinfo.cacis.formbean.collectionmanagement;

import java.util.Map;

import org.apache.struts.validator.ValidatorForm;

@SuppressWarnings("serial")
public class CollectionAgeingSetupForm extends ValidatorForm {
	private String issuerId;
	private String ageingPolicy;
	private String startDays;
	private String endDays;
	private String bucket;
	public String getIssuerId() {
		return issuerId;
	}
	public void setIssuerId(String issuerId) {
		this.issuerId = issuerId;
	}
	public String getAgeingPolicy() {
		return ageingPolicy;
	}
	public void setAgeingPolicy(String ageingPolicy) {
		this.ageingPolicy = ageingPolicy;
	}
	public String getStartDays() {
		return startDays;
	}
	public void setStartDays(String startDays) {
		this.startDays = startDays;
	}
	public String getEndDays() {
		return endDays;
	}
	public void setEndDays(String endDays) {
		this.endDays = endDays;
	}
	public String getBucket() {
		return bucket;
	}
	public void setBucket(String bucket) {
		this.bucket = bucket;
	}
}
