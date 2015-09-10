package org.transinfo.cacis.dto.settings;

import java.io.Serializable;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

@SuppressWarnings( { "unchecked", "serial" })
public class CardProductDto implements Serializable {

	private String cardProductId;
	private String issuerId;
	private String cardProductName;
	private CardTypeDto cardType;
	private String bin;
	private String serviceCode;
	private CardProductTypeDto cardProductType;
	private int expireTime;
	//private float floorLimit;
	private int floorLimit;
	private int dueDays;
	private String status;
	private Date updatedDate = new Date();
	private String userId;

	private Integer binLength;
	private Integer pinLength;
	private Integer cardNoLength;
	private Integer embossNameLength;
	private String pinRequired;
	private String renewalCardActive;
	private Integer firstIssueValidity;
	private Integer renewalIssueValidity;
	private Integer maxPinRetry;
	private String accountCreation;
	// private String salaryProfile;
	private Integer cardNoNextValue;
	private String pinMailerRequired;
	private String cardActivationOn;
	private String newOrSameCardnumberForReplacement;
	private String branchIdInclude;

	private String eComEnable;

	private Set cardproductrate = new HashSet();
	private Set creditLimit = new HashSet();
	
	private String low3rdRange;
	private Long creditLowerLimit;
	private Long creditUpperLimit;

	public CardProductDto() {
	}

	public String getCardProductId() {
		return cardProductId;
	}

	public void setCardProductId(String cardProductId) {
		this.cardProductId = cardProductId;
	}

	public String getIssuerId() {
		return issuerId;
	}

	public void setIssuerId(String issuerId) {
		this.issuerId = issuerId;
	}

	public String getCardProductName() {
		return cardProductName;
	}

	public void setCardProductName(String cardProductName) {
		this.cardProductName = cardProductName;
	}

	public CardTypeDto getCardType() {
		return cardType;
	}

	public void setCardType(CardTypeDto cardType) {
		this.cardType = cardType;
	}

	public String getBin() {
		return bin;
	}

	public void setBin(String bin) {
		this.bin = bin;
	}

	public String getServiceCode() {
		return serviceCode;
	}

	public void setServiceCode(String serviceCode) {
		this.serviceCode = serviceCode;
	}

	public CardProductTypeDto getCardProductType() {
		return cardProductType;
	}

	public void setCardProductType(CardProductTypeDto cardProductType) {
		this.cardProductType = cardProductType;
	}

	public int getExpireTime() {
		return expireTime;
	}

	public void setExpireTime(int expireTime) {
		this.expireTime = expireTime;
	}
	
	public int getDueDays() {
		return dueDays;
	}

	public void setDueDays(int dueDays) {
		this.dueDays = dueDays;
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

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public String getPinRequired() {
		return pinRequired;
	}

	public void setPinRequired(String pinRequired) {
		this.pinRequired = pinRequired;
	}

	public String getRenewalCardActive() {
		return renewalCardActive;
	}

	public void setRenewalCardActive(String renewalCardActive) {
		this.renewalCardActive = renewalCardActive;
	}

	public String getAccountCreation() {
		return accountCreation;
	}

	public void setAccountCreation(String accountCreation) {
		this.accountCreation = accountCreation;
	}

	public String getPinMailerRequired() {
		return pinMailerRequired;
	}

	public void setPinMailerRequired(String pinMailerRequired) {
		this.pinMailerRequired = pinMailerRequired;
	}

	public String getCardActivationOn() {
		return cardActivationOn;
	}

	public void setCardActivationOn(String cardActivationOn) {
		this.cardActivationOn = cardActivationOn;
	}

	public Set getCardproductrate() {
		return cardproductrate;
	}

	public void setCardproductrate(Set cardproductrate) {
		this.cardproductrate = cardproductrate;
	}

	public Integer getBinLength() {
		return binLength;
	}

	public void setBinLength(Integer binLength) {
		this.binLength = binLength;
	}

	public Integer getPinLength() {
		return pinLength;
	}

	public void setPinLength(Integer pinLength) {
		this.pinLength = pinLength;
	}

	public Integer getCardNoLength() {
		return cardNoLength;
	}

	public void setCardNoLength(Integer cardNoLength) {
		this.cardNoLength = cardNoLength;
	}

	public Integer getEmbossNameLength() {
		return embossNameLength;
	}

	public void setEmbossNameLength(Integer embossNameLength) {
		this.embossNameLength = embossNameLength;
	}

	public Integer getFirstIssueValidity() {
		return firstIssueValidity;
	}

	public void setFirstIssueValidity(Integer firstIssueValidity) {
		this.firstIssueValidity = firstIssueValidity;
	}

	public Integer getRenewalIssueValidity() {
		return renewalIssueValidity;
	}

	public void setRenewalIssueValidity(Integer renewalIssueValidity) {
		this.renewalIssueValidity = renewalIssueValidity;
	}

	public Integer getMaxPinRetry() {
		return maxPinRetry;
	}

	public void setMaxPinRetry(Integer maxPinRetry) {
		this.maxPinRetry = maxPinRetry;
	}

	public Integer getCardNoNextValue() {
		return cardNoNextValue;
	}

	public void setCardNoNextValue(Integer cardNoNextValue) {
		this.cardNoNextValue = cardNoNextValue;
	}

	public String getNewOrSameCardnumberForReplacement() {
		return newOrSameCardnumberForReplacement;
	}

	public void setNewOrSameCardnumberForReplacement(
			String newOrSameCardnumberForReplacement) {
		this.newOrSameCardnumberForReplacement = newOrSameCardnumberForReplacement;
	}

	public int getFloorLimit() {
		return floorLimit;
	}

	public void setFloorLimit(int floorLimit) {
		this.floorLimit = floorLimit;
	}

	public String getBranchIdInclude() {
		return branchIdInclude;
	}

	public void setBranchIdInclude(String branchIdInclude) {
		this.branchIdInclude = branchIdInclude;
	}

	public String geteComEnable() {
		return eComEnable;
	}

	public void seteComEnable(String eComEnable) {
		this.eComEnable = eComEnable;
	}

	public String getLow3rdRange() {
		return low3rdRange;
	}

	public void setLow3rdRange(String low3rdRange) {
		this.low3rdRange = low3rdRange;
	}

	public Long getCreditLowerLimit() {
		return creditLowerLimit;
	}

	public void setCreditLowerLimit(Long creditLowerLimit) {
		this.creditLowerLimit = creditLowerLimit;
	}

	public Long getCreditUpperLimit() {
		return creditUpperLimit;
	}

	public void setCreditUpperLimit(Long creditUpperLimit) {
		this.creditUpperLimit = creditUpperLimit;
	}

	public Set getCreditLimit() {
		return creditLimit;
	}

	public void setCreditLimit(Set creditLimit) {
		this.creditLimit = creditLimit;
	}
	
}
