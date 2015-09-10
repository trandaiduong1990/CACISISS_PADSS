package org.transinfo.cacis.dto.applicationforms;

import java.io.Serializable;
import java.util.Date;

@SuppressWarnings("serial")
public class CardsRenewalHistoryDto implements Serializable {

	private String renewalHistorySerial;
	private long cardNumber;
	private String issuerId;
	private String cardExpireDate;
	private String userId = "Admin";
	private Date updatedDate = new Date();

	public CardsRenewalHistoryDto() {
	}

	public String getCardExpireDate() {
		return cardExpireDate;
	}

	public void setCardExpireDate(String cardExpireDate) {
		this.cardExpireDate = cardExpireDate;
	}

	public long getCardNumber() {
		return cardNumber;
	}

	public void setCardNumber(long cardNumber) {
		this.cardNumber = cardNumber;
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

	public String getIssuerId() {
		return issuerId;
	}

	public void setIssuerId(String issuerId) {
		this.issuerId = issuerId;
	}

	public String getRenewalHistorySerial() {
		return renewalHistorySerial;
	}

	public void setRenewalHistorySerial(String renewalHistorySerial) {
		this.renewalHistorySerial = renewalHistorySerial;
	}
}
