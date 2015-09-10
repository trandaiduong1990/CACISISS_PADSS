package org.transinfo.cacis.formbean.collectionmanagement;

import java.util.Map;

import org.apache.struts.validator.ValidatorForm;
import org.transinfo.cacis.dataacess.DAOFactory;
import org.transinfo.cacis.dataacess.dao.collectionmanagement.DelinquencyPolicyDAO;

@SuppressWarnings("serial")
public class DelinquencyPolicySearchForm extends ValidatorForm {
	private String policyId;
	Map policyList;
	private String issuerId;
	
	public String getPolicyId() {
		return policyId;
	}

	public void setPolicyId(String policyId) {
		this.policyId = policyId;
	}

	public Map getPolicyList() {
		return policyList;
	}

	public void setPolicyList(Map policyList) {
		this.policyList = policyList;
	}

	public String getIssuerId() {
		return issuerId;
	}

	public void setIssuerId(String issuerId) {
		this.issuerId = issuerId;
	}

	public void getPreListData(String issuerId) {

		try {
			DelinquencyPolicyDAO objDAO = DAOFactory.getInstance().getDelinquencyPolicyDAO();

			if (policyList == null) {
				policyList = objDAO.delinquencyPolicyList(issuerId);
			}
		} catch (Exception e) {
			System.out
					.println("Error while getting PreList in CustomerTypeSearchForm:"
							+ e.getMessage());
		}
	}
}
