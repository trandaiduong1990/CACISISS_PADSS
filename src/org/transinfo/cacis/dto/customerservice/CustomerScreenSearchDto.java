package org.transinfo.cacis.dto.customerservice;

import java.io.Serializable;

public class CustomerScreenSearchDto implements Serializable {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private String cardNo;
	private String customerName;
	private String strFromDate;
	private String strToDate;

	private String autoAccNo;
	private String email;
	private String dob;
	
	public CustomerScreenSearchDto(){}
	
	public String getCustomerName() {
		return customerName;
	}
	
	public void setCustomerName(String customerName) {
		this.customerName = customerName;
	}

	public String getCardNo() {
		return cardNo;
	}

	public void setCardNo(String cardNo) {
		this.cardNo = cardNo;
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

	public String getAutoAccNo() {
		return autoAccNo;
	}

	public void setAutoAccNo(String autoAccNo) {
		this.autoAccNo = autoAccNo;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getDob() {
		return dob;
	}

	public void setDob(String dob) {
		this.dob = dob;
	}
	
}
