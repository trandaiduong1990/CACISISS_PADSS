package org.transinfo.cacis.dto.customerservice;

import java.io.Serializable;
import java.util.Date;

@SuppressWarnings("serial")
public class FeeWaiver implements Serializable {
	
	private String sno;
	private String cardNo;
	private String feeType;
	private String feeRef;
	private String amount;
	private String createdBY;
	private Date createdDate;
	
	public String getSno() {
		return sno;
	}
	public void setSno(String sno) {
		this.sno = sno;
	}
	public String getCardNo() {
		return cardNo;
	}
	public void setCardNo(String cardNo) {
		this.cardNo = cardNo;
	}
	public String getFeeType() {
		return feeType;
	}
	public void setFeeType(String feeType) {
		this.feeType = feeType;
	}
	public String getFeeRef() {
		return feeRef;
	}
	public void setFeeRef(String feeRef) {
		this.feeRef = feeRef;
	}
	public String getAmount() {
		return amount;
	}
	public void setAmount(String amount) {
		this.amount = amount;
	}
	public String getCreatedBY() {
		return createdBY;
	}
	public void setCreatedBY(String createdBY) {
		this.createdBY = createdBY;
	}
	public Date getCreatedDate() {
		return createdDate;
	}
	public void setCreatedDate(Date createdDate) {
		this.createdDate = createdDate;
	}	

}
