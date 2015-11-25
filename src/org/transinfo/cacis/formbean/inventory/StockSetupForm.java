package org.transinfo.cacis.formbean.inventory;

import java.util.Collection;
import java.util.Map;

import org.apache.struts.validator.ValidatorForm;
import org.transinfo.cacis.dataacess.DAOFactory;
import org.transinfo.cacis.dataacess.dao.collectionmanagement.CollectionAgentDAO;
import org.transinfo.cacis.dataacess.dao.collectionmanagement.DelinquencyNotificationSetupDAO;
import org.transinfo.cacis.dataacess.dao.inventory.InventoryMasterDAO;

@SuppressWarnings("serial")
public class StockSetupForm extends ValidatorForm {
	private String issuerId;
	private String cardProductId;
	private Map supplierList;
	private Collection stockList;


	public String getIssuerId() {
		return issuerId;
	}


	public void setIssuerId(String issuerId) {
		this.issuerId = issuerId;
	}


	public String getCardProductId() {
		return cardProductId;
	}


	public void setCardProductId(String cardProductId) {
		this.cardProductId = cardProductId;
	}


	public Map getSupplierList() {
		return supplierList;
	}


	public void setSupplierList(Map supplierList) {
		this.supplierList = supplierList;
	}


	public Collection getStockList() {
		return stockList;
	}


	public void setStockList(Collection stockList) {
		this.stockList = stockList;
	}


	public void getPreListData() {
		try {
			InventoryMasterDAO objDAO = DAOFactory.getInstance().getInventoryMasterDAO();

			if (supplierList == null) {
				supplierList = objDAO.getSupplierList(issuerId);
			}
		} catch (Exception e) {
			System.out
					.println("Error while getting PreList in CustomerTypeSearchForm:"
							+ e.getMessage());
		}
	}

}
