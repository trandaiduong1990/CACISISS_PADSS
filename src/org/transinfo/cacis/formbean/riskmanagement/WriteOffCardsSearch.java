package org.transinfo.cacis.formbean.riskmanagement;

import org.apache.struts.validator.ValidatorForm;
import org.transinfo.cacis.formbean.common.DateForm;

public class WriteOffCardsSearch extends ValidatorForm {

	private String issuerId;

	private String cardNumber;

	private String id;

	private String status;

	private String strFromDate;

	private String strToDate;

	DateForm writeOffDateForm = new DateForm();

	public String getCardNumber() {
		return cardNumber;
	}

	public void setCardNumber(String cardNumber) {
		this.cardNumber = cardNumber;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getIssuerId() {
		return issuerId;
	}

	public void setIssuerId(String issuerId) {
		this.issuerId = issuerId;
	}

	public DateForm getWriteOffDateForm() {
		return writeOffDateForm;
	}

	public void setWriteOffDateForm(DateForm writeOffDateForm) {
		this.writeOffDateForm = writeOffDateForm;
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

}
