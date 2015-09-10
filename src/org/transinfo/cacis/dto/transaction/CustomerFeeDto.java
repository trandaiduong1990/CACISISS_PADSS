package org.transinfo.cacis.dto.transaction;

import java.io.Serializable;
import java.util.Date;

@SuppressWarnings("serial")
public class CustomerFeeDto implements Serializable {
	
	private String customerFeeId;
	private Long cardNo;
	private String accountId;
	private String refId;
	private Float feeAmt;
	private String amountSign;
	private Date FeeDate;
	private String feeSrc;
	private String billed;
	private Date createdDate;
	private String createdBy;
	private Long tranxLogId;
	private String feeWaived;
	private Date annualFeeDate;
	private String remarks;
	
	public String getCustomerFeeId() {
		return customerFeeId;
	}
	public void setCustomerFeeId(String customerFeeId) {
		this.customerFeeId = customerFeeId;
	}
	public String getAccountId() {
		return accountId;
	}
	public void setAccountId(String accountId) {
		this.accountId = accountId;
	}
	public String getRefId() {
		return refId;
	}
	public void setRefId(String refId) {
		this.refId = refId;
	}
	public Date getFeeDate() {
		return FeeDate;
	}
	public void setFeeDate(Date feeDate) {
		FeeDate = feeDate;
	}
	public String getFeeSrc() {
		return feeSrc;
	}
	public void setFeeSrc(String feeSrc) {
		this.feeSrc = feeSrc;
	}
	public String getBilled() {
		return billed;
	}
	public void setBilled(String billed) {
		this.billed = billed;
	}
	public String getCreatedBy() {
		return createdBy;
	}
	public void setCreatedBy(String createdBy) {
		this.createdBy = createdBy;
	}
	public Float getFeeAmt() {
		return feeAmt;
	}
	public void setFeeAmt(Float feeAmt) {
		this.feeAmt = feeAmt;
	}
	public String getAmountSign() {
		return amountSign;
	}
	public void setAmountSign(String amountSign) {
		this.amountSign = amountSign;
	}
	public Date getCreatedDate() {
		return createdDate;
	}
	public void setCreatedDate(Date createdDate) {
		this.createdDate = createdDate;
	}
	public Long getCardNo() {
		return cardNo;
	}
	public void setCardNo(Long cardNo) {
		this.cardNo = cardNo;
	}
	public Long getTranxLogId() {
		return tranxLogId;
	}
	public void setTranxLogId(Long tranxLogId) {
		this.tranxLogId = tranxLogId;
	}
	public String getFeeWaived() {
		return feeWaived;
	}
	public void setFeeWaived(String feeWaived) {
		this.feeWaived = feeWaived;
	}
	public Date getAnnualFeeDate() {
		return annualFeeDate;
	}
	public void setAnnualFeeDate(Date annualFeeDate) {
		this.annualFeeDate = annualFeeDate;
	}
	public String getRemarks() {
		return remarks;
	}
	public void setRemarks(String remarks) {
		this.remarks = remarks;
	}

}
