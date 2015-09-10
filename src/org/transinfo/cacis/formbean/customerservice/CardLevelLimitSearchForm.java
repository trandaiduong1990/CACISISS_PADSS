package org.transinfo.cacis.formbean.customerservice;

import java.util.HashMap;
import java.util.Map;

import org.apache.struts.validator.ValidatorForm;
import org.transinfo.cacis.dataacess.DAOFactory;
import org.transinfo.cacis.dataacess.dao.cardproduction.CardDeliverDAO;

/*
 * This a bean - and also a Value - or Data Transfer Object
 */
@SuppressWarnings( { "serial" })
public class CardLevelLimitSearchForm extends ValidatorForm {

	private String searchCardNo;
	private String issuerId;

	private String cardHolderType;
	private String cardNo;
	
	private String status;

	private Map statusList;

	public CardLevelLimitSearchForm() {
		// getPreListData();
	}

	public String getIssuerId() {
		return issuerId;
	}

	public void setIssuerId(String issuerId) {
		this.issuerId = issuerId;
	}

	public String getCardHolderType() {
		return cardHolderType;
	}

	public void setCardHolderType(String cardHolderType) {
		this.cardHolderType = cardHolderType;
	}

	public String getSearchCardNo() {
		return searchCardNo;
	}

	public void setSearchCardNo(String searchCardNo) {
		this.searchCardNo = searchCardNo;
	}

	public String getCardNo() {
		return cardNo;
	}

	public void setCardNo(String cardNo) {
		this.cardNo = cardNo;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public Map getStatusList() {
		return statusList;
	}

	public void setStatusList(Map statusList) {
		this.statusList = statusList;
	}

	public void getPreListData() {

		try {

			if (statusList == null) {
				statusList = new HashMap();
				statusList.put("A", "Enable");
				statusList.put("I", "Disable");
			}

		} catch (Exception e) {
			System.out.println("Error while getting  PreListData in CardDeliverSearch:" + e.getMessage());
		}

	}

}
