package org.transinfo.cacis.formbean.collectionmanagement;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.struts.action.ActionMapping;
import org.apache.struts.validator.ValidatorForm;

public class AgeingAction {
	private String days;
	private String phone;
	private String remainder;
	private String remainderType;
	private String tempCardOff;
	private String writeOff;
	
	public AgeingAction() {
		// TODO Auto-generated constructor stub
	}
	public AgeingAction(String days, String phone, String remainder,
			String remainderType, String tempCardOff, String writeOff) {
		this.days = days;
		this.phone = phone;
		this.remainder = remainder;
		this.remainderType = remainderType;
		this.tempCardOff = tempCardOff;
		this.writeOff = writeOff;
	}
	public String getDays() {
		return days;
	}
	public void setDays(String days) {
		this.days = days;
	}
	public String getPhone() {
		return phone;
	}
	public void setPhone(String phone) {
		this.phone = phone;
	}
	public String getRemainder() {
		return remainder;
	}
	public void setRemainder(String remainder) {
		this.remainder = remainder;
	}
	public String getRemainderType() {
		return remainderType;
	}
	public void setRemainderType(String remainderType) {
		this.remainderType = remainderType;
	}
	public String getTempCardOff() {
		return tempCardOff;
	}
	public void setTempCardOff(String tempCardOff) {
		this.tempCardOff = tempCardOff;
	}
	public String getWriteOff() {
		return writeOff;
	}
	public void setWriteOff(String writeOff) {
		this.writeOff = writeOff;
	}

}
