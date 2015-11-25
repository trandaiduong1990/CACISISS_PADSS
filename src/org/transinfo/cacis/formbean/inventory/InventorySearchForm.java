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
public class InventorySearchForm extends ValidatorForm {
	private String orderNo;
	private Map orderNoList;
	private String status;

	public String getOrderNo() {
		return orderNo;
	}

	public void setOrderNo(String orderNo) {
		this.orderNo = orderNo;
	}

	public Map getOrderNoList() {
		return orderNoList;
	}

	public void setOrderNoList(Map orderNoList) {
		this.orderNoList = orderNoList;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public void getPreListData() {
		try {
			InventoryManagementDAO objDAO = DAOFactory.getInstance().getInventoryManagementDAO();

			if (orderNoList == null) {
				orderNoList = objDAO.getOrderNoList(status);
			}
		} catch (Exception e) {
			System.out
					.println("Error while getting PreList in CustomerTypeSearchForm:"
							+ e.getMessage());
		}
	}

}
