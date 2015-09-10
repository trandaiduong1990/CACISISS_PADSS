package org.transinfo.cacis.dto.customerservice;

import java.io.Serializable;
import java.util.Date;
import java.util.Map;

import org.transinfo.cacis.dataacess.DAOFactory;
import org.transinfo.cacis.dataacess.dao.customerservice.AccountAdjustmentDAO;
import org.transinfo.cacis.dto.cardproduction.CardsDto;
import org.transinfo.cacis.dto.cardproduction.CustomerAccountDto;

@SuppressWarnings( { "serial", "unchecked" })
public class AccountAdjustmentDto implements Serializable {

	/**
	 * 
	 */
	// private static final long serialVersionUID = 1L;
	private String sNo;
	private CardsDto card;
	private CustomerAccountDto customerAccount;
	private Map productList;
	private String issuerID;
	private String userId;

	private Long cardNo;
	private String cardType;
	private String cardProductName;
	private String cardStatus;

	private String cardLimit;

	private String adjustmentAmount;
	private String creditOrDebit;
	private Date effectiveDate = new Date();
	private Date updatedDate = new Date();
	private String reason;
	private String amtSrc;
	private String chgType;

	private Double preLimitUsed;
	private Double limitUsed;

	private Double prePurchaseUsed;
	private Double preCashUsed;
	private Double purchaseUsed;
	private Double cashUsed;
	private String tranxType;

	public long getCardNo() {
		return cardNo;
	}

	public void setCardNo(long cardNo) {
		this.cardNo = cardNo;
	}

	public String getCardType() {
		return cardType;
	}

	public void setCardType(String cardType) {
		this.cardType = cardType;
	}

	public String getCardProductName() {
		return cardProductName;
	}

	public void setCardProductName(String cardProductName) {
		this.cardProductName = cardProductName;
	}

	public String getCardStatus() {
		return cardStatus;
	}

	public void setCardStatus(String cardStatus) {
		this.cardStatus = cardStatus;
	}

	public String getCardLimit() {
		return cardLimit;
	}

	public void setCardLimit(String cardLimit) {
		this.cardLimit = cardLimit;
	}

	public String getIssuerID() {
		return issuerID;
	}

	public void setIssuerID(String issuerID) {
		this.issuerID = issuerID;
	}

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public String getAdjustmentAmount() {
		return adjustmentAmount;
	}

	public void setAdjustmentAmount(String adjustmentAmount) {
		this.adjustmentAmount = adjustmentAmount;
	}

	public String getCreditOrDebit() {
		return creditOrDebit;
	}

	public void setCreditOrDebit(String creditOrDebit) {
		this.creditOrDebit = creditOrDebit;
	}

	public Date getEffectiveDate() {
		return effectiveDate;
	}

	public void setEffectiveDate(Date effectiveDate) {
		this.effectiveDate = effectiveDate;
	}

	public String getReason() {
		return reason;
	}

	public void setReason(String reason) {
		this.reason = reason;
	}

	public void getPreListData(String issuerId) {

		try {

			AccountAdjustmentDAO objDAO = DAOFactory.getInstance()
			.getAccountAdjustmentDAO();
			if (productList == null || productList.isEmpty()) {
				productList = objDAO.cardProductListData(issuerId);
			}

		} catch (Exception e) {
			System.out
			.println("Error while getting  PreListData in AccountAdjustmentDto:"
					+ e.getMessage());
		}

	}

	/*
	 * // Reset form fields. public void reset(ActionMapping mapping,
	 * HttpServletRequest request) { cardNo = null; changeProductID = null;
	 * existCardStatus = null; }
	 */
	public CardsDto getCard() {
		return card;
	}

	public void setCard(CardsDto card) {
		this.card = card;
	}

	public String getsNo() {
		return sNo;
	}

	public void setsNo(String sNo) {
		this.sNo = sNo;
	}

	public String getAmtSrc() {
		return amtSrc;
	}

	public void setAmtSrc(String amtSrc) {
		this.amtSrc = amtSrc;
	}

	public String getChgType() {
		return chgType;
	}

	public void setChgType(String chgType) {
		this.chgType = chgType;
	}

	public Date getUpdatedDate() {
		return updatedDate;
	}

	public void setUpdatedDate(Date updatedDate) {
		this.updatedDate = updatedDate;
	}

	public Map getProductList() {
		return productList;
	}

	public void setProductList(Map productList) {
		this.productList = productList;
	}

	public CustomerAccountDto getCustomerAccount() {
		return customerAccount;
	}

	public void setCustomerAccount(CustomerAccountDto customerAccount) {
		this.customerAccount = customerAccount;
	}

	public Double getPreLimitUsed() {
		return preLimitUsed;
	}

	public void setPreLimitUsed(Double preLimitUsed) {
		this.preLimitUsed = preLimitUsed;
	}

	public Double getLimitUsed() {
		return limitUsed;
	}

	public void setLimitUsed(Double limitUsed) {
		this.limitUsed = limitUsed;
	}

	public Double getPrePurchaseUsed() {
		return prePurchaseUsed;
	}

	public void setPrePurchaseUsed(Double prePurchaseUsed) {
		this.prePurchaseUsed = prePurchaseUsed;
	}

	public Double getPreCashUsed() {
		return preCashUsed;
	}

	public void setPreCashUsed(Double preCashUsed) {
		this.preCashUsed = preCashUsed;
	}

	public Double getPurchaseUsed() {
		return purchaseUsed;
	}

	public void setPurchaseUsed(Double purchaseUsed) {
		this.purchaseUsed = purchaseUsed;
	}

	public Double getCashUsed() {
		return cashUsed;
	}

	public void setCashUsed(Double cashUsed) {
		this.cashUsed = cashUsed;
	}

	public String getTranxType() {
		return tranxType;
	}

	public void setTranxType(String tranxType) {
		this.tranxType = tranxType;
	}

}
