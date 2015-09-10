package org.transinfo.cacis.formbean.authorization;

import java.util.Map;

import org.apache.struts.validator.ValidatorForm;
import org.transinfo.cacis.dataacess.DAOFactory;
import org.transinfo.cacis.dataacess.dao.authorization.SMSServiceDAO;
import org.transinfo.cacis.formbean.common.DateForm;

public class SMSServiceForm extends ValidatorForm {

	private String cardNumber = "";

	private String accountEnquiry = "";

	private String paymentAlert = "";

	private String generalMsg = "";

	private String status = "";

	DateForm lastUpdatedDateForm = new DateForm();
	
	private String lastUpdatedBy = "";
	
	private String issuerId = "";
	
	private String action = "";

	private Map statusList;

	public String getAccountEnquiry() {
		return accountEnquiry;
	}

	public void setAccountEnquiry(String accountEnquiry) {
		this.accountEnquiry = accountEnquiry;
	}

	public String getCardNumber() {
		return cardNumber;
	}

	public void setCardNumber(String cardNumber) {
		this.cardNumber = cardNumber;
	}

	public String getGeneralMsg() {
		return generalMsg;
	}

	public void setGeneralMsg(String generalMsg) {
		this.generalMsg = generalMsg;
	}

	public String getLastUpdatedBy() {
		return lastUpdatedBy;
	}

	public void setLastUpdatedBy(String lastUpdatedBy) {
		this.lastUpdatedBy = lastUpdatedBy;
	}

	public DateForm getLastUpdatedDateForm() {
		return lastUpdatedDateForm;
	}

	public void setLastUpdatedDateForm(DateForm lastUpdated) {
		this.lastUpdatedDateForm = lastUpdated;
	}

	public String getPaymentAlert() {
		return paymentAlert;
	}

	public void setPaymentAlert(String paymentAlert) {
		this.paymentAlert = paymentAlert;
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

	public String getAction() {
		return action;
	}

	public void setAction(String action) {
		this.action = action;
	}

	public String getIssuerId() {
		return issuerId;
	}

	public void setIssuerId(String issuerId) {
		this.issuerId = issuerId;
	}

	public void getPreListData() {
		try {
			SMSServiceDAO objSMSServiceDAO = DAOFactory.getInstance()
					.getSMSServiceDAO();
			if (statusList == null) {
				statusList = objSMSServiceDAO.statusListData("CODE_AI");
			}
		} catch (Exception e) {
			System.out.println("Error while getting  PreListData:" + e);
		}
	}
}
