package org.transinfo.cacis.formbean.disputemanagement;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import org.apache.struts.validator.ValidatorForm;
import org.transinfo.cacis.util.DateUtil;

@SuppressWarnings({ "unchecked", "serial" })
public class TransactionLogForm extends ValidatorForm {

	private String tranxLogId;
	private String issuerId;

	private Date dateTime;
	private String strDateTime;

	private String merchantName;
	private String merchantloc;
	private String merchantcountry;
	
	private String merchantId;
	private String terminalId;
	private String tranxCode;
	private String cardNumber;
	private String cardExpireDate;
	private String amount;
	private String currencyCode;
	private String mcc;
	private String tcc;
	private String track2Data;
	private String referenceNo;
	private String traceNo;
	private String acQcCountryCode;
	private String posentryMode;
	private String posConditionCode;
	private String acqId;
	private String responseCode;
	private String approvalCode;
	private String pinData;
	private String deleted;
	private String invoiceNo;
	private String traceNo2;
	private String originalAmount;
	private String originalCurrCode;
	private String f55Exist;
	private String f55PassThrough;
	private String f55ArqcStatus;
	private String f55ResponseCode;
	private String f55FinalResponseCode;
	private String f55ArpcStatus;
	private String f55ScriptStatus;
	private String f55cvrTvrStatus;
	private String f55responceException;
	private String f55applinterChangeProfile;
	private String f55issuerAuthData;
	private String f55Tvr;
	private String f55issuerApplData;
	private String f55applCryptogram;
	private String f55cryptoInoData;
	private String f55termCapabilities;
	private String f55cvmResults;
	private String f55terminalType;
	private String f55Atc;
	private String f55unpredicatableNo;
	private String currency;
	private String originalCurrency;

	private Set disputeCleaningMasters = new HashSet();

	private Date reconDate;
	private String strreconDate;
	private String recon;
	private String remarks;

	private String cardHolderTranxAmt;
	private String cardHolderTranxCurr;

	private String revertEnable="N";

	private String clearAmount;
	private String tranxCurrCovAmt;

	private String currConvFee;
	
	private String billedAmt;

	public String getTranxLogId() {
		return tranxLogId;
	}

	public void setTranxLogId(String tranxLogId) {
		this.tranxLogId = tranxLogId;
	}

	public String getIssuerId() {
		return issuerId;
	}

	public void setIssuerId(String issuerId) {
		this.issuerId = issuerId;
	}

	public Date getDateTime() {
		if (getStrDateTime() != null) {
			this.dateTime = DateUtil
					.convertTranxDateStringToDate(getStrDateTime());
		}
		return this.dateTime;
	}

	public void setDateTime(Date dateTime) {
		if (dateTime != null) {
			setStrDateTime(DateUtil.convertTranxDateToDateString(dateTime));
		}
		this.dateTime = dateTime;
	}

	public String getStrDateTime() {
		return strDateTime;
	}

	public void setStrDateTime(String strDateTime) {
		this.strDateTime = strDateTime;
	}

	public String getMerchantName() {
		return merchantName;
	}

	public void setMerchantName(String merchantName) {
		this.merchantName = merchantName;
	}

	public String getMerchantId() {
		return merchantId;
	}

	public void setMerchantId(String merchantId) {
		this.merchantId = merchantId;
	}

	public String getTerminalId() {
		return terminalId;
	}

	public void setTerminalId(String terminalId) {
		this.terminalId = terminalId;
	}

	public String getTranxCode() {
		return tranxCode;
	}

	public void setTranxCode(String tranxCode) {
		this.tranxCode = tranxCode;
	}

	public String getCardNumber() {
		return cardNumber;
	}

	public void setCardNumber(String cardNumber) {
		this.cardNumber = cardNumber;
	}

	public String getCardExpireDate() {
		return cardExpireDate;
	}

	public void setCardExpireDate(String cardExpireDate) {
		this.cardExpireDate = cardExpireDate;
	}

	public String getAmount() {
		return amount;
	}

	public void setAmount(String amount) {
		this.amount = amount;
	}

	public String getCurrencyCode() {
		return currencyCode;
	}

	public void setCurrencyCode(String currencyCode) {
		this.currencyCode = currencyCode;
	}

	public String getMcc() {
		return mcc;
	}

	public void setMcc(String mcc) {
		this.mcc = mcc;
	}

	public String getTcc() {
		return tcc;
	}

	public void setTcc(String tcc) {
		this.tcc = tcc;
	}

	public String getTrack2Data() {
		return track2Data;
	}

	public void setTrack2Data(String track2Data) {
		this.track2Data = track2Data;
	}

	public String getReferenceNo() {
		return referenceNo;
	}

	public void setReferenceNo(String referenceNo) {
		this.referenceNo = referenceNo;
	}

	public String getTraceNo() {
		return traceNo;
	}

	public void setTraceNo(String traceNo) {
		this.traceNo = traceNo;
	}

	public String getAcQcCountryCode() {
		return acQcCountryCode;
	}

	public void setAcQcCountryCode(String acQcCountryCode) {
		this.acQcCountryCode = acQcCountryCode;
	}

	public String getPosentryMode() {
		return posentryMode;
	}

	public void setPosentryMode(String posentryMode) {
		this.posentryMode = posentryMode;
	}

	public String getPosConditionCode() {
		return posConditionCode;
	}

	public void setPosConditionCode(String posConditionCode) {
		this.posConditionCode = posConditionCode;
	}

	public String getAcqId() {
		return acqId;
	}

	public void setAcqId(String acqId) {
		this.acqId = acqId;
	}

	public String getResponseCode() {
		return responseCode;
	}

	public void setResponseCode(String responseCode) {
		this.responseCode = responseCode;
	}

	public String getApprovalCode() {
		return approvalCode;
	}

	public void setApprovalCode(String approvalCode) {
		this.approvalCode = approvalCode;
	}

	public String getPinData() {
		return pinData;
	}

	public void setPinData(String pinData) {
		this.pinData = pinData;
	}

	public String getDeleted() {
		return deleted;
	}

	public void setDeleted(String deleted) {
		this.deleted = deleted;
	}

	public String getInvoiceNo() {
		return invoiceNo;
	}

	public void setInvoiceNo(String invoiceNo) {
		this.invoiceNo = invoiceNo;
	}

	public String getTraceNo2() {
		return traceNo2;
	}

	public void setTraceNo2(String traceNo2) {
		this.traceNo2 = traceNo2;
	}

	public String getOriginalAmount() {
		return originalAmount;
	}

	public void setOriginalAmount(String originalAmount) {
		this.originalAmount = originalAmount;
	}

	public String getOriginalCurrCode() {
		return originalCurrCode;
	}

	public void setOriginalCurrCode(String originalCurrCode) {
		this.originalCurrCode = originalCurrCode;
	}

	public String getF55Exist() {
		return f55Exist;
	}

	public void setF55Exist(String f55Exist) {
		this.f55Exist = f55Exist;
	}

	public String getF55PassThrough() {
		return f55PassThrough;
	}

	public void setF55PassThrough(String f55PassThrough) {
		this.f55PassThrough = f55PassThrough;
	}

	public String getF55ArqcStatus() {
		return f55ArqcStatus;
	}

	public void setF55ArqcStatus(String f55ArqcStatus) {
		this.f55ArqcStatus = f55ArqcStatus;
	}

	public String getF55ResponseCode() {
		return f55ResponseCode;
	}

	public void setF55ResponseCode(String f55ResponseCode) {
		this.f55ResponseCode = f55ResponseCode;
	}

	public String getF55FinalResponseCode() {
		return f55FinalResponseCode;
	}

	public void setF55FinalResponseCode(String f55FinalResponseCode) {
		this.f55FinalResponseCode = f55FinalResponseCode;
	}

	public String getF55ArpcStatus() {
		return f55ArpcStatus;
	}

	public void setF55ArpcStatus(String f55ArpcStatus) {
		this.f55ArpcStatus = f55ArpcStatus;
	}

	public String getF55ScriptStatus() {
		return f55ScriptStatus;
	}

	public void setF55ScriptStatus(String f55ScriptStatus) {
		this.f55ScriptStatus = f55ScriptStatus;
	}

	public String getF55cvrTvrStatus() {
		return f55cvrTvrStatus;
	}

	public void setF55cvrTvrStatus(String f55cvrTvrStatus) {
		this.f55cvrTvrStatus = f55cvrTvrStatus;
	}

	public String getF55responceException() {
		return f55responceException;
	}

	public void setF55responceException(String f55responceException) {
		this.f55responceException = f55responceException;
	}

	public String getF55applinterChangeProfile() {
		return f55applinterChangeProfile;
	}

	public void setF55applinterChangeProfile(String f55applinterChangeProfile) {
		this.f55applinterChangeProfile = f55applinterChangeProfile;
	}

	public String getF55issuerAuthData() {
		return f55issuerAuthData;
	}

	public void setF55issuerAuthData(String f55issuerAuthData) {
		this.f55issuerAuthData = f55issuerAuthData;
	}

	public String getF55Tvr() {
		return f55Tvr;
	}

	public void setF55Tvr(String f55Tvr) {
		this.f55Tvr = f55Tvr;
	}

	public String getF55issuerApplData() {
		return f55issuerApplData;
	}

	public void setF55issuerApplData(String f55issuerApplData) {
		this.f55issuerApplData = f55issuerApplData;
	}

	public String getF55applCryptogram() {
		return f55applCryptogram;
	}

	public void setF55applCryptogram(String f55applCryptogram) {
		this.f55applCryptogram = f55applCryptogram;
	}

	public String getF55cryptoInoData() {
		return f55cryptoInoData;
	}

	public void setF55cryptoInoData(String f55cryptoInoData) {
		this.f55cryptoInoData = f55cryptoInoData;
	}

	public String getF55termCapabilities() {
		return f55termCapabilities;
	}

	public void setF55termCapabilities(String f55termCapabilities) {
		this.f55termCapabilities = f55termCapabilities;
	}

	public String getF55cvmResults() {
		return f55cvmResults;
	}

	public void setF55cvmResults(String f55cvmResults) {
		this.f55cvmResults = f55cvmResults;
	}

	public String getF55terminalType() {
		return f55terminalType;
	}

	public void setF55terminalType(String f55terminalType) {
		this.f55terminalType = f55terminalType;
	}

	public String getF55Atc() {
		return f55Atc;
	}

	public void setF55Atc(String f55Atc) {
		this.f55Atc = f55Atc;
	}

	public String getF55unpredicatableNo() {
		return f55unpredicatableNo;
	}

	public void setF55unpredicatableNo(String f55unpredicatableNo) {
		this.f55unpredicatableNo = f55unpredicatableNo;
	}

	public String getCurrency() {
		return currency;
	}

	public void setCurrency(String currency) {
		this.currency = currency;
	}

	public String getOriginalCurrency() {
		return originalCurrency;
	}

	public void setOriginalCurrency(String originalCurrency) {
		this.originalCurrency = originalCurrency;
	}

	public Set getDisputeCleaningMasters() {
		return disputeCleaningMasters;
	}

	public void setDisputeCleaningMasters(Set disputeCleaningMasters) {
		this.disputeCleaningMasters = disputeCleaningMasters;
	}

	public Date getReconDate() {
		if (getStrreconDate() != null) {
			this.reconDate = DateUtil
					.convertTranxDateStringToDate(getStrreconDate());
		}
		return this.reconDate;
	}

	public void setReconDate(Date reconDate) {
		if (reconDate != null) {
			setStrreconDate(DateUtil.convertTranxDateToDateString(reconDate));
		}	
		this.reconDate = reconDate;
	}

	public String getStrreconDate() {
		return strreconDate;
	}

	public void setStrreconDate(String strreconDate) {
		this.strreconDate = strreconDate;
	}

	public String getRecon() {
		return recon;
	}

	public void setRecon(String recon) {
		this.recon = recon;
	}

	public String getRemarks() {
		return remarks;
	}

	public void setRemarks(String remarks) {
		this.remarks = remarks;
	}

	public String getMerchantloc() {
		return merchantloc;
	}

	public void setMerchantloc(String merchantloc) {
		this.merchantloc = merchantloc;
	}

	public String getMerchantcountry() {
		return merchantcountry;
	}

	public void setMerchantcountry(String merchantcountry) {
		this.merchantcountry = merchantcountry;
	}

	public String getCardHolderTranxAmt() {
		return cardHolderTranxAmt;
	}

	public void setCardHolderTranxAmt(String cardHolderTranxAmt) {
		this.cardHolderTranxAmt = cardHolderTranxAmt;
	}

	public String getCardHolderTranxCurr() {
		return cardHolderTranxCurr;
	}

	public void setCardHolderTranxCurr(String cardHolderTranxCurr) {
		this.cardHolderTranxCurr = cardHolderTranxCurr;
	}

	public String getRevertEnable() {
		return revertEnable;
	}

	public void setRevertEnable(String revertEnable) {
		this.revertEnable = revertEnable;
	}

	public String getClearAmount() {
		return clearAmount;
	}

	public void setClearAmount(String clearAmount) {
		this.clearAmount = clearAmount;
	}

	public String getTranxCurrCovAmt() {
		return tranxCurrCovAmt;
	}

	public void setTranxCurrCovAmt(String tranxCurrCovAmt) {
		this.tranxCurrCovAmt = tranxCurrCovAmt;
	}

	public String getCurrConvFee() {
		return currConvFee;
	}

	public void setCurrConvFee(String currConvFee) {
		this.currConvFee = currConvFee;
	}

	public String getBilledAmt() {
		return billedAmt;
	}

	public void setBilledAmt(String billedAmt) {
		this.billedAmt = billedAmt;
	}

}
