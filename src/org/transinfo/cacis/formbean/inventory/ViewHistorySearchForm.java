package org.transinfo.cacis.formbean.inventory;

import java.util.Collection;
import java.util.Map;

import org.apache.struts.validator.ValidatorForm;
import org.transinfo.cacis.dataacess.DAOFactory;
import org.transinfo.cacis.dataacess.dao.collectionmanagement.CollectionAgentDAO;
import org.transinfo.cacis.dataacess.dao.collectionmanagement.DelinquencyNotificationSetupDAO;
import org.transinfo.cacis.dataacess.dao.inventory.InventoryManagementDAO;
import org.transinfo.cacis.dataacess.dao.inventory.InventoryMasterDAO;

/**
 * @author Clark
 *
 */
@SuppressWarnings("serial")
public class ViewHistorySearchForm extends ValidatorForm {
	private String branchId;
	private Map branchIdList;
	private String status;
	private Map statusList;
	private String issuerId;
	
	public String getBranchId() {
		return branchId;
	}

	public void setBranchId(String branchId) {
		this.branchId = branchId;
	}

	public Map getBranchIdList() {
		return branchIdList;
	}

	public void setBranchIdList(Map branchIdList) {
		this.branchIdList = branchIdList;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public Map getStatusList() {
		return statusList;
	}

	public void setStatusList(Map statusList) {
		this.statusList = statusList;
	}

	public String getIssuerId() {
		return issuerId;
	}

	public void setIssuerId(String issuerId) {
		this.issuerId = issuerId;
	}

	public void getPreListData() {
		try {
			InventoryManagementDAO objDAO = DAOFactory.getInstance().getInventoryManagementDAO();

			if (branchIdList == null) {
				branchIdList = objDAO.branchListData(issuerId);
			}
		} catch (Exception e) {
			System.out
					.println("Error while getting PreList in CustomerTypeSearchForm:"
							+ e.getMessage());
		}
	}

}
