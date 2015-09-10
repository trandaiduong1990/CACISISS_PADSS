package org.transinfo.cacis.formbean.letters;

import java.util.Map;

import org.apache.struts.validator.ValidatorForm;
import org.transinfo.cacis.dataacess.DAOFactory;
import org.transinfo.cacis.dataacess.dao.letters.LetterTemplateDAO;
import org.transinfo.cacis.dataacess.dao.settings.EMVProfileDAO;
import org.transinfo.cacis.dto.letters.LetterCategoryDto;

@SuppressWarnings("serial")
public class LetterTemplateSearchForm extends ValidatorForm {

	private String letterId;
	private String description;
	private LetterCategoryDto category;
	private String applicationSource;
	private String signatoryName;
	private String department;
	private String issuerId;
	private String noOfCopies;
	private String status;
	private String manualEntry;
	private String lastUpdatedBy;
	private String lastUpdatedDate;
	
	private String totalCount;
	private String pageNo;
	
	private Map letterIdList;
	
	public LetterTemplateSearchForm() {}
	
	public LetterTemplateSearchForm(String letterId, String description, LetterCategoryDto category, String applicationSource
			, String signatoryName, String department, String issuerId, String noOfCopies, String status, String manualEntry
			, String lastUpdatedBy, String lastUpdatedDate) {
		this.letterId = letterId;
		this.description = description;
		this.category = category;
		this.applicationSource = applicationSource;
		this.signatoryName = signatoryName;
		this.department = department;
		this.issuerId = issuerId;
		this.noOfCopies = noOfCopies;
		this.status = status;
		this.manualEntry = manualEntry;
		this.lastUpdatedBy = lastUpdatedBy;
		this.lastUpdatedDate = lastUpdatedDate;
	}
	
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
	public LetterCategoryDto getCategory() {
		return category;
	}
	public void setCategory(LetterCategoryDto category) {
		this.category = category;
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
	public Map getLetterIdList() {
		return letterIdList;
	}
	public void setLetterIdList(Map letterIdList) {
		this.letterIdList = letterIdList;
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

	public String getTotalCount() {
		return totalCount;
	}

	public void setTotalCount(String totalCount) {
		this.totalCount = totalCount;
	}

	public String getPageNo() {
		return pageNo;
	}

	public void setPageNo(String pageNo) {
		this.pageNo = pageNo;
	}

	public void getPreListData(){
		
		try{
			LetterTemplateDAO objDAO = DAOFactory.getInstance().getLetterTemplateDAO();
	 		
			if(letterIdList == null)  {
				letterIdList = objDAO.getLetterCodeList();	
		    }

		} catch(Exception e) {
		      System.out.println("Error while getting PreList in LetterTemplateSearchForm:" + e.getMessage());
		}
	}
}
