package org.transinfo.cacis.dto.riskmanagement;

import java.io.Serializable;
import java.util.Date;

import org.transinfo.cacis.formbean.common.DateForm;

public class WriteOffCardsDto implements Cloneable, Serializable {

	private long cardNumber;

	private String id;

	private char status;

	private Date writeOffDate;

	private DateForm writeOffDateForm = new DateForm();

	private String strFromDate;

	private String strToDate;

	private DateForm lastUpdatedDateForm;

	private Date lastUpdatedDate = new Date();

	private String lastUpdatedBy;

	private String issuerId;

	public WriteOffCardsDto() {
	}

	public long getCardNumber() {
		return cardNumber;
	}

	public void setCardNumber(long cardNumber) {
		this.cardNumber = cardNumber;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public char getStatus() {
		return status;
	}

	public void setStatus(char status) {
		this.status = status;
	}

	public String getIssuerId() {
		return issuerId;
	}

	public void setIssuerId(String issuerId) {
		this.issuerId = issuerId;
	}

	public String getLastUpdatedBy() {
		return lastUpdatedBy;
	}

	public void setLastUpdatedBy(String lastUpdatedBy) {
		this.lastUpdatedBy = lastUpdatedBy;
	}

	public Date getWriteOffDate() {
		return writeOffDate;
	}

	public void setWriteOffDate(Date writeOffDateIn) {
		writeOffDate = writeOffDateIn;
		if (writeOffDate != null) {
			writeOffDateForm = new DateForm(writeOffDate);
		}
	}

	public DateForm getWriteOffDateForm() {
		return writeOffDateForm;
	}

	public void setWriteOffDateForm(DateForm writeOffDateForm) {
		this.writeOffDateForm = writeOffDateForm;
	}

	public Date getLastUpdatedDate() {
		return lastUpdatedDate;
	}

	public void setLastUpdatedDate(Date lastUpdatedDateIn) {
		lastUpdatedDate = lastUpdatedDateIn;
		if (lastUpdatedDate != null) {
			lastUpdatedDateForm = new DateForm(lastUpdatedDate);
		}
	}

	public DateForm getLastUpdatedDateForm() {
		return lastUpdatedDateForm;
	}

	public void setLastUpdatedDateForm(DateForm lastUpdatedDateForm) {
		this.lastUpdatedDateForm = lastUpdatedDateForm;
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
