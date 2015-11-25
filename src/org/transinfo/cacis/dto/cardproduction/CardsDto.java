package org.transinfo.cacis.dto.cardproduction;

import java.io.Serializable;
import java.util.Date;

import org.transinfo.cacis.dto.settings.CardProductDto;

@SuppressWarnings( { "unchecked", "serial" })
public class CardsDto implements Serializable {

	/*
	 * private long cardNumber =1234; private String customerId="1"; private
	 * String cardProductId="1"; private String issuerId="Issuer1"; private
	 * String accountId="1"; private int cardHolderType = 1; private int
	 * cardExpDate =0704; private Date effectiveDate = new Date(); private long
	 * cardStatusId =0; private int cvki=1; private int pvki =1; private int cvv
	 * =1; private int cvv2=1; private int pvv =1; private int opvv =1; private
	 * int pvvOfSet =1; private int wrongPinCount =1; private char pinDisabled
	 * ='Y'; private String nip ="sdf"; private String visaCode ="02"; private
	 * Date billingDate = new Date(); private String status ="A"; private String
	 * lastUpdatedBy ="ADMIN"; private Date updatedDate = new Date();
	 */

	/**
	 * 
	 */
	private long cardNumber;
	private String customerId;
	private String cardProductId;
	private int creditLimitPercent;
	private int cashLimitPercent;
	private String issuerId;
	private String accountId;
	private int cardHolderType;
	private String cardExpDate;
	private Date effectiveDate;
	private long cardStatusId;
	private int cvki;
	private int pvki;
	private int cvv;
	private int cvv2;
	private int pvv;
	private int opvv;
	private long pvvOffSet;
	private int wrongPinCount;
	private char pinDisabled;
	private String nip;
	private String visaCode;
	private Date billingDate;
	private String status;
	private String lastUpdatedBy;
	private Date updatedDate;
	private String pinBlock;

	// to get back track1 and track2 data only. NOT mapping with xml file
	private String track1;
	private String track2;

	/*
	 * these for showing in CusomerService Screens selects data using hibernate
	 * formula
	 */
	private String cardStatus;
	private String creditLimit;
	/*
	 * this is for Cards to Card_Products many-to-one for showing data to the
	 * CusomerService screes
	 */
	private CardProductDto cardProductsDto;

	// this for Cards to Customer many-to-one
	private ApplicationProcessDto appProcessDto;

	// this for Cards to CustomerAccount many-to-one
	private CustomerAccountDto custAccountDto;

	private String reasonCode;
	private Character pinReset;

	private String branchId;

	// this is only for card product change, to identify the old card no
	private String oldCradNo;
	// only for reporting
	private String custName;
	private String nricId;

	private String cycleNo;

	private String icvv;

	private String lastStatementId;
	private Date lastStatementDate;

	private String eComEnable="N";

	private String oc="1";

	private Double cashUsed;
	private Double purchaseUsed;

	private String maskedCardNo;
	private String encryptedCardNo;
	
	private String batchId;
	private String corporateId;
	

	public String getBatchId() {
		return batchId;
	}

	public void setBatchId(String batchId) {
		this.batchId = batchId;
	}

	public String getCorporateId() {
		return corporateId;
	}

	public void setCorporateId(String corporateId) {
		this.corporateId = corporateId;
	}

	public String getOc() {
		return oc;
	}

	public void setOc(String oc) {
		this.oc = oc;
	}

	public CardsDto() {

	}

	public CardsDto(ApplicationProcessDto appProcessDto) {
		this.appProcessDto = appProcessDto;
		appProcessDto.getCustomerCards().add(this);
	}

	public CardsDto(CustomerAccountDto custAccountDto) {
		this.custAccountDto = custAccountDto;
		custAccountDto.getCustomerCards().add(this);
	}

	public String getAccountId() {
		return accountId;
	}

	public void setAccountId(String accountId) {
		this.accountId = accountId;
	}

	public Date getBillingDate() {
		return billingDate;
	}

	public void setBillingDate(Date billingDate) {
		this.billingDate = billingDate;
	}

	public int getCardHolderType() {
		return cardHolderType;
	}

	public void setCardHolderType(int cardHolderType) {
		this.cardHolderType = cardHolderType;
	}

	public long getCardStatusId() {
		return cardStatusId;
	}

	public void setCardStatusId(long cardStatusId) {
		this.cardStatusId = cardStatusId;
	}

	public String getCustomerId() {
		return customerId;
	}

	public void setCustomerId(String customerId) {
		this.customerId = customerId;
	}

	public int getCvki() {
		return cvki;
	}

	public void setCvki(int cvki) {
		this.cvki = cvki;
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

	public Date getEffectiveDate() {
		return effectiveDate;
	}

	public void setEffectiveDate(Date effectiveDate) {
		this.effectiveDate = effectiveDate;
	}

	public String getIssuerId() {
		return issuerId;
	}

	public void setIssuerId(String issuerId) {
		this.issuerId = issuerId;
	}

	public String getLastUpdatedBy() {
		return lastUpdatedBy;
	}

	public void setLastUpdatedBy(String lastUpdatedBy) {
		this.lastUpdatedBy = lastUpdatedBy;
	}

	public String getNip() {
		return nip;
	}

	public void setNip(String nip) {
		this.nip = nip;
	}

	public int getOpvv() {
		return opvv;
	}

	public void setOpvv(int opvv) {
		this.opvv = opvv;
	}

	public char getPinDisabled() {
		return pinDisabled;
	}

	public void setPinDisabled(char pinDisabled) {
		this.pinDisabled = pinDisabled;
	}

	public int getPvki() {
		return pvki;
	}

	public void setPvki(int pvki) {
		this.pvki = pvki;
	}

	public int getPvv() {
		return pvv;
	}

	public void setPvv(int pvv) {
		this.pvv = pvv;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public Date getUpdatedDate() {
		return updatedDate;
	}

	public void setUpdatedDate(Date updatedDate) {
		this.updatedDate = updatedDate;
	}

	public String getVisaCode() {
		return visaCode;
	}

	public void setVisaCode(String visaCode) {
		this.visaCode = visaCode;
	}

	public int getWrongPinCount() {
		return wrongPinCount;
	}

	public void setWrongPinCount(int wrongPinCount) {
		this.wrongPinCount = wrongPinCount;
	}

	public CustomerAccountDto getCustAccountDto() {
		return custAccountDto;
	}

	public void setCustAccountDto(CustomerAccountDto custAccountDto) {
		this.custAccountDto = custAccountDto;
	}

	public long getPvvOffSet() {
		return pvvOffSet;
	}

	public void setPvvOffSet(long pvvOffSet) {
		this.pvvOffSet = pvvOffSet;
	}

	public ApplicationProcessDto getAppProcessDto() {
		return appProcessDto;
	}

	public void setAppProcessDto(ApplicationProcessDto appProcessDto) {
		this.appProcessDto = appProcessDto;
	}

	public String getCardProductId() {
		return cardProductId;
	}

	public void setCardProductId(String cardProductId) {
		this.cardProductId = cardProductId;
	}

	public String getCardExpDate() {
		return cardExpDate;
	}

	public void setCardExpDate(String cardExpDate) {
		this.cardExpDate = cardExpDate;
	}

	public CardProductDto getCardProductsDto() {
		return cardProductsDto;
	}

	public void setCardProductsDto(CardProductDto cardProductsDto) {
		this.cardProductsDto = cardProductsDto;
	}

	public String getCardStatus() {
		return cardStatus;
	}

	public void setCardStatus(String cardStatus) {
		this.cardStatus = cardStatus;
	}

	public String getCreditLimit() {
		return creditLimit;
	}

	public void setCreditLimit(String creditLimit) {
		this.creditLimit = creditLimit;
	}

	public int getCashLimitPercent() {
		return cashLimitPercent;
	}

	public void setCashLimitPercent(int cashLimitPercent) {
		this.cashLimitPercent = cashLimitPercent;
	}

	public int getCreditLimitPercent() {
		return creditLimitPercent;
	}

	public void setCreditLimitPercent(int creditLimitPercent) {
		this.creditLimitPercent = creditLimitPercent;
	}

	public long getCardNumber() {
		return cardNumber;
	}

	public void setCardNumber(long cardNumber) {
		this.cardNumber = cardNumber;
	}

	public String getReasonCode() {
		return reasonCode;
	}

	public void setReasonCode(String reasonCode) {
		this.reasonCode = reasonCode;
	}

	public Character getPinReset() {
		return pinReset;
	}

	public void setPinReset(Character pinReset) {
		this.pinReset = pinReset;
	}

	public String getPinBlock() {
		return pinBlock;
	}

	public void setPinBlock(String pinBlock) {
		this.pinBlock = pinBlock;
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

	public String getOldCradNo() {
		return oldCradNo;
	}

	public void setOldCradNo(String oldCradNo) {
		this.oldCradNo = oldCradNo;
	}

	public String getCustName() {
		return custName;
	}

	public void setCustName(String custName) {
		this.custName = custName;
	}

	public String getNricId() {
		return nricId;
	}

	public void setNricId(String nricId) {
		this.nricId = nricId;
	}

	public String getBranchId() {
		return branchId;
	}

	public void setBranchId(String branchId) {
		this.branchId = branchId;
	}

	public String getCycleNo() {
		return cycleNo;
	}

	public void setCycleNo(String cycleNo) {
		this.cycleNo = cycleNo;
	}

	public String getIcvv() {
		return icvv;
	}

	public void setIcvv(String icvv) {
		this.icvv = icvv;
	}

	public String getLastStatementId() {
		return lastStatementId;
	}

	public void setLastStatementId(String lastStatementId) {
		this.lastStatementId = lastStatementId;
	}

	public String geteComEnable() {
		return eComEnable;
	}

	public void seteComEnable(String eComEnable) {
		this.eComEnable = eComEnable;
	}

	public Double getCashUsed() {
		return cashUsed;
	}

	public void setCashUsed(Double cashUsed) {
		this.cashUsed = cashUsed;
	}

	public Double getPurchaseUsed() {
		return purchaseUsed;
	}

	public void setPurchaseUsed(Double purchaseUsed) {
		this.purchaseUsed = purchaseUsed;
	}

	public Date getLastStatementDate() {
		return lastStatementDate;
	}

	public void setLastStatementDate(Date lastStatementDate) {
		this.lastStatementDate = lastStatementDate;
	}

	public String getMaskedCardNo() {
		return maskedCardNo;
	}

	public void setMaskedCardNo(String maskedCardNo) {
		this.maskedCardNo = maskedCardNo;
	}

	public String getEncryptedCardNo() {
		return encryptedCardNo;
	}

	public void setEncryptedCardNo(String encryptedCardNo) {
		this.encryptedCardNo = encryptedCardNo;
	}

}
