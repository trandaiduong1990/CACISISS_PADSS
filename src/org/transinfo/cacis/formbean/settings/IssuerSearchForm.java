package org.transinfo.cacis.formbean.settings;

import java.util.Map;

import org.apache.struts.validator.ValidatorForm;
import org.transinfo.cacis.dataacess.DAOFactory;
import org.transinfo.cacis.dataacess.dao.settings.IssuerDAO;

/*
 * This a bean - and also a Value - or Data Transfer Object
 */

@SuppressWarnings({ "unchecked", "serial" })
public class IssuerSearchForm extends ValidatorForm {

	private String issuerId;
	private String issuerName;
	private String status;

	private Map issuerList;
	private Map statusList;

	public IssuerSearchForm() {
		// getPreListData();

	}

	public IssuerSearchForm(String issuerId) {
		setIssuerId(issuerId);

	}

	public String getIssuerId() {
		return issuerId;
	}

	public void setIssuerId(String issuerId) {
		this.issuerId = issuerId;
	}

	public String getIssuerName() {
		return issuerName;
	}

	public void setIssuerName(String issuerName) {
		this.issuerName = issuerName;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public void getPreListData() {

		try {
			IssuerDAO objDAO = DAOFactory.getInstance().getIssuerDAO();

			if (issuerList == null) {
				issuerList = objDAO.issuerListData();
				;
			}
			if (statusList == null) {
				statusList = objDAO.statusListData("CODE_AI");
			}
		} catch (Exception e) {
			System.out.println("Error while getting  IssuerList:"
					+ e.getMessage());
		}
	}
	
	public Map getStatusList() {
		return statusList;
	}

	public void setStatusList(Map statusList) {
		this.statusList = statusList;
	}

	public Map getIssuerList() {
		return issuerList;
	}

	public void setIssuerList(Map issuerList) {
		this.issuerList = issuerList;
	}

}
