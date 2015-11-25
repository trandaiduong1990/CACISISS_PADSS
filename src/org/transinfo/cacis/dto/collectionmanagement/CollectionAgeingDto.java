package org.transinfo.cacis.dto.collectionmanagement;

import java.io.Serializable;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

@SuppressWarnings("serial")
public class CollectionAgeingDto implements Serializable {
	private String ageingPolicy;
	private int startDays;
	private int endDays;
	private int bucket;
	private String issuerId;
	private String lastUpdatedBy;
	private Date lastUpdatedDate;

	public CollectionAgeingDto() {
		// TODO Auto-generated constructor stub
	}
	
	public CollectionAgeingDto(String ageingPolicy) {
		this.ageingPolicy = ageingPolicy;
	}
	
	public String getIssuerId() {
		return issuerId;
	}
	public String getAgeingPolicy() {
		return ageingPolicy;
	}
	public void setAgeingPolicy(String ageingPolicy) {
		this.ageingPolicy = ageingPolicy;
	}
	public int getStartDays() {
		return startDays;
	}
	public void setStartDays(int startDays) {
		this.startDays = startDays;
	}
	public int getEndDays() {
		return endDays;
	}
	public void setEndDays(int endDays) {
		this.endDays = endDays;
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
	public void setIssuerId(String issuerId) {
		this.issuerId = issuerId;
	}

	public int getBucket() {
		return bucket;
	}

	public void setBucket(int bucket) {
		this.bucket = bucket;
	}
}
