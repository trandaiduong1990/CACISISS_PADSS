package org.transinfo.cacis.formbean.collectionmanagement;

import java.util.Map;

import org.apache.struts.validator.ValidatorForm;
import org.transinfo.cacis.dataacess.DAOFactory;
import org.transinfo.cacis.dataacess.dao.collectionmanagement.CollectionAgentDAO;
import org.transinfo.cacis.dataacess.dao.collectionmanagement.DelinquencyNotificationSetupDAO;

@SuppressWarnings("serial")
public class CollectionAgentSearchForm extends ValidatorForm {
	private String issuerId;
	private String agentId;
	private String agentName;
	Map agentList;

	public String getIssuerId() {
		return issuerId;
	}

	public void setIssuerId(String issuerId) {
		this.issuerId = issuerId;
	}

	public String getAgentId() {
		return agentId;
	}

	public void setAgentId(String agentId) {
		this.agentId = agentId;
	}

	public String getAgentName() {
		return agentName;
	}

	public void setAgentName(String agentName) {
		this.agentName = agentName;
	}

	public Map getAgentList() {
		return agentList;
	}

	public void setAgentList(Map agentList) {
		this.agentList = agentList;
	}

	public void getPreListData() {
		try {
			CollectionAgentDAO objDAO = DAOFactory.getInstance().getCollectionAgentDAO();

			if (agentList == null) {
				agentList = objDAO.agentList(issuerId);
			}
		} catch (Exception e) {
			System.out
					.println("Error while getting PreList in CustomerTypeSearchForm:"
							+ e.getMessage());
		}
	}

}
