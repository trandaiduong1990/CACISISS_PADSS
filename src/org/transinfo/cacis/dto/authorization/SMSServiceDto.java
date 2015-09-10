package org.transinfo.cacis.dto.authorization;

import java.io.Serializable;
import java.util.Date;

public class SMSServiceDto implements Serializable {
	private long cardNumber;

	private char accountEnquiry;

	private char paymentAlert;

	private char generalMsg;

	private char status;

	private Date lastUpdatedDate = new Date();

	private String lastUpdatedBy;

	public char getAccountEnquiry() {
		return accountEnquiry;
	}

	public void setAccountEnquiry(char accountEnquiry) {
		this.accountEnquiry = accountEnquiry;
	}

	public long getCardNumber() {
		return cardNumber;
	}

	public void setCardNumber(long cardNumber) {
		this.cardNumber = cardNumber;
	}

	public char getGeneralMsg() {
		return generalMsg;
	}

	public void setGeneralMsg(char generalMsg) {
		this.generalMsg = generalMsg;
	}

	public String getLastUpdatedBy() {
		return lastUpdatedBy;
	}

	public void setLastUpdatedBy(String lastUpdatedBy) {
		this.lastUpdatedBy = lastUpdatedBy;
	}

	public Date getLastUpdatedDate() {
		return lastUpdatedDate;
	}

	public void setLastUpdatedDate(Date lastUpdatedDate) {
		this.lastUpdatedDate = lastUpdatedDate;
	}

	public char getPaymentAlert() {
		return paymentAlert;
	}

	public void setPaymentAlert(char paymentAlert) {
		this.paymentAlert = paymentAlert;
	}

	public char getStatus() {
		return status;
	}

	public void setStatus(char status) {
		this.status = status;
	}
}
