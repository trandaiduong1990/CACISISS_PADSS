package org.transinfo.cacis.formbean.letters;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.struts.validator.ValidatorForm;
import org.transinfo.cacis.common.constants.CommonCodes;
import org.transinfo.cacis.dataacess.DAOFactory;
import org.transinfo.cacis.dataacess.dao.letters.LetterApplMapDAO;
import org.transinfo.cacis.dataacess.dao.settings.EMVProfileDAO;
import org.transinfo.cacis.dto.letters.LetterTemplateDto;

public class LetterApplMapSetupForm extends ValidatorForm {

	private String issuerId;
	private String applAction;
	private String applActionDesc;
	private String applModule;
	private String status;
	private String lastUpdatedBy;
	private String letterTemplate;
	private String lastUpdatedDate;
	
	public List letterApplMapList;
	private Map letterCodeList;
	private Map statusList;
	
	public String getIssuerId() {
		return issuerId;
	}
	public void setIssuerId(String issuerId) {
		this.issuerId = issuerId;
	}
	public String getApplAction() {
		return applAction;
	}
	public void setApplAction(String applAction) {
		this.applAction = applAction;
	}
	public String getApplActionDesc() {
		return applActionDesc;
	}
	public void setApplActionDesc(String applActionDesc) {
		this.applActionDesc = applActionDesc;
	}
	public String getApplModule() {
		return applModule;
	}
	public void setApplModule(String applModule) {
		this.applModule = applModule;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public String getLastUpdatedBy() {
		return lastUpdatedBy;
	}
	public void setLastUpdatedBy(String lastUpdatedBy) {
		this.lastUpdatedBy = lastUpdatedBy;
	}
	public String getLetterTemplate() {
		return letterTemplate;
	}
	public void setLetterTemplate(String letterTemplate) {
		this.letterTemplate = letterTemplate;
	}
	public String getLastUpdatedDate() {
		return lastUpdatedDate;
	}
	public void setLastUpdatedDate(String lastUpdatedDate) {
		this.lastUpdatedDate = lastUpdatedDate;
	}
	public List getLetterApplMapList() {
		return letterApplMapList;
	}
	public void setLetterApplMapList(List letterApplMapList) {
		this.letterApplMapList = letterApplMapList;
	}
	public Map getLetterCodeList() {
		return letterCodeList;
	}
	public void setLetterCodeList(Map letterCodeList) {
		this.letterCodeList = letterCodeList;
	}
	public Map getStatusList() {
		return statusList;
	}
	public void setStatusList(Map statusList) {
		this.statusList = statusList;
	}
	
	public void getPreListData() {
		
		try {
			LetterApplMapDAO objLetterApplMapDAO = DAOFactory.getInstance()
					.getLetterApplMapDAO();
			
			if (letterCodeList == null) {
				letterCodeList = objLetterApplMapDAO.getLetterCodeList();
			}
			
			if (statusList == null) {
				statusList = new HashMap();
				statusList.put("A", "Active");
				statusList.put("D", "DeActive");
			}
			
		} catch (Exception e) {
			System.out
			.println("Error while getting  PreListData in EmvProfileForm:"
					+ e);
		}
	}
	
}
