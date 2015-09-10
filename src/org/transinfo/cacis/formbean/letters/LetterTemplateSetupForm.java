package org.transinfo.cacis.formbean.letters;

import java.util.Map;

import org.apache.struts.upload.FormFile;
import org.apache.struts.validator.ValidatorForm;
import org.transinfo.cacis.dataacess.DAOFactory;
import org.transinfo.cacis.dataacess.dao.letters.LetterTemplateDAO;
import org.transinfo.cacis.dto.letters.LetterCategoryDto;

@SuppressWarnings("serial")
public class LetterTemplateSetupForm extends ValidatorForm {

	private String letterId;
	private String description;
	private String letterCategory;
	private String applicationSource;
	private String signatoryName;
	private String department;
	private String issuerId;
	private String noOfCopies;
	private String status;
	private String manualEntry = "N";
	private String sqlQuery;
	private String lastUpdatedBy;
	private String lastUpdatedDate;
	
	private Map letterCategoryList;
	private FormFile fileUpload;
	
	public LetterTemplateSetupForm() {}

	public String getLetterId() {
		return letterId;
	}

	public void setLetterId(String letterId) {
		this.letterId = letterId;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getLetterCategory() {
		return letterCategory;
	}

	public void setLetterCategory(String letterCategory) {
		this.letterCategory = letterCategory;
	}

	public String getApplicationSource() {
		return applicationSource;
	}

	public void setApplicationSource(String applicationSource) {
		this.applicationSource = applicationSource;
	}

	public String getSignatoryName() {
		return signatoryName;
	}

	public void setSignatoryName(String signatoryName) {
		this.signatoryName = signatoryName;
	}

	public String getDepartment() {
		return department;
	}

	public void setDepartment(String department) {
		this.department = department;
	}

	public String getIssuerId() {
		return issuerId;
	}

	public void setIssuerId(String issuerId) {
		this.issuerId = issuerId;
	}

	public String getNoOfCopies() {
		return noOfCopies;
	}

	public void setNoOfCopies(String noOfCopies) {
		this.noOfCopies = noOfCopies;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getManualEntry() {
		return manualEntry;
	}

	public void setManualEntry(String manualEntry) {
		this.manualEntry = manualEntry;
	}

	public String getLastUpdatedBy() {
		return lastUpdatedBy;
	}

	public void setLastUpdatedBy(String lastUpdatedBy) {
		this.lastUpdatedBy = lastUpdatedBy;
	}

	public String getLastUpdatedDate() {
		return lastUpdatedDate;
	}

	public void setLastUpdatedDate(String lastUpdatedDate) {
		this.lastUpdatedDate = lastUpdatedDate;
	}

	public String getSqlQuery() {
		return sqlQuery;
	}

	public void setSqlQuery(String sqlQuery) {
		this.sqlQuery = sqlQuery;
	}

	public Map getLetterCategoryList() {
		return letterCategoryList;
	}

	public void setLetterCategoryList(Map letterCategoryList) {
		this.letterCategoryList = letterCategoryList;
	}

	public FormFile getFileUpload() {
		return fileUpload;
	}

	public void setFileUpload(FormFile fileUpload) {
		this.fileUpload = fileUpload;
	}

	public void getPreListData(String issuerIdF) {
		
		try{
			LetterTemplateDAO objDAO = DAOFactory.getInstance().getLetterTemplateDAO();
	 		
			if(letterCategoryList == null)  {
				letterCategoryList = objDAO.getLetterCategoryList(issuerIdF);	
		    }

		} catch(Exception e) {
		      System.out.println("Error while getting PreList in LetterTemplateSetupForm:" + e.getMessage());
		}
	}
	
}
