package org.transinfo.cacis.formbean.batchprocess;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.struts.action.ActionMapping;
import org.apache.struts.validator.ValidatorForm;
import org.transinfo.cacis.dataacess.DAOFactory;
import org.transinfo.cacis.dataacess.dao.batchprocess.CardBatchProcessADO;
import org.transinfo.cacis.dataacess.dao.collectionmanagement.CollectionAccountDetailsDAO;

@SuppressWarnings( { "serial", "unchecked" })
public class BatchProcessForm extends ValidatorForm {
	private String issuerId;
	private String userId;
	private String batchIdArray = "";
	private List<BatchProcess> batchProcessList = new ArrayList<BatchProcess>();
	private String applicationType;
	private Map applTypeList;
	private String cardBatchStatus;
	private Map cardBatchStatusList;
	
	public String getBatchIdArray() {
		return batchIdArray;
	}

	public void setBatchIdArray(String batchIdArray) {
		this.batchIdArray = batchIdArray;
	}

	public List<BatchProcess> getBatchProcessList() {
		return batchProcessList;
	}

	public void setBatchProcessList(List<BatchProcess> batchProcessList) {
		this.batchProcessList = batchProcessList;
	}

	public String getIssuerId() {
		return issuerId;
	}

	public void setIssuerId(String issuerId) {
		this.issuerId = issuerId;
	}

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public String getApplicationType() {
		return applicationType;
	}

	public void setApplicationType(String applicationType) {
		this.applicationType = applicationType;
	}


	public Map getApplTypeList() {
		return applTypeList;
	}

	public void setApplTypeList(Map applTypeList) {
		this.applTypeList = applTypeList;
	}

	public String getCardBatchStatus() {
		return cardBatchStatus;
	}

	public void setCardBatchStatus(String cardBatchStatus) {
		this.cardBatchStatus = cardBatchStatus;
	}

	public Map getCardBatchStatusList() {
		return cardBatchStatusList;
	}

	public void setCardBatchStatusList(Map cardBatchStatusList) {
		this.cardBatchStatusList = cardBatchStatusList;
	}
	
	public void getPreListData() {

		try {
			CardBatchProcessADO objDAO = DAOFactory.getInstance()
					.getCardBatchProcessADO();

			if (applTypeList == null) {
				applTypeList = objDAO.getApplTypleList();
			}
			if (cardBatchStatusList == null) {
				cardBatchStatusList = objDAO.getCardBatchStatusList();
			}
		} catch (Exception e) {
			System.out
					.println("Error while getting PreList in CustomerTypeSearchForm:"
							+ e.getMessage());
		}
	}
	
}
