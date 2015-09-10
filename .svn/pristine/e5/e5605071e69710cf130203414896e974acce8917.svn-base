package org.transinfo.cacis.dto.settings;

import java.io.Serializable;
import java.util.Date;

@SuppressWarnings("serial")
public class CurrencyRateDto implements Serializable {

	private float rate;
	private Date updatedDate = new Date();
	private String userId;

	public static class Id implements Serializable {

		private String issuerId;
		private CurrencyDto currCode;

		public Id() {
		}

		public Id(String issuerId, CurrencyDto currCode) {
			this.issuerId = issuerId;
			this.currCode = currCode;
		}

		public String getIssuerId() {
			return issuerId;
		}

		public void setIssuerId(String issuerId) {
			this.issuerId = issuerId;
		}

		public CurrencyDto getCurrCode() {
			return currCode;
		}

		public void setCurrCode(CurrencyDto currCode) {
			this.currCode = currCode;
		}

	}

	public Id id = new Id();

	public float getRate() {
		return rate;
	}

	public void setRate(float rate) {
		this.rate = rate;
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

	public Id getId() {
		return id;
	}

	public void setId(Id id) {
		this.id = id;
	}
}
