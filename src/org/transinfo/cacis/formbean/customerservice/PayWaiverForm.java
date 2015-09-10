package org.transinfo.cacis.formbean.customerservice;

import java.util.List;

import org.apache.struts.validator.ValidatorForm;

@SuppressWarnings({ "unchecked", "serial" })
public class PayWaiverForm extends ValidatorForm {
	
	private String cardNo;
	private String csn;
	
	private List feeList;

	private String selectedSerialNos[] = {};
	private String serialNosTowaive = "";

	private String cardType="";

	private String remarks;

	private String butEnable="N";
	
	public String getCardNo() {
		return cardNo;
	}
	public void setCardNo(String cardNo) {
		this.cardNo = cardNo;
	}
	public String getCsn() {
		return csn;
	}
	public void setCsn(String csn) {
		this.csn = csn;
	}
	public List getFeeList() {
		return feeList;
	}
	public void setFeeList(List feeList) {
		this.feeList = feeList;
	}
	public String[] getSelectedSerialNos() {
		return selectedSerialNos;
	}
	public void setSelectedSerialNos(String[] selectedSerialNos) {
		this.selectedSerialNos = selectedSerialNos;
	}
	public String getSerialNosTowaive() {
		return serialNosTowaive;
	}
	public void setSerialNosTowaive(String serialNosTowaive) {
		this.serialNosTowaive = serialNosTowaive;
	}
	public String getCardType() {
		return cardType;
	}
	public void setCardType(String cardType) {
		this.cardType = cardType;
	}
	public String getRemarks() {
		return remarks;
	}
	public void setRemarks(String remarks) {
		this.remarks = remarks;
	}
	public String getButEnable() {
		return butEnable;
	}
	public void setButEnable(String butEnable) {
		this.butEnable = butEnable;
	}
}
