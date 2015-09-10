package org.transinfo.cacis.dto.csr;

import java.io.Serializable;
import java.util.Date;

@SuppressWarnings("serial")
public class CallRecordLogDto implements Serializable {

	private String referenceNo;
	private String originalRefNo;
	private String prevReferenceNo;
	private String issuerId;
	private long cardNumber;
	private String callTypeId;
	private String callDescription;
	private Date callStartTime;
	private Date callEndTime;
	private String callStatus;
	private String userId;
	private Date updatedDate = new Date();
	// for callType
	private String callType;

	public CallRecordLogDto() {
	}

	public String getCallDescription() {
		return callDescription;
	}

	public void setCallDescription(String callDescription) {
		this.callDescription = callDescription;
	}

	public Date getCallEndTime() {
		return callEndTime;
	}

	public void setCallEndTime(Date callEndTime) {
		this.callEndTime = callEndTime;
	}

	public Date getCallStartTime() {
		return callStartTime;
	}

	public void setCallStartTime(Date callStartTime) {
		this.callStartTime = callStartTime;
	}

	public long getCardNumber() {
		return cardNumber;
	}

	public void setCardNumber(long cardNumber) {
		this.cardNumber = cardNumber;
	}

	public String getIssuerId() {
		return issuerId;
	}

	public void setIssuerId(String issuerId) {
		this.issuerId = issuerId;
	}

	public String getPrevReferenceNo() {
		return prevReferenceNo;
	}

	public void setPrevReferenceNo(String prevReferenceNo) {
		this.prevReferenceNo = prevReferenceNo;
	}

	public String getReferenceNo() {
		return referenceNo;
	}

	public void setReferenceNo(String referenceNo) {
		this.referenceNo = referenceNo;
	}

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public String getCallTypeId() {
		return callTypeId;
	}

	public void setCallTypeId(String callTypeId) {
		this.callTypeId = callTypeId;
	}

	public Date getUpdatedDate() {
		return updatedDate;
	}

	public void setUpdatedDate(Date updatedDate) {
		this.updatedDate = updatedDate;
	}

	public String getCallStatus() {
		return callStatus;
	}

	public void setCallStatus(String callStatus) {
		this.callStatus = callStatus;
	}

	public String getOriginalRefNo() {
		return originalRefNo;
	}

	public void setOriginalRefNo(String originalRefNo) {
		this.originalRefNo = originalRefNo;
	}

	public String getCallType() {
		return callType;
	}

	public void setCallType(String callType) {
		this.callType = callType;
	}

}
