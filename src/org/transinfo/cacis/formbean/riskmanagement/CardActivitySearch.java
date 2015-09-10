package org.transinfo.cacis.formbean.riskmanagement;

import org.apache.struts.validator.ValidatorForm;

public class CardActivitySearch extends ValidatorForm {

	private String cardActivityId;

	private String cardNumber;

	private String dateTime;

	private String activity;

	private String stationIp;

	private String reason;
	
	private String userId;

	private String strFromDate;

	private String strToDate;

	public String getCardActivityId() {
		return cardActivityId;
	}

	public void setCardActivityId(String cardActivityId) {
		this.cardActivityId = cardActivityId;
	}

	public String getCardNumber() {
		return cardNumber;
	}

	public void setCardNumber(String cardNumber) {
		this.cardNumber = cardNumber;
	}

	public String getActivity() {
		return activity;
	}

	public void setActivity(String activity) {
		this.activity = activity;
	}

	public String getReason() {
		return reason;
	}

	public void setReason(String reason) {
		this.reason = reason;
	}

	public String getStationIp() {
		return stationIp;
	}

	public void setStationIp(String stationip) {
		this.stationIp = stationip;
	}

	public String getDateTime() {
		return dateTime;
	}

	public void setDateTime(String dateTime) {
		this.dateTime = dateTime;
	}

	public String getStrFromDate() {
		return strFromDate;
	}

	public void setStrFromDate(String strFromDate) {
		this.strFromDate = strFromDate;
	}

	public String getStrToDate() {
		return strToDate;
	}

	public void setStrToDate(String strToDate) {
		this.strToDate = strToDate;
	}

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

}
