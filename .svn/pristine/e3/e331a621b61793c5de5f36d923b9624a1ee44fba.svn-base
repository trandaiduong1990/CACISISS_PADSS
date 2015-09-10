package org.transinfo.cacis.formbean.customerservice;

import java.util.Date;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.struts.action.ActionMapping;
import org.apache.struts.validator.ValidatorForm;
import org.transinfo.cacis.dataacess.DAOFactory;
import org.transinfo.cacis.dataacess.dao.customerservice.CardChangeDAO;
import org.transinfo.cacis.dto.cardproduction.CardsDto;
import org.transinfo.cacis.dto.cardproduction.CustomerAccountDto;

@SuppressWarnings({ "unchecked", "serial" })
public class AccountAdjustmentForm extends ValidatorForm {

	//private CustomerInfoDto customer;
	private CardsDto card;
	private CustomerAccountDto customerAccount;

	private Map productList;
	private String issuerID;
	private String userId;



	private String cardNo;
	private String cardType;
	private String cardProductName;
	private String cardStatus;

	private String cardLimit;
	private String limitUsed;
	private String purchaseUsed;
	private String cashUsed;

	private String adjustmentAmount;
	private String creditOrDebit;
	private Date effectiveDate;
	//private String formattedEffectiveDate;
	private String reason;
	private String tranxType;

	public String getCardNo() {
		return cardNo;
	}
	public void setCardNo(String cardNo) {
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
	public String getLimitUsed() {
		return limitUsed;
	}
	public void setLimitUsed(String limitUsed) {
		this.limitUsed = limitUsed;
	}
	public String getPurchaseUsed() {
		return purchaseUsed;
	}
	public void setPurchaseUsed(String purchaseUsed) {
		this.purchaseUsed = purchaseUsed;
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




	public String getCashUsed() {
		return cashUsed;
	}
	public void setCashUsed(String cashUsed) {
		this.cashUsed = cashUsed;
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

	public String getReason() {
		return reason;
	}
	public void setReason(String reason) {
		this.reason = reason;
	}

	public void getPreListData(String issuerId) {

		try {

			CardChangeDAO objDAO = DAOFactory.getInstance().getCardChangeDAO();
			if (productList == null || productList.isEmpty()) {
				productList = objDAO.cardProductListData(issuerId);
			}

		} catch (Exception e) {
			System.out.println("Error while getting  PreListData:" + e.getMessage());
		}

	}

	// Reset form fields.
	public void reset(ActionMapping mapping, HttpServletRequest request) {
		cardNo = null;
		//changeProductID = null;
		//existCardStatus = null;
	}

	public CardsDto getCard() {
		return card;
	}
	public void setCard(CardsDto card) {
		this.card = card;
	}
	public CustomerAccountDto getCustomerAccount() {
		return customerAccount;
	}
	public void setCustomerAccount(CustomerAccountDto customerAccount) {
		this.customerAccount = customerAccount;
	}

	public Map getProductList() {
		return productList;
	}
	public void setProductList(Map productList) {
		this.productList = productList;
	}
	public Date getEffectiveDate() {
		return effectiveDate;
	}
	public void setEffectiveDate(Date effectiveDate) {
		this.effectiveDate = effectiveDate;
	}
	public String getTranxType() {
		return tranxType;
	}
	public void setTranxType(String tranxType) {
		this.tranxType = tranxType;
	}


}
