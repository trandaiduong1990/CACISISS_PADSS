package org.transinfo.cacis.formbean.settings;

import java.util.Map;

import org.apache.struts.validator.ValidatorForm;
import org.transinfo.cacis.dataacess.DAOFactory;
import org.transinfo.cacis.dataacess.dao.settings.CustomerTypeDAO;
import org.transinfo.cacis.dataacess.dao.settings.EMVProfileDAO;

@SuppressWarnings("serial")
public class EMVProfileSearchForm extends ValidatorForm {
	
	private String emvProfileName;
	private String applType;
	private String screen;
	
	Map emvProfileNameList;
	Map applTypeList;
	
	public EMVProfileSearchForm() {
		
	}
	
	public EMVProfileSearchForm(String emvProfileName, String applType) {
		this.emvProfileName = emvProfileName;
		this.applType = applType;
	}

	public String getEmvProfileName() {
		return emvProfileName;
	}

	public void setEmvProfileName(String emvProfileName) {
		this.emvProfileName = emvProfileName;
	}

	public String getApplType() {
		return applType;
	}

	public void setApplType(String applType) {
		this.applType = applType;
	}
	
	public Map getEmvProfileNameList() {
		return emvProfileNameList;
	}

	public void setEmvProfileNameList(Map emvProfileNameList) {
		this.emvProfileNameList = emvProfileNameList;
	}

	public Map getApplTypeList() {
		return applTypeList;
	}

	public void setApplTypeList(Map applTypeList) {
		this.applTypeList = applTypeList;
	}

	public String getScreen() {
		return screen;
	}

	public void setScreen(String screen) {
		this.screen = screen;
	}

	public void getPreListData(){
		
		try{
			EMVProfileDAO objDAO = DAOFactory.getInstance().getEMVProfileDAO();
	 		
			if(emvProfileNameList == null)  {
				emvProfileNameList = objDAO.empProfileNameListData();	
		    }

			if(applTypeList == null) {
				applTypeList = objDAO.codeMasterList();
			}
		} catch(Exception e) {
		      System.out.println("Error while getting PreList in CustomerTypeSearchForm:" + e.getMessage());
		}
	}
}
