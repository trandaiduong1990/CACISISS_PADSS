package org.transinfo.cacis.dto.accounting;

import java.io.Serializable;
import java.util.Date;

@SuppressWarnings("serial")
public class CardHolderPaymentDto implements Serializable{

	private long     settlementId;
	private String   refNumber;
	private String   issuerId;
	private String   cardNumber;
	private String   currencyCode;
	private double   amountCurr;
	private Date     dateTime;
	private String   userId;
	public CardHolderPaymentDto(){}




	public String getIssuerId() {
		return issuerId;
	}
	public void setIssuerId(String issuerId) {
		this.issuerId = issuerId;
	}
	public String getUserId() {
		return userId;
	}
	public void setUserId(String userId) {
		this.userId = userId;
	}

	public String getCurrencyCode() {
		return currencyCode;
	}

	public void setCurrencyCode(String currencyCode) {
		this.currencyCode = currencyCode;
	}

	public Date getDateTime() {
		return dateTime;
	}

	public void setDateTime(Date dateTime) {
		this.dateTime = dateTime;
	}



	public double getAmountCurr() {
		return amountCurr;
	}



	public void setAmountCurr(double amountCurr) {
		this.amountCurr = amountCurr;
	}



	public long getSettlementId() {
		return settlementId;
	}



	public void setSettlementId(long settlementId) {
		this.settlementId = settlementId;
	}



	public String getRefNumber() {
		return refNumber;
	}



	public void setRefNumber(String refNumber) {
		this.refNumber = refNumber;
	}




	public String getCardNumber() {
		return cardNumber;
	}




	public void setCardNumber(String cardNumber) {
		this.cardNumber = cardNumber;
	}

}
