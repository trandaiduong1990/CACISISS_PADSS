package org.transinfo.cacis.dto.riskmanagement;

import java.io.Serializable;
import java.util.Date;

import org.transinfo.cacis.formbean.common.DateForm;

public class OriginalRequestDto implements Cloneable,Serializable{
	
	private long   settlementId;
	private String issuerId;
	private Date   dateTime;
	private String cardNumber;
	private String refNumber; 
	private String acqId;
	private DateForm tranxDateForm = new DateForm();
	private Date   tranxDate;
	private double amountCurr;
	private double amountDebited;
	private String currencyCode;
	private String merchantName;
	private String merchantCity;
	private String merchantCountry;
	private String mcc;
	private String approvalCode;
	private String data;
	private String primaryCardNo;
	private double originalAmount;
	private int    status;
	private String reasonCode;
	private String originalCurr;
	private String sent;
	private String tranxCode;
	private String remarks;
    private String userId;
	
   //for showing Currency Name using currcode and original currcode
    private String currName;
    private String originalCurrName;
    
    
	public OriginalRequestDto(){}

	public String getAcqId() {
		return acqId;
	}

	public void setAcqId(String acqId) {
		this.acqId = acqId;
	}

	

	public double getAmountDebited() {
		return amountDebited;
	}

	public void setAmountDebited(double amountDebited) {
		this.amountDebited = amountDebited;
	}

	public String getApprovalCode() {
		return approvalCode;
	}

	public void setApprovalCode(String approvalCode) {
		this.approvalCode = approvalCode;
	}

	public String getCardNumber() {
		return cardNumber;
	}

	public void setCardNumber(String cardNumber) {
		this.cardNumber = cardNumber;
	}

	public String getCurrencyCode() {
		return currencyCode;
	}

	public void setCurrencyCode(String currencyCode) {
		this.currencyCode = currencyCode;
	}

	public String getData() {
		return data;
	}

	public void setData(String data) {
		this.data = data;
	}

	public Date getDateTime() {
		return dateTime;
	}

	public void setDateTime(Date dateTime) {
		this.dateTime = dateTime;
	}

	public String getIssuerId() {
		return issuerId;
	}

	public void setIssuerId(String issuerId) {
		this.issuerId = issuerId;
	}

	public String getMcc() {
		return mcc;
	}

	public void setMcc(String mcc) {
		this.mcc = mcc;
	}

	public String getMerchantCity() {
		return merchantCity;
	}

	public void setMerchantCity(String merchantCity) {
		this.merchantCity = merchantCity;
	}

	public String getMerchantCountry() {
		return merchantCountry;
	}

	public void setMerchantCountry(String merchantCountry) {
		this.merchantCountry = merchantCountry;
	}

	public String getMerchantName() {
		return merchantName;
	}

	public void setMerchantName(String merchantName) {
		this.merchantName = merchantName;
	}

	public double getOriginalAmount() {
		return originalAmount;
	}

	public void setOriginalAmount(double originalAmount) {
		this.originalAmount = originalAmount;
	}

	
	public String getPrimaryCardNo() {
		return primaryCardNo;
	}

	public void setPrimaryCardNo(String primaryCardNo) {
		this.primaryCardNo = primaryCardNo;
	}

	public String getReasonCode() {
		return reasonCode;
	}

	public void setReasonCode(String reasonCode) {
		this.reasonCode = reasonCode;
	}

	public String getRemarks() {
		return remarks;
	}

	public void setRemarks(String remarks) {
		this.remarks = remarks;
	}

	public String getSent() {
		return sent;
	}

	public void setSent(String sent) {
		this.sent = sent;
	}

	public long getSettlementId() {
		return settlementId;
	}

	public void setSettlementId(long settlementId) {
		this.settlementId = settlementId;
	}

	public int getStatus() {
		return status;
	}

	public void setStatus(int status) {
		this.status = status;
	}

	public String getTranxCode() {
		return tranxCode;
	}

	public void setTranxCode(String tranxCode) {
		this.tranxCode = tranxCode;
	}

	public Date getTranxDate() {
		return tranxDate;
	}

	public void setTranxDate(Date tranxDate) {

		this.tranxDate = tranxDate;

		if (tranxDate != null) {
			this.tranxDateForm = new DateForm(tranxDate);
		}
	}
	public DateForm getTranxDateForm() {
		return tranxDateForm;
	}

	public void setTranxDateForm(DateForm tranxDateForm) {
		this.tranxDateForm = tranxDateForm;
		tranxDate = tranxDateForm.toDate();
	}
	
	public String getOriginalCurr() {
		return originalCurr;
	}

	public void setOriginalCurr(String originalCurr) {
		this.originalCurr = originalCurr;
	}

	public double getAmountCurr() {
		return amountCurr;
	}

	public void setAmountCurr(double amountCurr) {
		this.amountCurr = amountCurr;
	}

	public String getRefNumber() {
		return refNumber;
	}

	public void setRefNumber(String refNumber) {
		this.refNumber = refNumber;
	}

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	

	public String getOriginalCurrName() {
		return originalCurrName;
	}

	public void setOriginalCurrName(String originalCurrName) {
		this.originalCurrName = originalCurrName;
	}

	public String getCurrName() {
		return currName;
	}

	public void setCurrName(String currName) {
		this.currName = currName;
	}

	
	
	
}
