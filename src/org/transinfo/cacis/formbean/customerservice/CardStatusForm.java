package org.transinfo.cacis.formbean.customerservice;

import java.util.Collection;

import org.apache.struts.validator.ValidatorForm;

@SuppressWarnings( { "unchecked", "serial" })
public class CardStatusForm extends ValidatorForm {

	private String cardNo;
	private Collection statusList;

	private String mcardNo;

	public String getCardNo() {
		return cardNo;
	}

	public void setCardNo(String cardNo) {
		this.cardNo = cardNo;
	}

	public Collection getStatusList() {
		return statusList;
	}

	public void setStatusList(Collection statusList) {
		this.statusList = statusList;
	}

	public String getMcardNo() {
		return mcardNo;
	}

	public void setMcardNo(String mcardNo) {
		this.mcardNo = mcardNo;
	}

}
