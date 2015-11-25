package org.transinfo.cacis.formbean.collectionmanagement;

import java.util.Map;

import org.apache.struts.validator.ValidatorForm;
import org.transinfo.cacis.dataacess.DAOFactory;
import org.transinfo.cacis.dataacess.dao.collectionmanagement.CollectionAgeingActionDAO;
import org.transinfo.cacis.dataacess.dao.collectionmanagement.DelinquencyFeeSetupDAO;

@SuppressWarnings("serial")
public class CollectionAgeingActionSearchForm extends ValidatorForm {
	private String issuerId;
	private String ageingPolicy;
	Map ageingPolicyList;
	public String getIssuerId() {
		return issuerId;
	}
	public void setIssuerId(String issuerId) {
		this.issuerId = issuerId;
	}
	public String getAgeingPolicy() {
		return ageingPolicy;
	}
	public void setAgeingPolicy(String ageingPolicy) {
		this.ageingPolicy = ageingPolicy;
	}
	public Map getAgeingPolicyList() {
		return ageingPolicyList;
	}
	public void setAgeingPolicyList(Map ageingPolicyList) {
		this.ageingPolicyList = ageingPolicyList;
	}
	public void getPreListData() {

		try {
			CollectionAgeingActionDAO objDAO = DAOFactory.getInstance().getCollectionAgeingActionDAO();

			if (ageingPolicyList == null) {
				ageingPolicyList = objDAO.getAgeingList(issuerId);
			}
		} catch (Exception e) {
			System.out
					.println("Error while getting PreList in CustomerTypeSearchForm:"
							+ e.getMessage());
		}
	}
}
