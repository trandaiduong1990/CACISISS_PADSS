package org.transinfo.cacis.formbean.customerservice;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.struts.action.ActionMapping;
import org.apache.struts.validator.ValidatorActionForm;
import org.transinfo.cacis.common.constants.CommonCodes;
import org.transinfo.cacis.dataacess.DAOFactory;
import org.transinfo.cacis.dataacess.dao.customerservice.CardReplacementDAO;
import org.transinfo.cacis.formbean.common.AddressForm;

@SuppressWarnings({ "serial" })
public class CardReplacementForm extends ValidatorActionForm {

	private String issuerId;
	private String customerId;
	private String applicationId;
	private String cardNumber;
	private String idNumber;
	private String cardHolderType;
	private String branchId;
	private String cardProductId;
	private String applicationType;
	private String applicationStatus;
	private String reasonCode;
	private String remarks;
	private String userId;
	// for pinresend checking cardstatus is active or not
	private String cardStatusId;
	// for cardHolderLimitAdjustment
	private String creditLimit;
	private String cashLimit;

	// for prelist data
	private Map countryList;
	private Map cardStatusList;

	// this for address
	AddressForm requestAddress = new AddressForm();
	AddressForm changeAddress = new AddressForm();

	private String blockReason;
	private String cardAction;
	private String feeApplicable = "Y";
	private String replacementFees;

	private String customerName;
	private String nricNo;

	private String orgBackDate;

	private Map stateList;
	private Map cityList;
	private Map townshipList;

	private String immeidateProcess = "N";

	private String eComEnable;

	private List feeTranx;
	private String totFeeAmt;

	private String limitUsed;
	private String totBal;

	private String accountId;

	private String maskedCardNo;
	private String encryptedCardNo;
	private String instantReplacement = "N";
	private String replacementCardNo;

	
	public String getReplacementCardNo() {
		return replacementCardNo;
	}

	public void setReplacementCardNo(String replacementCardNo) {
		this.replacementCardNo = replacementCardNo;
	}

	public String getTotFeeAmt() {
		return totFeeAmt;
	}

	public void setTotFeeAmt(String totFeeAmt) {
		this.totFeeAmt = totFeeAmt;
	}

	public List getFeeTranx() {
		return feeTranx;
	}

	public void setFeeTranx(List feeTranx) {
		this.feeTranx = feeTranx;
	}

	public CardReplacementForm() {
		getPreListData();
	}

	public String getCardNumber() {
		return cardNumber;
	}

	public void setCardNumber(String cardNumber) {
		this.cardNumber = cardNumber;
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

	public void getPreListData() {

		try {

			CardReplacementDAO objDAO = DAOFactory.getInstance()
					.getCardReplacementDAO();
			if (countryList == null) {
				countryList = objDAO.countryListData();
			}
			if (cardStatusList == null) {
				cardStatusList = objDAO
						.cardStatusListData(CommonCodes.CARD_GREATER);

			}

		} catch (Exception e) {
			System.out.println("Error while getting  PreListData:"
					+ e.getMessage());
		}

	}

	public AddressForm getRequestAddress() {
		return requestAddress;
	}

	public void setRequestAddress(AddressForm requestAddress) {
		this.requestAddress = requestAddress;
	}

	public String getApplicationId() {
		// return applicationId =IdsGenartion.GenerateApplicationId();
		return applicationId;
	}

	public void setApplicationId(String applicationId) {
		this.applicationId = applicationId;
	}

	public Map getCardStatusList() {

		return cardStatusList;
	}

	public void setCardStatusList(Map cardStatusList) {
		this.cardStatusList = cardStatusList;
	}

	public String getIssuerId() {
		return issuerId;
	}

	public void setIssuerId(String issuerId) {
		this.issuerId = issuerId;
	}

	public String getApplicationStatus() {
		return applicationStatus;
	}

	public void setApplicationStatus(String applicationStatus) {
		this.applicationStatus = applicationStatus;
	}

	public String getApplicationType() {
		return applicationType;
	}

	public void setApplicationType(String applicationType) {
		this.applicationType = applicationType;
	}

	public String getBranchId() {
		return branchId;
	}

	public void setBranchId(String branchId) {
		this.branchId = branchId;
	}

	public String getCardProductId() {
		return cardProductId;
	}

	public void setCardProductId(String cardProductId) {
		this.cardProductId = cardProductId;
	}

	public String getCardHolderType() {
		return cardHolderType;
	}

	public void setCardHolderType(String cardHolderType) {
		this.cardHolderType = cardHolderType;
	}

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public String getCustomerId() {
		return customerId;
	}

	public void setCustomerId(String customerId) {
		this.customerId = customerId;
	}

	public String getIdNumber() {
		return idNumber;
	}

	public void setIdNumber(String idNumber) {
		this.idNumber = idNumber;
	}

	public String getCardStatusId() {
		return cardStatusId;
	}

	public void setCardStatusId(String cardStatusId) {
		this.cardStatusId = cardStatusId;
	}

	public String getCashLimit() {
		return cashLimit;
	}

	public void setCashLimit(String cashLimit) {
		this.cashLimit = cashLimit;
	}

	public String getCreditLimit() {
		return creditLimit;
	}

	public void setCreditLimit(String creditLimit) {
		this.creditLimit = creditLimit;
	}

	public void setCountryList(Map countryList) {
		this.countryList = countryList;
	}

	public Map getCountryList() {
		return countryList;
	}

	// Reset form fields.
	public void reset(ActionMapping mapping, HttpServletRequest request) {
		cardNumber = null;
		remarks = null;

	}

	public String getBlockReason() {
		return blockReason;
	}

	public void setBlockReason(String blockReason) {
		this.blockReason = blockReason;
	}

	public String getCardAction() {
		return cardAction;
	}

	public void setCardAction(String cardAction) {
		this.cardAction = cardAction;
	}

	public String getFeeApplicable() {
		return feeApplicable;
	}

	public void setFeeApplicable(String feeApplicable) {
		this.feeApplicable = feeApplicable;
	}

	public String getReplacementFees() {
		return replacementFees;
	}

	public void setReplacementFees(String replacementFees) {
		this.replacementFees = replacementFees;
	}

	public String getCustomerName() {
		return customerName;
	}

	public void setCustomerName(String customerName) {
		this.customerName = customerName;
	}

	public String getNricNo() {
		return nricNo;
	}

	public void setNricNo(String nricNo) {
		this.nricNo = nricNo;
	}

	public Map getStateList() {
		return stateList;
	}

	public void setStateList(Map stateList) {
		this.stateList = stateList;
	}

	public Map getCityList() {
		return cityList;
	}

	public void setCityList(Map cityList) {
		this.cityList = cityList;
	}

	public Map getTownshipList() {
		return townshipList;
	}

	public void setTownshipList(Map townshipList) {
		this.townshipList = townshipList;
	}

	public AddressForm getChangeAddress() {
		return changeAddress;
	}

	public void setChangeAddress(AddressForm changeAddress) {
		this.changeAddress = changeAddress;
	}

	public String getOrgBackDate() {
		return orgBackDate;
	}

	public void setOrgBackDate(String orgBackDate) {
		this.orgBackDate = orgBackDate;
	}

	public String getImmeidateProcess() {
		return immeidateProcess;
	}

	public void setImmeidateProcess(String immeidateProcess) {
		this.immeidateProcess = immeidateProcess;
	}

	public String geteComEnable() {
		return eComEnable;
	}

	public void seteComEnable(String eComEnable) {
		this.eComEnable = eComEnable;
	}

	public String getLimitUsed() {
		return limitUsed;
	}

	public void setLimitUsed(String limitUsed) {
		this.limitUsed = limitUsed;
	}

	public String getTotBal() {
		return totBal;
	}

	public void setTotBal(String totBal) {
		this.totBal = totBal;
	}

	public String getAccountId() {
		return accountId;
	}

	public void setAccountId(String accountId) {
		this.accountId = accountId;
	}

	public String getEncryptedCardNo() {
		return encryptedCardNo;
	}

	public void setEncryptedCardNo(String encryptedCardNo) {
		this.encryptedCardNo = encryptedCardNo;
	}

	public String getMaskedCardNo() {
		return maskedCardNo;
	}

	public void setMaskedCardNo(String maskedCardNo) {
		this.maskedCardNo = maskedCardNo;
	}

	public String getInstantReplacement() {
		return instantReplacement;
	}

	public void setInstantReplacement(String instantReplacement) {
		this.instantReplacement = instantReplacement;
	}

}
