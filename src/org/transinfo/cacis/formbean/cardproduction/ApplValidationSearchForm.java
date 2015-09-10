package org.transinfo.cacis.formbean.cardproduction;

import java.util.Map;

import org.apache.struts.validator.ValidatorForm;
import org.transinfo.cacis.dataacess.DAOFactory;
import org.transinfo.cacis.dataacess.dao.cardproduction.ApplValidationDAO;

@SuppressWarnings("serial")
public class ApplValidationSearchForm extends ValidatorForm {
	
	private String applValName;
	private String issuerId;
	
	private Map applValNameList;

	public String getApplValName() {
		return applValName;
	}

	public void setApplValName(String applValName) {
		this.applValName = applValName;
	}

	public String getIssuerId() {
		return issuerId;
	}

	public void setIssuerId(String issuerId) {
		this.issuerId = issuerId;
	}

	public Map getApplValNameList() {
		return applValNameList;
	}

	public void setApplValNameList(Map applValNameList) {
		this.applValNameList = applValNameList;
	}

	public void getPreListData() {

		try{
			ApplValidationDAO objDAO = DAOFactory.getInstance().getApplValidationDAO();
	 		
			if(applValNameList == null)  {
				applValNameList = objDAO.getApplNameList();	
		    }

		} catch(Exception e) {
		      System.out.println("Error while getting PreList in CustomerTypeSearchForm:" + e.getMessage());
		}
	}
}
