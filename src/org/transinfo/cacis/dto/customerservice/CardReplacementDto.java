package org.transinfo.cacis.dto.customerservice;

import java.io.Serializable;
import java.util.Date;

import org.transinfo.cacis.dto.cardproduction.CustomerAccountDto;
import org.transinfo.cacis.dto.cardproduction.CustomerAddressDto;

@SuppressWarnings("serial")
public class CardReplacementDto implements Serializable {

	private String    applicationId;
	private String    issuerId;
	private long      cardNumber;
	private String    idNumber;
	private int       cardHolderType;
    private String    customerId;
	private String    branchId;
	private String    cardProductId ;
	private int       applicationType;
	private int       applicationStatus;
	private long      reasonCode;
	private String    remarks;
	//private String    replaceReason;
	private String   userId;
	private Date     updatedDate = new Date();
	private String   feeApplicable;
	private String immeidateProcess;
	private String eComEnable;
	private String instantReplacement;
	
	/*Setting AddressDto to CardReplacementDto*/
	// AddressDto addressDto = new AddressDto();
	
	 //Setting  CustomerAddressDto to CardReplacementDto
	 CustomerAddressDto customerAddDto = new CustomerAddressDto();
	 
	//this for Cards to CustomerAccount many-to-one
	 private CustomerAccountDto customerAccountDto;

	private long cardStatusId;
	private String blockReason;

	private String encryptedCardNo;
	 
	 public CardReplacementDto(){}
	 
		public String getApplicationId() {
			return applicationId;
		}
		public void setApplicationId(String applicationId) {
			this.applicationId = applicationId;
		}
	
	public long getCardNumber() {
		return new Long(cardNumber).longValue();
	}
	public void setCardNumber(long cardNumber) {
		this.cardNumber = cardNumber;
	}
	public String getIssuerId() {
		return issuerId;
	}
	public void setIssuerId(String issuerId) {
		this.issuerId = issuerId;
	}
	
	public long getReasonCode() {
		return reasonCode;
	}
	public void setReasonCode(long reasonCode) {
		this.reasonCode = reasonCode;
	}
	public String getRemarks() {
		return remarks;
	}
	public void setRemarks(String remarks) {
		this.remarks = remarks;
	}
	public Date getUpdatedDate() {
		return updatedDate;
	}
	public void setUpdatedDate(Date updatedDate) {
		this.updatedDate = updatedDate;
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
	public int getApplicationStatus() {
		return applicationStatus;
	}
	public void setApplicationStatus(int applicationStatus) {
		this.applicationStatus = applicationStatus;
	}
	public int getApplicationType() {
		return applicationType;
	}
	public void setApplicationType(int applicationType) {
		this.applicationType = applicationType;
	}

	public int getCardHolderType() {
		return cardHolderType;
	}
	public void setCardHolderType(int cardHolderType) {
		this.cardHolderType = cardHolderType;
	}

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public CustomerAccountDto getCustomerAccountDto() {
		return customerAccountDto;
	}

	public void setCustomerAccountDto(CustomerAccountDto customerAccountDto) {
		this.customerAccountDto = customerAccountDto;
	}

	public String getCustomerId() {
		return customerId;
	}

	public void setCustomerId(String customerId) {
		this.customerId = customerId;
	}

	public CustomerAddressDto getCustomerAddDto() {
		return customerAddDto;
	}

	public void setCustomerAddDto(CustomerAddressDto customerAddDto) {
		this.customerAddDto = customerAddDto;
	}

	public String getIdNumber() {
		return idNumber;
	}

	public void setIdNumber(String idNumber) {
		this.idNumber = idNumber;
	}

	public long getCardStatusId() {
		return cardStatusId;
	}

	public void setCardStatusId(long cardStatusId) {
		this.cardStatusId = cardStatusId;
	}

	public String getBlockReason() {
		return blockReason;
	}

	public void setBlockReason(String blockReason) {
		this.blockReason = blockReason;
	}

	public String getFeeApplicable() {
		return feeApplicable;
	}

	public void setFeeApplicable(String feeApplicable) {
		this.feeApplicable = feeApplicable;
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

	public String getEncryptedCardNo() {
		return encryptedCardNo;
	}

	public void setEncryptedCardNo(String encryptedCardNo) {
		this.encryptedCardNo = encryptedCardNo;
	}

	public String getInstantReplacement() {
		return instantReplacement;
	}

	public void setInstantReplacement(String instantReplacement) {
		this.instantReplacement = instantReplacement;
	}

	

}
