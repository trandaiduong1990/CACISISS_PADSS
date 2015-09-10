package org.transinfo.cacis.formbean.settings;

import java.util.Map;

import org.apache.struts.validator.ValidatorForm;
import org.transinfo.cacis.dataacess.DAOFactory;
import org.transinfo.cacis.dataacess.dao.settings.CardProductDAO;
import org.transinfo.cacis.dto.settings.CardProductTypeDto;
import org.transinfo.cacis.dto.settings.CardTypeDto;

@SuppressWarnings( { "unchecked", "serial" })
public class CardProductSetupForm extends ValidatorForm {

	private String cardProductId;
	private String issuerId;
	private String cardProductName;
	private CardTypeDto cardType = new CardTypeDto();
	private String bin;
	private String serviceCode;
	private CardProductTypeDto cardProductType = new CardProductTypeDto();
	private String expireTime;
	private String floorLimit;
	private String dueDays;
	private String status;
	private String userId;

	private String binLength;
	private String pinLength;
	private String cardNoLength;
	private String embossNameLength;
	private String pinRequired;
	private String renewalCardActive;
	private String firstIssueValidity;
	private String renewalIssueValidity;
	private String maxPinRetry;
	private String accountCreation;
	private String salaryProfile;
	private String cardNoNextValue;
	private String pinMailerRequired;
	private String cardActivationOn = "D";
	private String newOrSameCardnumberForReplacement = "S";
	private String branchIdInclude = "Y";

	private String eComEnable = "N";

	private Map cardTypeList;
	private Map cardProductTypeList;

	public CardProductSetupForm() {
		// getPreListData();
	}

	public void getPreListData() {

		try {

			CardProductDAO objDAO = DAOFactory.getInstance()
					.getCardProductDAO();

			if (cardTypeList == null) {
				cardTypeList = objDAO.cardTypeListData(issuerId);
			}
			if (cardProductTypeList == null) {
				cardProductTypeList = objDAO.cardProductTypeListData();
			}
		} catch (Exception e) {
			System.out
					.println("Error while getting  PreListData in CardProductSetup:"
							+ e.getMessage());
		}

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

	public String getExpireTime() {
		return expireTime;
	}

	public void setExpireTime(String expireTime) {
		this.expireTime = expireTime;
	}

	public String getFloorLimit() {
		return floorLimit;
	}

	public void setFloorLimit(String floorLimit) {
		this.floorLimit = floorLimit;
	}

	public String getDueDays() {
		return dueDays;
	}

	public void setDueDays(String dueDays) {
		this.dueDays = dueDays;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public String getBinLength() {
		return binLength;
	}

	public void setBinLength(String binLength) {
		this.binLength = binLength;
	}

	public String getPinLength() {
		return pinLength;
	}

	public void setPinLength(String pinLength) {
		this.pinLength = pinLength;
	}

	public String getCardNoLength() {
		return cardNoLength;
	}

	public void setCardNoLength(String cardNoLength) {
		this.cardNoLength = cardNoLength;
	}

	public String getEmbossNameLength() {
		return embossNameLength;
	}

	public void setEmbossNameLength(String embossNameLength) {
		this.embossNameLength = embossNameLength;
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

	public String getFirstIssueValidity() {
		return firstIssueValidity;
	}

	public void setFirstIssueValidity(String firstIssueValidity) {
		this.firstIssueValidity = firstIssueValidity;
	}

	public String getRenewalIssueValidity() {
		return renewalIssueValidity;
	}

	public void setRenewalIssueValidity(String renewalIssueValidity) {
		this.renewalIssueValidity = renewalIssueValidity;
	}

	public String getMaxPinRetry() {
		return maxPinRetry;
	}

	public void setMaxPinRetry(String maxPinRetry) {
		this.maxPinRetry = maxPinRetry;
	}

	public String getAccountCreation() {
		return accountCreation;
	}

	public void setAccountCreation(String accountCreation) {
		this.accountCreation = accountCreation;
	}

	public String getSalaryProfile() {
		return salaryProfile;
	}

	public void setSalaryProfile(String salaryProfile) {
		this.salaryProfile = salaryProfile;
	}

	public String getCardNoNextValue() {
		return cardNoNextValue;
	}

	public void setCardNoNextValue(String cardNoNextValue) {
		this.cardNoNextValue = cardNoNextValue;
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

	public Map getCardTypeList() {
		return cardTypeList;
	}

	public void setCardTypeList(Map cardTypeList) {
		this.cardTypeList = cardTypeList;
	}

	public Map getCardProductTypeList() {
		return cardProductTypeList;
	}

	public void setCardProductTypeList(Map cardProductTypeList) {
		this.cardProductTypeList = cardProductTypeList;
	}

	public CardTypeDto getCardType() {
		return cardType;
	}

	public void setCardType(CardTypeDto cardType) {
		this.cardType = cardType;
	}

	public CardProductTypeDto getCardProductType() {
		return cardProductType;
	}

	public void setCardProductType(CardProductTypeDto cardProductType) {
		this.cardProductType = cardProductType;
	}

	public String getNewOrSameCardnumberForReplacement() {
		return newOrSameCardnumberForReplacement;
	}

	public void setNewOrSameCardnumberForReplacement(
			String newOrSameCardnumberForReplacement) {
		this.newOrSameCardnumberForReplacement = newOrSameCardnumberForReplacement;
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
}
