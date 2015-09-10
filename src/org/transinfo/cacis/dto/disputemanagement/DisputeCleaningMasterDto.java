package org.transinfo.cacis.dto.disputemanagement;

import java.io.Serializable;
import java.util.Date;

import org.transinfo.cacis.dto.csr.TransactionLogDto;

@SuppressWarnings("serial")
public class DisputeCleaningMasterDto implements Serializable {

	private String settlementID;
	private String arn;
	private TransactionLogDto tranxLog;
	private Base2TranxCodeDto tranxCode;
	//private String tranxCode;
	private String acquirerID;
	private String purchaseDate;
	private String ctfAmt;
	private String ctfCurrency;
	private String usageCode;
	private DisputeGroupDetailsDto reason;
	private String athorizationCode;
	private String motoECIID;
	private String isDispute;
	private String disputeReason;
	private Date updatedDate;
	private String updatedBy;
	private String status;
	private String remarks;
	private String merchantName;
	private String merchantCity;
	private String merchantCountry;
	private String posTerminalCap;
	private String intlFeeIndex;
	private String offOnLine;
	private String cardNo;
	private String tranxFound;
	private String motoEci;
	
	private double formatAmount;
	
	private DisputeClearanceCTFDetails disputeCTFDetails;

	public String getArn() {
		return arn;
	}

	public void setArn(String arn) {
		this.arn = arn;
	}

	public TransactionLogDto getTranxLog() {
		return tranxLog;
	}

	public void setTranxLog(TransactionLogDto tranxLog) {
		this.tranxLog = tranxLog;
	}

	public String getAcquirerID() {
		return acquirerID;
	}

	public void setAcquirerID(String acquirerID) {
		this.acquirerID = acquirerID;
	}

	public String getPurchaseDate() {
		return purchaseDate;
	}

	public void setPurchaseDate(String purchaseDate) {
		this.purchaseDate = purchaseDate;
	}

	public String getUsageCode() {
		return usageCode;
	}

	public void setUsageCode(String usageCode) {
		this.usageCode = usageCode;
	}

	public DisputeGroupDetailsDto getReason() {
		return reason;
	}

	public void setReason(DisputeGroupDetailsDto reason) {
		this.reason = reason;
	}

	public String getAthorizationCode() {
		return athorizationCode;
	}

	public void setAthorizationCode(String athorizationCode) {
		this.athorizationCode = athorizationCode;
	}

	public String getMotoECIID() {
		return motoECIID;
	}

	public void setMotoECIID(String motoECIID) {
		this.motoECIID = motoECIID;
	}

	public String getIsDispute() {
		return isDispute;
	}

	public void setIsDispute(String isDispute) {
		this.isDispute = isDispute;
	}

	public String getDisputeReason() {
		return disputeReason;
	}

	public void setDisputeReason(String disputeReason) {
		this.disputeReason = disputeReason;
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

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getSettlementID() {
		return settlementID;
	}

	public void setSettlementID(String settlementID) {
		this.settlementID = settlementID;
	}

	public String getCtfAmt() {
		return ctfAmt;
	}

	public void setCtfAmt(String ctfAmt) {
		this.ctfAmt = ctfAmt;
	}

	public String getCtfCurrency() {
		return ctfCurrency;
	}

	public void setCtfCurrency(String ctfCurrency) {
		this.ctfCurrency = ctfCurrency;
	}

	public String getRemarks() {
		return remarks;
	}

	public void setRemarks(String remarks) {
		this.remarks = remarks;
	}

	public String getMerchantName() {
		return merchantName;
	}

	public void setMerchantName(String merchantName) {
		this.merchantName = merchantName;
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

	public String getPosTerminalCap() {
		return posTerminalCap;
	}

	public void setPosTerminalCap(String posTerminalCap) {
		this.posTerminalCap = posTerminalCap;
	}

	public String getIntlFeeIndex() {
		return intlFeeIndex;
	}

	public void setIntlFeeIndex(String intlFeeIndex) {
		this.intlFeeIndex = intlFeeIndex;
	}

	public DisputeClearanceCTFDetails getDisputeCTFDetails() {
		return disputeCTFDetails;
	}

	public void setDisputeCTFDetails(DisputeClearanceCTFDetails disputeCTFDetails) {
		this.disputeCTFDetails = disputeCTFDetails;
	}

	public String getCardNo() {
		return cardNo;
	}

	public void setCardNo(String cardNo) {
		this.cardNo = cardNo;
	}

	public double getFormatAmount() {		
		return this.formatAmount;
	}

	public void setFormatAmount(double formatAmount) {
		this.formatAmount = formatAmount;
	}

	public String getOffOnLine() {
		return offOnLine;
	}

	public void setOffOnLine(String offOnLine) {
		this.offOnLine = offOnLine;
	}

	public String getTranxFound() {
		return tranxFound;
	}

	public void setTranxFound(String tranxFound) {
		this.tranxFound = tranxFound;
	}

	public Base2TranxCodeDto getTranxCode() {
		return tranxCode;
	}

	public void setTranxCode(Base2TranxCodeDto tranxCode) {
		this.tranxCode = tranxCode;
	}

	public String getMotoEci() {
		return motoEci;
	}

	public void setMotoEci(String motoEci) {
		this.motoEci = motoEci;
	}
}