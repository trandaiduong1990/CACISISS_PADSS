package org.transinfo.cacis.dto.cardproduction;

import java.io.Serializable;
import java.util.Date;

@SuppressWarnings("serial")
public class CardDataDto implements Serializable {

	private String cardDataSerialNo;
	private long cardNumber;
	private String expDate;
	private String serviceCode;
	private long pvvOffSet;
	private int cvv;
	private int cvv2;
	private String status;
	private String track1;
	private String track2;
	private Date issueDate;
	private Date updatedDate;
	private String updatedBy;
	private Date closingDate;
	private String icvv;

	public String getCardDataSerialNo() {
		return cardDataSerialNo;
	}

	public void setCardDataSerialNo(String cardDataSerialNo) {
		this.cardDataSerialNo = cardDataSerialNo;
	}

	public long getCardNumber() {
		return cardNumber;
	}

	public void setCardNumber(long cardNumber) {
		this.cardNumber = cardNumber;
	}

	public String getExpDate() {
		return expDate;
	}

	public void setExpDate(String expDate) {
		this.expDate = expDate;
	}

	public String getServiceCode() {
		return serviceCode;
	}

	public void setServiceCode(String serviceCode) {
		this.serviceCode = serviceCode;
	}

	public long getPvvOffSet() {
		return pvvOffSet;
	}

	public void setPvvOffSet(long pvvOffSet) {
		this.pvvOffSet = pvvOffSet;
	}

	public int getCvv() {
		return cvv;
	}

	public void setCvv(int cvv) {
		this.cvv = cvv;
	}

	public int getCvv2() {
		return cvv2;
	}

	public void setCvv2(int cvv2) {
		this.cvv2 = cvv2;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getTrack1() {
		return track1;
	}

	public void setTrack1(String track1) {
		this.track1 = track1;
	}

	public String getTrack2() {
		return track2;
	}

	public void setTrack2(String track2) {
		this.track2 = track2;
	}

	public Date getIssueDate() {
		return issueDate;
	}

	public void setIssueDate(Date issueDate) {
		this.issueDate = issueDate;
	}

	public Date getUpdatedDate() {
		return updatedDate;
	}

	public void setUpdatedDate(Date updatedDate) {
		this.updatedDate = updatedDate;
	}

	public String getUpdatedBy() {
		return updatedBy;
	}

	public void setUpdatedBy(String updatedBy) {
		this.updatedBy = updatedBy;
	}

	public Date getClosingDate() {
		return closingDate;
	}

	public void setClosingDate(Date closingDate) {
		this.closingDate = closingDate;
	}

	public String getIcvv() {
		return icvv;
	}

	public void setIcvv(String icvv) {
		this.icvv = icvv;
	}
}
