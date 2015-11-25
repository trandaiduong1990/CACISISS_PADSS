package org.transinfo.cacis.formbean.collectionmanagement;

import java.util.Map;

import org.apache.struts.validator.ValidatorForm;
import org.transinfo.cacis.dataacess.DAOFactory;
import org.transinfo.cacis.dataacess.dao.collectionmanagement.CollectionAccountDetailsDAO;
import org.transinfo.cacis.dataacess.dao.collectionmanagement.CollectionAgeingActionDAO;
import org.transinfo.cacis.dataacess.dao.collectionmanagement.DelinquencyFeeSetupDAO;

@SuppressWarnings("serial")
public class CollectionAccountDetailsSearchForm extends ValidatorForm {
	private String cardNo;
	private String customerName;
	private String status="U";
	private String agentId;
	private String issuerId;
	Map statusList;
	Map agentList;

	public String getCustomerName() {
		return customerName;
	}

	public void setCustomerName(String customerName) {
		this.customerName = customerName;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getCardNo() {
		return cardNo;
	}

	public void setCardNo(String cardNo) {
		this.cardNo = cardNo;
	}

	public String getAgentId() {
		return agentId;
	}

	public void setAgentId(String agentId) {
		this.agentId = agentId;
	}

	public Map getStatusList() {
		return statusList;
	}

	public void setStatusList(Map statusList) {
		this.statusList = statusList;
	}

	public Map getAgentList() {
		return agentList;
	}

	public void setAgentList(Map agentList) {
		this.agentList = agentList;
	}

	public String getIssuerId() {
		return issuerId;
	}

	public void setIssuerId(String issuerId) {
		this.issuerId = issuerId;
	}

	public void getPreListData() {

		try {
			CollectionAccountDetailsDAO objDAO = DAOFactory.getInstance()
					.getCollectionAccountDetailsDAO();

			if (statusList == null) {
				statusList = objDAO.getStatusList();
			}
			if (agentList == null) {
				agentList = objDAO.getAgentList(issuerId);
			}
		} catch (Exception e) {
			System.out
					.println("Error while getting PreList in CustomerTypeSearchForm:"
							+ e.getMessage());
		}
	}
}
