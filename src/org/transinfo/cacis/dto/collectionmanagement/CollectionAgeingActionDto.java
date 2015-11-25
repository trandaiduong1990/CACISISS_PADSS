package org.transinfo.cacis.dto.collectionmanagement;

import java.io.Serializable;
import java.util.Date;
import java.util.Map;

@SuppressWarnings("serial")
public class CollectionAgeingActionDto implements Serializable {
	private String sno;
	private CollectionAgeingDto collectionAgeingDto;
	private String issuerId;
	private int days;
	private String phone;
	private String remainder;
	private String remainderType;
	private String tempCardOff;
	private String writeOff;
	private String lastUpdatedBy;
	private Date lastUpdatedDate;
	
	public CollectionAgeingActionDto() {
		// TODO Auto-generated constructor stub
	}
	
//	public CollectionAgeingActionDto(CollectionAgeingDto collectionAgeingDto) {
//		this.collectionAgeingDto = collectionAgeingDto;
//	}
	
	public String getSno() {
		return sno;
	}
	public void setSno(String sno) {
		this.sno = sno;
	}
	public CollectionAgeingDto getCollectionAgeingDto() {
		return collectionAgeingDto;
	}
	public void setCollectionAgeingDto(CollectionAgeingDto collectionAgeingDto) {
		this.collectionAgeingDto = collectionAgeingDto;
	}
	public String getIssuerId() {
		return issuerId;
	}
	public void setIssuerId(String issuerId) {
		this.issuerId = issuerId;
	}
	public int getDays() {
		return days;
	}
	public void setDays(int days) {
		this.days = days;
	}
	public String getPhone() {
		return phone;
	}
	public void setPhone(String phone) {
		this.phone = phone;
	}
	public String getRemainder() {
		return remainder;
	}
	public void setRemainder(String remainder) {
		this.remainder = remainder;
	}
	public String getRemainderType() {
		return remainderType;
	}
	public void setRemainderType(String remainderType) {
		this.remainderType = remainderType;
	}
	public String getTempCardOff() {
		return tempCardOff;
	}
	public void setTempCardOff(String tempCardOff) {
		this.tempCardOff = tempCardOff;
	}
	public String getWriteOff() {
		return writeOff;
	}
	public void setWriteOff(String writeOff) {
		this.writeOff = writeOff;
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
}
